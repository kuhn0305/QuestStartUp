package com.tektonspace.queststartup;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class StartupActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.v("StartUpTag", "MainActivity OnCreate");

        if(getApplicationContext().checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 0x3);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PackageParser packageParser = new PackageParser();
        String targetPackageName = packageParser.getPackageName();

        TextView packageTextView = findViewById(R.id.tv_package);
        packageTextView.setText(targetPackageName);

        Button startButton = findViewById(R.id.btn_start);
        startButton.setOnClickListener(view -> StartPackage(targetPackageName));

        StartPackage(targetPackageName);
    }

    private void StartPackage(String packageName) {
        if (!Settings.canDrawOverlays(getApplicationContext())) {
            startActivity(new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION));
        } else {
            Intent launchIntent = getPackageManager().getLaunchIntentForPackage(packageName);
            if (launchIntent != null) {
                startActivity(launchIntent);
            }
        }
    }
}