package com.example.sensores;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private SensorManager sensorManager;
    private Sensor accelerometerSensor;
    private SensorEventListener accelerometerEventListener;
    private TextView tvValues;
    private long x;
    private long y;
    private long z;

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Enlaza el ImageView desde el layout
        imageView = findViewById(R.id.imageViewOrientation);
        tvValues = findViewById(R.id.tv_values);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        accelerometerEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                x = Math.round(Math.toDegrees(sensorEvent.values[0]));
                y = Math.round(Math.toDegrees(sensorEvent.values[1]));
                z = Math.round(Math.toDegrees(sensorEvent.values[2]));

                if (x > 200) {
                    // Cambia la imagen según la orientación
                    imageView.setImageResource(R.drawable.abajo);
                } else if (x < -200) {
                    imageView.setImageResource(R.drawable.arriba);
                } else if (y > 200) {
                    imageView.setImageResource(R.drawable.derecha);
                } else if (y < -200) {
                    imageView.setImageResource(R.drawable.izquierda);
                } else {
                    imageView.setImageResource(R.drawable.stop);
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {
                // Implementación de onAccuracyChanged si es necesario
            }
        };

        sensorManager.registerListener(accelerometerEventListener, accelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }
}
