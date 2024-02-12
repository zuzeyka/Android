package com.zuzeyka.test;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class NotificationReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getStringExtra("action");
        Toast.makeText(context, "Action clicked: " + action, Toast.LENGTH_SHORT).show();
    }
}

