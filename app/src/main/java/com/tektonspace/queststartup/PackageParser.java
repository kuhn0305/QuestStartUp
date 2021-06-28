package com.tektonspace.queststartup;

import android.os.Build;
import android.os.Environment;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import androidx.annotation.RequiresApi;

class PackageParser {

    private final String jsonFileName = "packagename.json";
    private final String packageHeaderName = "package";

    public String getPackageName() {
        try {
            Log.v("StartUpTag", "Read Package Name");
            File file = new File(Environment.getExternalStorageDirectory(), jsonFileName);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            StringBuilder stringBuilder = new StringBuilder();
            String line = bufferedReader.readLine();

            while (line != null) {
                stringBuilder.append(line).append("\n");
                line = bufferedReader.readLine();
            }

            String jsonData = stringBuilder.toString();
            JSONObject jsonObject = new JSONObject(jsonData);

            return jsonObject.getString(packageHeaderName);
        } catch (JSONException | IOException e) {
            e.printStackTrace();
            Log.v("StartUpTag", "Read Package Name ; " + e.getMessage());
        }

        return "";
    }
}
