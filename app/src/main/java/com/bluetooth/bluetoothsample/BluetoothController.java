package com.bluetooth.bluetoothsample;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class BluetoothController {

    private  BluetoothAdapter adapter;

    public BluetoothController() {
       adapter = BluetoothAdapter.getDefaultAdapter();
    }

    private boolean isBluetoothOn() {
        return adapter.isEnabled();
    }


    public boolean startDiscovery(){
        return adapter.startDiscovery();
    }

    public  void setBluetoothStatus(boolean b, Activity context) {
        if (isBluetoothOn()) {
            if (b) {
                Toast.makeText(context, "Bluetooth is already On", Toast.LENGTH_SHORT).show();
            } else {
                adapter.disable();
            }
        } else {
            if (b) {
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                context.startActivityForResult(enableBtIntent, 1);
            } else {
                Toast.makeText(context, "Bluetooth is Already Off", Toast.LENGTH_SHORT).show();
            }
        }
    }


    public  List<BluetoothDevice> getAllPairedDevices() {
        List<BluetoothDevice> devices = new ArrayList<>();

        for (BluetoothDevice device : adapter.getBondedDevices()) {
            devices.add(device);
        }

        return devices;
    }


}
