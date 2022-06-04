package com.example.project.user;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

public class User {
    private static User INSTANCE;
    Context context;

    String pseudo;
    int avatar;
    int experience;
    int level;

    private User(Context c) {
        this.context = c;
        initWithSharedParameters();
        setLevel();
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
        int experience = sh.getInt("experience", 0);
        setPseudo(pseudo);
        setAvatar(avatar);
        setExperience(experience);
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

    public int getExperience() { return experience; }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel() {
        if (experience <= 10) {
            level = 0;
        } else if (experience <= 20) {
            level = 1;
        } else if (experience <= 40) {
            level = 2;
        } else if (experience <= 80) {
            level = 3;
        } else if (experience > 80) {
            level = 4;
        }
    }

    public void editSharedPreferences() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("PersonalInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        myEdit.putInt("experience", experience);
        myEdit.apply();
    }

    public void addExperience(float resultOfActivityInPercent) {
        if (resultOfActivityInPercent <= 25) {
            experience += 1;
            Toast.makeText(context, "+1 point d'exp", Toast.LENGTH_SHORT).show();
        } else if (resultOfActivityInPercent > 25 && resultOfActivityInPercent <= 50) {
            experience += 2;
            Toast.makeText(context, "+2 points d'exp", Toast.LENGTH_SHORT).show();
        } else if (resultOfActivityInPercent > 50 && resultOfActivityInPercent <= 80) {
            experience += 5;
            Toast.makeText(context, "+5 points d'exp", Toast.LENGTH_SHORT).show();
        } else if (resultOfActivityInPercent > 80) {
            experience += 10;
            Toast.makeText(context, "+10 points d'exp", Toast.LENGTH_SHORT).show();
        }
        setLevel();
        editSharedPreferences();
    }
}
