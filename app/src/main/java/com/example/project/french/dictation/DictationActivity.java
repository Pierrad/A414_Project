package com.example.project.french.dictation;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.text.InputType;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.project.R;
import com.example.project.english.EnglishDashboardActivity;
import com.example.project.user.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class DictationActivity extends AppCompatActivity {
    private User user = User.getInstance(this);
    TextView playerPosition, playerDuration,corriger;
    SeekBar seekBar;
    ImageView btRew, btPlay, btPause, btFf;
    MediaPlayer mediaPlayer;
    Handler handler = new Handler();
    Runnable runnable;
    Button btn;
    HashMap<Integer, String> list = new HashMap<Integer, String>();
    Random rand = new Random();
    String correction;
    int audio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dictation);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Français - Dictée");
        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.frenchMain)));
        // Set StatusBar color
        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.frenchMain));


        playerPosition = findViewById(R.id.player_position);
        playerDuration = findViewById(R.id.player_duration);
        seekBar = findViewById(R.id.seek_bar);
        btRew = findViewById(R.id.bt_rew);
        btPlay = findViewById(R.id.bt_play);
        btPause = findViewById(R.id.bt_pause);
        btFf = findViewById(R.id.bt_ff);
        corriger = findViewById(R.id.corriger);
        btn = findViewById(R.id.buttonCorrection);

       list.put(R.raw.dictee1, "Le cargo, avec ses flancs de fer profondément enfoncés dans l'eau " + "sous le poids de sa cargaison, roulait paresseusement sur ses ancres, au " + "large d'une petite île perdue dans l'océan. Les hommes d'équipage prenaient " + "un peu de repos. La veille, ils avaient affronté une terrible tempête. " + "Maintenant tout était calme. Le temps change très vite sous les tropiques, " + "seuls les excellents marins arrivent " + "à manœuvrer par gros temps.");
       list.put(R.raw.dictee2,"Quand le Monsieur le marquis ramenas à au château de Caylus sa belle madrilène long voilée, les ce fut une fièvre générale parmi les jeunes gentils hommes de la vallée de Louron. Il n'y avait point alors de touristes, ces lovelaces ambulants qui s'en vont incendier les cœurs de province partout où le train de plaisir favorise les voyages au rabais ! Mais la guerre permanente avec l'Espagne entretenait de nombreuses troupes de partisans à la frontière, et Monsieur le marquis n'avait qua se bien tenir.  Il se tint bien ; il accepta bravement la gageure. Le galant qui eût voulu tenter la conquête de la belle Inès aurait dû d'abord se munir de canons de siège.  Il ne s'agissait pas seulement d'un cœur : le cœur était à l'abri derrière les remparts d'une forteresse les tendres billets n'y pouvait rien…");
        Object[] values = list.values().toArray();
        correction = (String) values[rand.nextInt(values.length)];
        for (Map.Entry<Integer, String> entry : list.entrySet()) {
            if (entry.getValue().equals(correction)) {
                audio = entry.getKey();
            }
        }
        System.out.println(correction);

    mediaPlayer = MediaPlayer.create(this,audio);


        runnable = new Runnable() {
            @Override
            public void run() {
                seekBar.setProgress(mediaPlayer.getCurrentPosition());

                handler.postDelayed(this,500);
            }
        };


        int duration = mediaPlayer.getDuration();

        //convert millisecond to minute and second
        String sDuration = convertFormat(duration);

        playerDuration.setText(sDuration);

        btPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btPlay.setVisibility(View.GONE);
                btPause.setVisibility(View.VISIBLE);
                mediaPlayer.start();
                seekBar.setMax(mediaPlayer.getDuration());
                handler.postDelayed(runnable,0);
            }
        });

        btPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btPlay.setVisibility(View.VISIBLE);
                btPause.setVisibility(View.GONE);
                mediaPlayer.pause();
                handler.removeCallbacks(runnable);
            }
        });

        btFf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int currentPosition = mediaPlayer.getCurrentPosition();
                int duration = mediaPlayer.getDuration();

                if (mediaPlayer.isPlaying() && duration != currentPosition){
                    currentPosition += 5000;
                    playerPosition.setText(convertFormat(currentPosition));
                    mediaPlayer.seekTo(currentPosition);
                }
            }
        });

        btRew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int currentPosition = mediaPlayer.getCurrentPosition();

                if(mediaPlayer.isPlaying() && currentPosition > 5000){
                    currentPosition -= 5000;
                    playerPosition.setText(convertFormat(currentPosition));
                    mediaPlayer.seekTo(currentPosition);
                }
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser){
                    mediaPlayer.seekTo(progress);
                }
                playerPosition.setText(convertFormat(mediaPlayer.getCurrentPosition()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                corriger.setText(correction);
                addExperienceToUser();
            }
        });


        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                btPause.setVisibility(View.GONE);
                btPlay.setVisibility(View.VISIBLE);
                mediaPlayer.seekTo(0);
            }
        });
    }

    @SuppressLint("DefaultLocale")
    private String convertFormat(int duration) {
        return String.format("%02d:%02d"
                ,TimeUnit.MILLISECONDS.toMinutes(duration)
                ,TimeUnit.MILLISECONDS.toSeconds(duration) -
                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(duration)));
    }

    public void addExperienceToUser() {
        user.addExperience(25);
    }

}
