package com.bluetooth.bluetoothsample;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Switch aSwitch;
    Button searchDevices;
    RecyclerView recyclerView;
    BluetoothController controller;
    List<BluetoothDevice> bluetoothDevices;
    BluetoothAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initViews();

        adapter = BluetoothAdapter.getDefaultAdapter();


        controller = new BluetoothController();

        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                controller.setBluetoothStatus(b, MainActivity.this);
            }
        });

        bluetoothDevices = new ArrayList<>();

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        searchDevices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (adapter.startDiscovery()) {
                    Toast.makeText(MainActivity.this, "Started!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Failed!", Toast.LENGTH_SHORT).show();
                }
            }
        });


        IntentFilter intentFilter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        registerReceiver(broadcastReceiver, intentFilter);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(broadcastReceiver);
    }

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            if (intent.getAction().equals(BluetoothDevice.ACTION_FOUND)) {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
               // Log.e("device", device.getName());
                bluetoothDevices.add(device);
                DeviceRecyclerAdpater adpater = new DeviceRecyclerAdpater(bluetoothDevices, MainActivity.this);
                recyclerView.setAdapter(adpater);
            }

        }
    };


    private void initViews() {
        recyclerView = findViewById(R.id.device_list_recycler);
        aSwitch = findViewById(R.id.bt_switch);
        searchDevices = findViewById(R.id.bt_search_device);
    }
}
