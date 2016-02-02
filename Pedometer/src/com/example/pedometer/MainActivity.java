package com.example.pedometer;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements SensorEventListener{

	
	private SensorManager sensorManager;
	private Sensor StepCounter;
	private Sensor StepDetector;
	
	private TextView stepSensorText;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        stepSensorText=(TextView) findViewById(R.id.step_sensor);
        
        sensorManager=(SensorManager) getSystemService(Context.SENSOR_SERVICE);
        StepCounter=sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
//        if(StepCounter==null) stepSensorText.setText("No Step Counter");
        StepDetector = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);
//        if(StepDetector==null) stepSensorText.setText("No Step Detector");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        
        Button button=(Button)findViewById(R.id.reset);
        button.setOnClickListener(new OnClickListener() {
			
        	@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				stepSensorText.setText("0");
			}
		});
        return true;
    }

    protected void onResume(){
    	super.onResume();
    	sensorManager.registerListener(this, StepCounter, SensorManager.SENSOR_DELAY_FASTEST);
    	sensorManager.registerListener(this, StepDetector, SensorManager.SENSOR_DELAY_FASTEST);
    }

    protected void onStop()
    {
    	super.onStop();
    	stepSensorText.setText("0");
    	sensorManager.unregisterListener(this, StepCounter);
    	sensorManager.unregisterListener(this, StepDetector);
    }
    
	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onSensorChanged(SensorEvent event) {
		Sensor sensor =event.sensor;
		float[] values=event.values;
		int value=-1;
		if(values.length>0)
		{
			value=(int) values[0];
		}
		if(sensor.getType()==Sensor.TYPE_STEP_COUNTER)
		{
			stepSensorText.setText(String.valueOf(value));
		}
		else if(sensor.getType()==Sensor.TYPE_STEP_DETECTOR)
		{
			stepSensorText.setText(String.valueOf(value));
		}
	}
    
}
