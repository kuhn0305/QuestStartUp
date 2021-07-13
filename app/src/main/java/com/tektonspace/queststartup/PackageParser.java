package com.tektonspace.queststartup;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

class PackageParser {

    private String filePath = "";
    private final String jsonFileName = "packagename.json";
    private final String packageHeaderName = "package";

    public PackageParser(String filePath)
    {
        this.filePath = filePath;
    }

    public String getPackageName() {
        try {
            Log.v("StartUpTag", "Read Package Name");
            File file = new File(filePath, jsonFileName);
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

    public void SetPackageName(String packageName)
    {
        try {
            Log.v("StartUpTag", "Read Package Name");

            File file = new File(filePath, jsonFileName);
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            JSONObject jsonObject = new JSONObject();
            jsonObject.put(packageHeaderName, packageName);

            bufferedWriter.write(jsonObject.toString());

            bufferedWriter.flush();
            bufferedWriter.close();

        } catch (IOException | JSONException e) {
            e.printStackTrace();
            Log.v("StartUpTag", "Write Package Name ; " + e.getMessage());
        }

    }
}
