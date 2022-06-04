package com.example.project.english.oralcomprehension;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.media.MediaPlayer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.project.R;
import com.example.project.english.quizz.EnglishResultActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class OralComprehensionActivity extends AppCompatActivity {
    MediaPlayer mp;
    Handler handler = new Handler();
    Runnable runnable;
    ArrayList<AudioEntryMultiple> audioEntries = new ArrayList<AudioEntryMultiple>();
    String selected = "";
    int audioIndex, score = 0;
    int audio;

    TextView title, playerPosition, playerDuration;
    SeekBar seekBar;
    ImageView btnPause, btnPlay;
    Button btn1, btn2, btn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.english_oralcomprehension);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Anglais - Compréhension Orale");
        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.english)));

        title = findViewById(R.id.englishOralTitle);
        playerPosition = findViewById(R.id.player_position);
        playerDuration = findViewById(R.id.player_duration);
        seekBar = findViewById(R.id.seek_bar);
        btnPause = findViewById(R.id.btnPause);
        btnPlay = findViewById(R.id.btnPlay);
        btn1 = findViewById(R.id.englishOralMultipleAnswer1);
        btn2 = findViewById(R.id.englishOralMultipleAnswer2);
        btn3 = findViewById(R.id.englishOralMultipleAnswer3);

        ArrayList<Button> buttons = new ArrayList<Button>();
        buttons.add(btn1);
        buttons.add(btn2);
        buttons.add(btn3);
        for (Button btn : buttons) {
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    btn1.setBackgroundColor(getResources().getColor(R.color.english_light));
                    btn2.setBackgroundColor(getResources().getColor(R.color.english_light));
                    btn3.setBackgroundColor(getResources().getColor(R.color.english_light));
                    selected = btn.getText().toString();
                    btn.setBackgroundColor(getResources().getColor(R.color.english));
                }
            });
        }

        audioEntries = buildAudioEntries();
        title.setText(getString(R.string.quizz_entry_title, " " + 1, " " + audioEntries.size()));
        AudioEntryMultiple currentAudioEntry = audioEntries.get(0);
        audio = currentAudioEntry.getAudioId();
        mp = MediaPlayer.create(this, audio);
        btn1.setText(currentAudioEntry.getPossibleAnswers().get(0).toString());
        btn2.setText(currentAudioEntry.getPossibleAnswers().get(1).toString());
        btn3.setText(currentAudioEntry.getPossibleAnswers().get(2).toString());

        System.out.println(currentAudioEntry.getAnswer());

        runnable = new Runnable() {
            @Override
            public void run() {
                seekBar.setProgress(mp.getCurrentPosition());
                handler.postDelayed(this,500);
            }
        };

        int elapsed = mp.getDuration();
        playerDuration.setText(convertFormat(elapsed)); //convert millisecond to minute and second

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnPlay.setVisibility(View.GONE);
                btnPause.setVisibility(View.VISIBLE);
                mp.start();
                seekBar.setMax(mp.getDuration());
                handler.postDelayed(runnable,0);
            }
        });

        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnPlay.setVisibility(View.VISIBLE);
                btnPause.setVisibility(View.GONE);
                mp.pause();
                handler.removeCallbacks(runnable);
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser){
                    mp.seekTo(progress);
                }
                playerPosition.setText(convertFormat(mp.getCurrentPosition()));
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }
    public static ArrayList<AudioEntryMultiple> buildAudioEntries() {
        ArrayList<AudioEntryMultiple> audios = new ArrayList<>();
        audios.add(new AudioEntryMultiple(R.raw.here, "here", new ArrayList<String>(Arrays.asList("here","heal","hare"))));
        audios.add(new AudioEntryMultiple(R.raw.jingle, "jingle", new ArrayList<String>(Arrays.asList("jingle","jungle","google"))));
        audios.add(new AudioEntryMultiple(R.raw.leak, "leak", new ArrayList<String>(Arrays.asList("leak","leaks","leave"))));
        audios.add(new AudioEntryMultiple(R.raw.son, "son", new ArrayList<String>(Arrays.asList("son","sun","sum"))));
        audios.add(new AudioEntryMultiple(R.raw.time, "time", new ArrayList<String>(Arrays.asList("time","fine","wine"))));
        Collections.shuffle(audios);
        return audios;
    }

    @SuppressLint("DefaultLocale")
    private String convertFormat(int duration) {
        return String.format("%02d:%02d",
                TimeUnit.MILLISECONDS.toMinutes(duration),
                TimeUnit.MILLISECONDS.toSeconds(duration) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(duration)));
    }

    public void submitOralMultiple(View view) {
        if (selected.equals("")) {
            Toast.makeText(this, "Il faut saisir une réponse avant de valider", Toast.LENGTH_SHORT).show();
            return;
        }
        if (selected.equals(audioEntries.get(audioIndex).getAnswer().toLowerCase())) {
            score += 1;
        }
        audioIndex += 1;

        if (audioIndex == audioEntries.size()) {
            Intent intent = new Intent(OralComprehensionActivity.this, EnglishResultAudioActivity.class);
            intent.putParcelableArrayListExtra("audioEntries", audioEntries);
            intent.putExtra("score", score);
            this.startActivity(intent);
        } else {
            title.setText(getString(R.string.quizz_entry_title, "" + (audioIndex + 1), "" + audioEntries.size()));
            AudioEntryMultiple currentAudioEntry = audioEntries.get(audioIndex);
            audio = currentAudioEntry.getAudioId();
            mp = MediaPlayer.create(this, audio);
            btn1.setBackgroundColor(getResources().getColor(R.color.english_light));
            btn2.setBackgroundColor(getResources().getColor(R.color.english_light));
            btn3.setBackgroundColor(getResources().getColor(R.color.english_light));
            btn1.setText(currentAudioEntry.getPossibleAnswers().get(0));
            btn2.setText(currentAudioEntry.getPossibleAnswers().get(1));
            btn3.setText(currentAudioEntry.getPossibleAnswers().get(2));
            selected = "";
            btnPlay.setVisibility(View.VISIBLE);
            btnPause.setVisibility(View.GONE);
        }

    }
}