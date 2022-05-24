package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.project.user.User;

import java.util.ArrayList;
import java.util.Objects;

public class LoginActivity extends AppCompatActivity {
    private ImageView avatar;
    private ArrayList<Integer> avatarList = new ArrayList<>();
    private int avatarIndex = 0;

    private EditText pseudoInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Bienvenue 😁");
        initAvatarList();
        registerElements();
    }

    public void initAvatarList() {
        avatarList.add(R.mipmap.picture1);
        avatarList.add(R.mipmap.picture2);
        avatarList.add(R.mipmap.picture3);
        avatarList.add(R.mipmap.picture4);
        avatarList.add(R.mipmap.picture5);
    }

    public void registerElements() {
        avatar = findViewById(R.id.avatarImg);
        avatar.setImageResource(R.mipmap.picture1);
        pseudoInput = findViewById(R.id.pseudoInput);
    }

    public void swipeLeft(View v) {
        if (avatarIndex == 0) {
            avatarIndex = avatarList.size() - 1;
        } else {
            avatarIndex -= 1;
        }
        avatar.setImageResource(avatarList.get(avatarIndex));
    }

    public void swipeRight(View v) {
        if (avatarIndex == avatarList.size() - 1) {
            avatarIndex = 0;
        } else {
            avatarIndex += 1;
        }
        avatar.setImageResource(avatarList.get(avatarIndex));
    }

    public void submit(View v) {
        String pseudo = pseudoInput.getText().toString();
        if (pseudo.equals("")) {
            Toast.makeText(this, "N'oubliez pas de mettre un pseudo ;)", Toast.LENGTH_SHORT).show();
        } else {
            SharedPreferences sharedPreferences = getSharedPreferences("PersonalInfo", MODE_PRIVATE);
            SharedPreferences.Editor myEdit = sharedPreferences.edit();
            myEdit.putString("pseudo", pseudo);
            myEdit.putInt("avatar", avatarList.get(avatarIndex));
            myEdit.putInt("experience", 0);
            if (myEdit.commit()) {
                User.getInstance(this);
                Intent intent = new Intent(this, DashboardActivity.class);
                this.startActivity(intent);
            }
        }
    }
}