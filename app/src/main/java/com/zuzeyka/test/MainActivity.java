package com.zuzeyka.test;

import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private CameraManager cameraManager;
    private String cameraId;
    private boolean isFlashOn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);

        try {
            cameraId = cameraManager.getCameraIdList()[0]; // получаем id первой камеры (обычно это задняя камера)
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }

        Button toggleButton = findViewById(R.id.toggleButton);
        toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleFlash();
            }
        });
    }

    private void toggleFlash() {
        try {
            if (isFlashOn) {
                turnOffFlash();
            } else {
                turnOnFlash();
            }
            isFlashOn = !isFlashOn;
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    private void turnOnFlash() throws CameraAccessException {
        cameraManager.setTorchMode(cameraId, true);
    }

    private void turnOffFlash() throws CameraAccessException {
        cameraManager.setTorchMode(cameraId, false);
    }

    public void updateTotalPrice() {
    }
}
