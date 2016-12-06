package com.example.android.bluetoothtest;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private Button Btn_On,Btn_Off,Hint_Visible,BluetoothList;
    private BluetoothAdapter BA;
    private Set<BluetoothDevice> paireDevices;
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Btn_On = (Button)findViewById(R.id.BtnOn);
        Btn_Off = (Button)findViewById(R.id.BtnOff);
        Hint_Visible = (Button)findViewById(R.id.BtnVisible);
        lv = (ListView)findViewById(R.id.listView1);
        BA = BluetoothAdapter.getDefaultAdapter();
    }

    public void Btn_Trun_On(View view){
        if(!BA.isEnabled()){
            Intent turnOn = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(turnOn,0);
            Toast.makeText(getApplicationContext(),"蓝牙开启",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(getApplicationContext(),"蓝牙已经开启",Toast.LENGTH_SHORT).show();
        }
    }

    public void Btn_Trun_Off(View view){
        BA.disable();
        Toast.makeText(getApplicationContext(),"蓝牙关闭",Toast.LENGTH_SHORT).show();
    }

    public void setList(View view){
        paireDevices = BA.getBondedDevices();

        ArrayList list = new ArrayList();
        for(BluetoothDevice bt :paireDevices)
            list.add(bt.getName());

        Toast.makeText(getApplicationContext(),"显示蓝牙l链接",Toast.LENGTH_SHORT).show();

        final ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,list);
        lv.setAdapter(adapter);
    }

    public void setVisible(View view){
        Intent getVisible = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        startActivityForResult(getVisible,0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
