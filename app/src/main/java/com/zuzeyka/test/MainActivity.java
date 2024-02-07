package com.zuzeyka.test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private int clickCounter = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        button.setOnClickListener(v -> {
            if (clickCounter < 20){
                button.setText("Clicks:" + clickCounter++);
                int newWidth = button.getWidth() + 10;
                button.setWidth(newWidth);
                long timeUntilNextMonday = calculateTimeUntilNextMonday();
                String formattedTime = formatTime(timeUntilNextMonday);
                setTitle("Time until next Monday lesson: " + formattedTime);
                Toast.makeText(this, "Days until next Monday lesson: " + formattedTime, Toast.LENGTH_SHORT).show();
            }
            else{
                button.setEnabled(false);
            }
        });
    }

    private long calculateTimeUntilNextMonday() {
        Calendar calendar = Calendar.getInstance();
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        int daysUntilMonday = Calendar.MONDAY - dayOfWeek;
        if (daysUntilMonday <= 0) {
            daysUntilMonday += 7;
        }
        return TimeUnit.DAYS.toMillis(daysUntilMonday);
    }

    private String formatTime(long milliseconds) {
        long days = TimeUnit.MILLISECONDS.toDays(milliseconds);
        return String.valueOf(days);
    }

}