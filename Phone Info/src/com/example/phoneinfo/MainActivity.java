package com.example.phoneinfo;

import android.os.BatteryManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import com.example.batteryinfo.R;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fill();
    }
    
    private String getPlugTypeString(int plugged)
    {
    	String plugType="Unknown";
    	switch(plugged)
    	{
    	case BatteryManager.BATTERY_PLUGGED_AC:
    		plugType="AC";
    		break;
    	case BatteryManager.BATTERY_PLUGGED_USB:
    		plugType="USB";
    		break;
    	}
    	return plugType;
    }
    
    private String getHealthString(int health)
    {
    	String healthString="Unknown";
    	
    	switch(health)
    	{
    	case BatteryManager.BATTERY_HEALTH_DEAD:
    		healthString="Dead";
    		break;
    	case BatteryManager.BATTERY_HEALTH_GOOD:
    		healthString="Good";
    		break;
    	case BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE:
    		healthString="Over Voltage";
    		break;
    	case BatteryManager.BATTERY_HEALTH_OVERHEAT:
    		healthString="Over Heat";
    		break;
    	case BatteryManager.BATTERY_HEALTH_UNKNOWN:
    		healthString="Unknown";
    		break;
    	case BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE:
    		healthString="Failure";
    		break;
    	}
    	return healthString;
    }
    
    private String getStatusString(int status)
    {
    	String statusString="Unknown";
    	
    	switch(status)
    	{
    	case BatteryManager.BATTERY_STATUS_CHARGING:
    		statusString="Charging";
    		break;
    	case BatteryManager.BATTERY_STATUS_DISCHARGING:
    		statusString="Disharging";
    		break;
    	case BatteryManager.BATTERY_STATUS_FULL:
    		statusString="Full";
    		break;
    	case BatteryManager.BATTERY_STATUS_NOT_CHARGING:
    		statusString="Not Charging";
    		break;
    	case BatteryManager.BATTERY_STATUS_UNKNOWN:
    		statusString="Unknnown";
    		break;
    	}
    	return statusString;
    }
    
    private BroadcastReceiver battery_receiver=new BroadcastReceiver() {
		
		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			boolean isPresent=intent.getBooleanExtra("present", false);
			String technology=intent.getStringExtra("technology");
			int plugged=intent.getIntExtra("plugged", -1);
			int scale=intent.getIntExtra("scale", -1);
			int health=intent.getIntExtra("health", 0);
			int status=intent.getIntExtra("status", 0);
			int rawlevel=intent.getIntExtra("level", -1);
			int voltage=intent.getIntExtra("voltage", 0);
			float temperature=intent.getIntExtra("temperature", 0);
			temperature=temperature/10;
			int level=0;
			
//			Bundle bundle=intent.getExtras();
			
//			Log.i("BatteryLevel", bundle.toString());
			if(isPresent)
			{
				if(rawlevel>=0  && scale > 0)
				{
					level=(rawlevel*100)/scale;
				}
				TextView battery_level=(TextView) findViewById(R.id.battery_level);
				battery_level.setText(String.valueOf(level)+"%");
				TextView battery_technology=(TextView) findViewById(R.id.battery_technology);
				battery_technology.setText(String.valueOf(technology));
				TextView battery_plugged=(TextView) findViewById(R.id.battery_plugged);
				battery_plugged.setText(getPlugTypeString(plugged));
				TextView battery_health=(TextView) findViewById(R.id.battery_health);
				battery_health.setText(getHealthString(health));
				TextView battery_status=(TextView) findViewById(R.id.battery_status);
				battery_status.setText(getStatusString(status));
				
				TextView battery_voltage=(TextView) findViewById(R.id.battery_voltage);
				battery_voltage.setText(String.valueOf(voltage)+" mV");
				
				TextView battery_temperature=(TextView) findViewById(R.id.battery_temperature);
				battery_temperature.setText(String.valueOf(temperature)+" Celsius");
			}	
		}
	};
    public void fill()
    {
    	//TextView batteryType=(TextView) findViewById(R.id.battery_type);
//    	batteryType.setText(Build.BOARD);
    	IntentFilter filter=new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
//    	BroadcastReceiver battery_receiver;
		registerReceiver(battery_receiver, filter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        
        Button buildInfo=(Button) findViewById(R.id.build_info);
        
        buildInfo.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {

				Intent intent=getPackageManager().getLaunchIntentForPackage("com.example.buildinfo");
				startActivity(intent);
			}
		});
        return true;
    }
    
}
