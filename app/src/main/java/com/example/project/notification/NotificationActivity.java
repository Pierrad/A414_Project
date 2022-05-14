package com.example.project.notification;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.project.LoginActivity;
import com.example.project.MainActivity;
import com.example.project.R;

public class NotificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification_activity);
        Intent intent = new Intent(this, MainActivity.class);
        this.startActivity(intent);
    }
}
