package com.example.myapplication;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.telephony.TelephonyManager;
import android.widget.Toast;
public class backgroundService extends Service {
    IncomingCallReceiver incomingCallReceiver=null;
    public static boolean isRunning = false;
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "Service started by user.", Toast.LENGTH_LONG).show();
        IntentFilter intentFilter = null;
        try {
            intentFilter = new IntentFilter(TelephonyManager.EXTRA_STATE_RINGING,TelephonyManager.EXTRA_STATE_OFFHOOK);
        } catch (IntentFilter.MalformedMimeTypeException e) {
            e.printStackTrace();
        }
        incomingCallReceiver= new IncomingCallReceiver();
        registerReceiver(incomingCallReceiver, intentFilter);
        return START_STICKY;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(incomingCallReceiver);
        Toast.makeText(this, "Service destroyed by user.", Toast.LENGTH_LONG).show();
    }
}