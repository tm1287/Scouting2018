package team1100.scouting2017;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.ParcelUuid;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.IOException;
import java.util.Set;
import java.util.UUID;

public class BluetoothActivity extends AppCompatActivity {


    private static UUID MY_UUID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth);
        Intent intent = getIntent();
        String[] data = intent.getStringArrayExtra(MainActivity.EXTRA_BLUE_DATA);
        int position = Integer.parseInt(intent.getStringExtra(MainActivity.EXTRA_TABLET_NUMBER));
        switch(position){
            case 0:
                MY_UUID = UUID.fromString("551371ae-9714-41ca-8bcc-12651809e863");
                break;
            case 1:
                MY_UUID = UUID.fromString("1426babc-2df2-429e-9233-f84435154fa2");
                break;
            case 2:
                MY_UUID = UUID.fromString("27bb9a0e-b772-4cde-96b9-74e7ffd1b679");
                break;
            case 3:
                MY_UUID = UUID.fromString("e4283b54-0e8f-4a5f-b2c2-dafdce12b75e");
                break;
            case 4:
                MY_UUID = UUID.fromString("6632b275-da16-403c-a95f-b5b9024248b1");
                break;
            case 5:
                MY_UUID = UUID.fromString("1f5733c5-4511-4f87-83d4-2973cd876674");
                break;
            default:
                MY_UUID = UUID.fromString("551371ae-9714-41ca-8bcc-12651809e863");
                break;
        }

        ConnectThread thread = new ConnectThread(getDevice(), data);
        thread.start();
    }

    public BluetoothDevice getDevice(){
        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
        if (pairedDevices.size() > 0) {
            // There are paired devices. Get the name and address of each paired device.
            for (BluetoothDevice device : pairedDevices) {
                String deviceName = device.getName();
                String deviceHardwareAddress = device.getAddress(); // MAC address
                for(ParcelUuid u : device.getUuids()){
                    System.out.println(deviceName);
                    System.out.println(u.getUuid().toString());
                    if(deviceName.equals("1100-PC")){
                        return device;
                    }
                }
            }
        }
        return null;
    }


    private class ConnectThread extends Thread {
        private final BluetoothSocket mmSocket;
        private final BluetoothDevice mmDevice;
        private final String[] data;

        public ConnectThread(BluetoothDevice device, String[] data) {
            // Use a temporary object that is later assigned to mmSocket
            // because mmSocket is final.
            BluetoothSocket tmp = null;
            mmDevice = device;
            this.data = data;

            try {
                // Get a BluetoothSocket to connect with the given BluetoothDevice.
                // MY_UUID is the app's UUID string, also used in the server code.
                tmp = mmDevice.createRfcommSocketToServiceRecord(MY_UUID);
            } catch (Exception e) {
                Snackbar.make(findViewById(android.R.id.content),"Connection failed on "+MY_UUID.toString(), Snackbar.LENGTH_LONG).show();
            }
            mmSocket = tmp;
        }

        public void run() {
            try {
                // Connect to the remote device through the socket. This call blocks
                // until it succeeds or throws an exception.
                mmSocket.connect();
                String packet = "";
                for(String d : data){
                    packet+=d + "\n";
                }
                mmSocket.getOutputStream().write(packet.getBytes());
                System.out.println("Packet: " + packet);
                System.out.println("Packet is "+ packet.getBytes().length + " bytes long ("+data.length+" matches).");
            } catch (Exception connectException) {
                // Unable to connect; close the socket and return.
                try {
                    mmSocket.close();
                } catch (Exception closeException) {
                    //Log.e(TAG, "Could not close the client socket", closeException);
                }
                return;
            }
            cancel();
        }

        // Closes the client socket and causes the thread to finish.
        public void cancel() {
            try {
                mmSocket.close();
            } catch (IOException e) {

            }
        }
    }
}