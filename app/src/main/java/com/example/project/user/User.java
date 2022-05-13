package com.example.project.user;

import android.content.Context;
import android.content.SharedPreferences;

public class User {
    private static User INSTANCE;
    String pseudo;
    int avatar;
    Context context;

    private User(Context c) {
        this.context = c;
        initWithSharedParameters();
    }

    public static User getInstance(Context c) {
        if (INSTANCE == null) {
            INSTANCE = new User(c);
        }
        return INSTANCE;
    }

    public void initWithSharedParameters() {
        SharedPreferences sh = context.getSharedPreferences("PersonalInfo", Context.MODE_PRIVATE);
        String pseudo = sh.getString("pseudo", "");
        int avatar = sh.getInt("avatar", 0);
        setPseudo(pseudo);
        setAvatar(avatar);
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }
}
