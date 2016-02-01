package com.example.sensorinfo;

import java.util.ArrayList;
import java.util.List;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class MainActivity extends Activity {
	private ListView sensorListView;
	private Sensor accelerometer;
    private SensorManager sensorManager;
    private List<Sensor> sensorList=null;
    private List<String> sensorListName=new ArrayList<String>();
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        sensorListView=(ListView) findViewById(R.id.sensor_list);
        sensorManager=(SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensorList = sensorManager.getSensorList(Sensor.TYPE_ALL);
        
        for(Sensor s: sensorList)
        {
        	sensorListName.add(s.getName() + "\nVendor: "+s.getVendor()+"\nVersion: "+s.getVersion()+"\nType: "+s.getType()+"\nPower: "+s.getPower()+"\n");
        }
        sensorListView.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,sensorListName));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
