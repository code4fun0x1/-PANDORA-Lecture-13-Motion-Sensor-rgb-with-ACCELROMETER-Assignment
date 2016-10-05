package com.ahdollars.crazyeights.accellrometercolorchanger;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    
    ArrayList<Float> f=new ArrayList<>();
    public static final String TAG="SENSOR";
    TextView r,g,b;
    SensorManager sm;

    FrameLayout main_activity;
    float max= (float) 19.5;
    SensorEventListener eventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       sm= (SensorManager) getSystemService(SENSOR_SERVICE);
        main_activity=(FrameLayout)findViewById(R.id.activity_main);
        r=(TextView)findViewById(R.id.tvR);
        g=(TextView)findViewById(R.id.tvG);
        b=(TextView)findViewById(R.id.tvB);


         eventListener=new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {

               Log.d(TAG, "onSensorChanged: "+max);
             //   f.add(event.values[0]);


                int x= (int) ((event.values[0]/max)*255);
                int y= (int) ((event.values[1]/max)*255);
                int z= (int) ((event.values[2]/max)*255)-125;
            //    Log.d(TAG, "onSensorChanged: "+x+"    "+y+"    "+b);
               r.setText(String.valueOf(x));
                g.setText(String.valueOf(y));
                b.setText(String.valueOf(z));
                main_activity.setBackgroundColor(Color.rgb(x,y,z));

            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };

        sm.registerListener(eventListener,sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),SensorManager.SENSOR_DELAY_NORMAL,SensorManager.SENSOR_DELAY_NORMAL);
    }
    
    public float getAcceleration(float... args){
        return (float) Math.sqrt(args[0]*args[0]+args[1]*args[1]+args[2]*args[2]);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sm.unregisterListener(eventListener);
    }
}
