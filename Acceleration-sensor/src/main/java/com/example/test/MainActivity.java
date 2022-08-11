package com.example.test;

import androidx.appcompat.app.AppCompatActivity;
import android.hardware.SensorManager;
import android.hardware.SensorEventListener;
import android.hardware.Sensor;
import android.os.Bundle;
import android.widget.TextView;
import android.content.Context;
import android.hardware.SensorEvent;

public class MainActivity extends AppCompatActivity {

    private SensorManager mySensorManager;
    private SensorEventListener gyroListener;
    private SensorEventListener magnetListener;
    private Sensor myGyroscope;
    private Sensor magnetSensor;

    private double roll;
    private double pitch;
    private double yaw;

    private double timestamp = 0.0;
    private double dt;

    private double rad_to_dgr = 180 / Math.PI;
    private static final float NS2S = 1.0f/1000000000.0f;
    TextView zRot, xRot, yRot, magnet1, magnet2, magnet3;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        zRot = (TextView)findViewById(R.id.zRot);
        xRot = (TextView)findViewById(R.id.xRot);
        yRot = (TextView)findViewById(R.id.yRot);
        magnet1 = (TextView)findViewById(R.id.magnet1);
        magnet2 = (TextView)findViewById(R.id.magnet2);
        magnet3 = (TextView)findViewById(R.id.magnet3);

        mySensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        myGyroscope = mySensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        magnetSensor = mySensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        magnetListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                magnet1.setText("magnet1 : " + String.valueOf(sensorEvent.values[0]));
                magnet2.setText("magnet2 : " + String.valueOf(sensorEvent.values[1]));
                magnet3.setText("magnet3 : " + String.valueOf(sensorEvent.values[2]));
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };
        gyroListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                double gyroX = sensorEvent.values[0];
                double gyroY = sensorEvent.values[1];
                double gyroZ = sensorEvent.values[2];

                dt = (sensorEvent.timestamp - timestamp) * NS2S;
                timestamp = sensorEvent.timestamp;

                if(dt-timestamp*NS2S != 0){
                    pitch = pitch + gyroY * dt;
                    roll = roll + gyroX * dt;
                    yaw = yaw + gyroZ * dt;

                    xRot.setText("x-rot : " + String.format("%.1f", roll+rad_to_dgr));
                    yRot.setText("y-rot : " + String.format("%.1f", pitch+rad_to_dgr));
                    zRot.setText("z-rot : " + String.format("%.1f", yaw+rad_to_dgr));
                }
            };

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };
    }
    protected void onResume(){
        super.onResume();
        mySensorManager.registerListener(gyroListener, myGyroscope, SensorManager.SENSOR_DELAY_UI);
        mySensorManager.registerListener(magnetListener, magnetSensor, mySensorManager.SENSOR_DELAY_UI);
    }
    protected void onPause(){
        super.onPause();
        mySensorManager.unregisterListener(gyroListener);
    }
    protected void onStop(){
        super.onStop();
    }

}
