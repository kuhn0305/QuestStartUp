package com.tektonspace.queststartup;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

public class StartUpReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.v("StartUpTag", "Start Up OnReceive");
        Intent activityIntent = new Intent(context, StartupActivity.class);
        activityIntent.setAction(Intent.ACTION_MAIN);
        activityIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        activityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        context.startActivity(activityIntent);
    }
}