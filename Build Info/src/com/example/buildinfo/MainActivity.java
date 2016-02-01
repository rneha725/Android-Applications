package com.example.buildinfo;

import android.os.Build;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fill();
    }
    public void fill()
    {
    	TextView buildRelease=(TextView) findViewById(R.id.build_release);
    	buildRelease.setText(Build.VERSION.RELEASE);
    	
    	TextView buildBoard=(TextView) findViewById(R.id.build_board);
    	buildBoard.setText(Build.BOARD);
    	
    	TextView buildBrand=(TextView) findViewById(R.id.build_brand);
    	buildBrand.setText(Build.BRAND);
    	
    	TextView buildDevice=(TextView) findViewById(R.id.build_device);
    	buildDevice.setText(Build.DEVICE);
    	
    	TextView buildDisplay=(TextView) findViewById(R.id.build_display);
    	buildDisplay.setText(Build.DISPLAY);
    	
    	TextView buildFingerprint=(TextView) findViewById(R.id.build_fingerprint);
    	buildFingerprint.setText(Build.FINGERPRINT);
    	
    	TextView buildHardware=(TextView) findViewById(R.id.build_hardware);
    	buildHardware.setText(Build.HARDWARE);
    	
    	TextView buildManufacturer=(TextView) findViewById(R.id.build_manufacturer);
    	buildManufacturer.setText(Build.MANUFACTURER);

    	TextView buildModel=(TextView) findViewById(R.id.build_model);
    	buildModel.setText(Build.MODEL);
    	
    	TextView buildProduct=(TextView) findViewById(R.id.build_product);
    	buildProduct.setText(Build.PRODUCT);

    	TextView buildType=(TextView) findViewById(R.id.build_type);
    	buildType.setText(Build.TYPE);

    	TextView buildBootloader=(TextView) findViewById(R.id.build_bootloader);
    	buildBootloader.setText(Build.BOOTLOADER);

    	TextView buildSDK=(TextView) findViewById(R.id.build_sdk);
    	buildSDK.setText(String.valueOf(Build.VERSION.SDK_INT));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        Button batteryInfo=(Button) findViewById(R.id.battery_info);
        
        batteryInfo.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View view) {
				// TODO Auto-generated method stub
				Intent intent=getPackageManager().getLaunchIntentForPackage("com.example.batteryinfo");
				if(intent==null){}
				else
				startActivity(intent);
			}
        	
        });
        return true;
    }
    
}
