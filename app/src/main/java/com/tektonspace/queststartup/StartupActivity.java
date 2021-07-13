package com.tektonspace.queststartup;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class StartupActivity extends AppCompatActivity {
    String targetPackageName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.v("StartUpTag", "MainActivity OnCreate");

        if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 0x3);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PackageParser packageParser = new PackageParser(getDir("data",Context.MODE_PRIVATE).getPath());
        targetPackageName = packageParser.getPackageName();

        TextView packageTextView = findViewById(R.id.tv_package);
        packageTextView.setText(targetPackageName);

        Button startButton = findViewById(R.id.btn_start);
        startButton.setOnClickListener(view -> StartPackage(targetPackageName));

        List<ApplicationInfo> applicationInfoList = getPackageManager().getInstalledApplications(PackageManager.GET_META_DATA);
        List<String> packageNameList = new ArrayList<>();
        List<String> labelNameList = new ArrayList<>();
        for (ApplicationInfo info : applicationInfoList) {
            if (getPackageManager().getLaunchIntentForPackage(info.packageName) != null && !getPackageManager().getLaunchIntentForPackage(info.packageName).toString().equals("")) {
                packageNameList.add(info.packageName);
                labelNameList.add((String) info.loadLabel(getPackageManager()));
            }
        }

        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.spinner_item, labelNameList);
        spinner.setAdapter(arrayAdapter);

        Button applyButton = findViewById(R.id.btn_apply);
        applyButton.setOnClickListener(view -> {
                    targetPackageName = packageNameList.get(spinner.getSelectedItemPosition());
                    packageParser.SetPackageName(targetPackageName);
                    packageTextView.setText(targetPackageName);
                }
        );

        String path = this.getDir("tmp", Context.MODE_PRIVATE).getPath();
        if(getIntent().getBooleanExtra("Boot",false))
        {
            Log.v("StartUpTag", "MainActivity Boot Extra");
            StartPackage(targetPackageName);
        }
        else
        {
            Log.v("StartUpTag", "MainActivity No Boot Extra");
        }
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
