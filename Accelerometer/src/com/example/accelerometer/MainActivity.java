package com.example.accelerometer;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity implements SensorEventListener{
	private SensorManager sensorManager;
	private TextView maxX, maxY, maxZ,currentX,currentY,currentZ;
	private Sensor accelerometer;
	private float deltaX=0,lastX,lastY,lastZ;
	private float deltaY=0,deltaXMax=0,deltaYMax=0,deltaZMax=0;
	private float deltaZ=0;
	public Vibrator vib;
	private float vibratorThreshold=0;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        maxX=(TextView) findViewById(R.id.max_acceleration_x_axis);
        maxY=(TextView) findViewById(R.id.max_acceleration_y_axis);
        maxZ=(TextView) findViewById(R.id.max_acceleration_z_axis);
        
        currentX=(TextView) findViewById(R.id.current_x);
        currentY=(TextView) findViewById(R.id.current_y);
        currentZ=(TextView) findViewById(R.id.current_z);
        
        sensorManager=(SensorManager)getSystemService(Context.SENSOR_SERVICE);
        accelerometer=sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if(accelerometer==null){}
        else
        {
        	sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        	vibratorThreshold=accelerometer.getMaximumRange()/2;
        }
        vib=(Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
    }
    
    protected void onResume() {
    	super.onResume();
    	sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
	}

    protected void onPause() {
    	super.onPause();
    	sensorManager.unregisterListener(this);
		
	}
    
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
	}
    
    @Override
    public void onSensorChanged(SensorEvent event) {
		currentX.setText("0.0");
		currentY.setText("0.0");
		currentZ.setText("0.0");
		
		currentX.setText(String.valueOf(deltaX));
		currentY.setText(String.valueOf(deltaY));
		currentZ.setText(String.valueOf(deltaZ));
		
		displayMaxValue();
		
		deltaX=Math.abs(lastX-event.values[0]);
		deltaY=Math.abs(lastY-event.values[0]);
		deltaZ=Math.abs(lastZ-event.values[0]);
		
		if(deltaX < 2) deltaX=0;
		if(deltaY<2) deltaY=0;
		if((deltaX > vibratorThreshold) ||(deltaY > vibratorThreshold) || (deltaZ > vibratorThreshold))
		{
			vib.vibrate(50);
		}	
	}
    
    public void displayMaxValue(){
    	if(deltaX >deltaXMax){
    		deltaXMax=deltaX;
    		maxX.setText(String.valueOf(deltaX));
    	}
    	if(deltaY >deltaYMax){
    		deltaYMax=deltaY;
    		maxY.setText(String.valueOf(deltaY));
    	}
    	if(deltaZ >deltaZMax){
    		deltaZMax=deltaZ;
    		maxZ.setText(String.valueOf(deltaZ));
    	}
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
