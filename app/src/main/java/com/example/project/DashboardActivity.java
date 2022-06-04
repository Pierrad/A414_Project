package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.project.english.EnglishDashboardActivity;
import com.example.project.french.FrenchDashboardActivity;
import com.example.project.history.HistoryDashboardActivity;
import com.example.project.maths.MathsDashboardActivity;
import com.example.project.user.User;

import java.util.Objects;

public class DashboardActivity extends AppCompatActivity {
    private User user = User.getInstance(this);
    private ImageView avatar;
    private TextView pseudo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Dashboard");
        registerElements();
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerElements(); // Update level
    }

    @Override
    public void onBackPressed() {
        // super.onBackPressed();
        // Not calling **super**, disables back button in current screen.
    }

    public void registerElements() {
        avatar = findViewById(R.id.avatar);
        pseudo = findViewById(R.id.dashboardTitle);

        avatar.setImageResource(user.getAvatar());
        pseudo.setText(getString(R.string.pseudoLevel, user.getPseudo(), "" + user.getLevel()));
    }

    public void redirectToEnglish(View v) {
        Intent intent = new Intent(this, EnglishDashboardActivity.class);
        this.startActivity(intent);
    }

    public void redirectToFrench(View v) {
        Intent intent = new Intent(this, FrenchDashboardActivity.class);
        this.startActivity(intent);
    }

    public void redirectToHistory(View v) {
        Intent intent = new Intent(this, HistoryDashboardActivity.class);
        this.startActivity(intent);
    }

    public void redirectToMaths(View v) {
        Intent intent = new Intent(this, MathsDashboardActivity.class);
        this.startActivity(intent);
    }

}