package com.bluetooth.bluetoothsample;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class DeviceRecyclerAdpater extends RecyclerView.Adapter<DeviceRecyclerAdpater.DeviceHolder> {

    List<BluetoothDevice> bluetoothDevices;
    Context context;


    public DeviceRecyclerAdpater(List<BluetoothDevice> bluetoothDevices, Context context) {
        this.bluetoothDevices = bluetoothDevices;
        this.context = context;
    }

    @NonNull
    @Override
    public DeviceHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.device_list_row, parent, false);
        return new DeviceHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DeviceHolder holder, int position) {

        holder.deviceName.setText(bluetoothDevices.get(position).getName());
        holder.deviceMacAddress.setText(bluetoothDevices.get(position).getAddress());

    }

    @Override
    public int getItemCount() {
        return bluetoothDevices.size();
    }


    class DeviceHolder extends RecyclerView.ViewHolder{
        TextView deviceName, deviceMacAddress;

        public DeviceHolder(View itemView) {
            super(itemView);
            deviceMacAddress = itemView.findViewById(R.id.mac_address);
            deviceName = itemView.findViewById(R.id.device_name);
        }
    }
}
