package com.example.project.helpers;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class JSONHandler {
    private String filePath;
    private Context context;

    public JSONHandler(String fp, Context c) {
        this.filePath = fp;
        this.context = c;
    }

    public String getJSON() {
        String JSON = "";
        try {
            InputStream JSONIS = context.getAssets().open(filePath);
            int JSONSize = JSONIS.available();
            byte[] buffer = new byte[JSONSize];
            JSONIS.read(buffer);
            JSONIS.close();
            JSON = new String(buffer, StandardCharsets.UTF_8);
            return JSON;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
