package com.example.timer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    boolean isclicked =true ;
    TextView tview;
    CountDownTimer countDownTimer;
    SeekBar sbar ;
    Button bugo;
    String address="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sbar =(SeekBar)findViewById(R.id.sbar);
        tview = (TextView)findViewById(R.id.tview);

        sbar.setMax(600);
        sbar.setProgress(30);
        sbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                UpdateTimer(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
    public void UpdateTimer(int progress){
        int min = progress/60;
        int sec =progress-(min*60);
        String second =String.valueOf(sec);
        String mints =String.valueOf(min);

            if (min <= 9){
                mints ="0"+String.valueOf(min);
            }
            if (sec<=9){
                second ="0"+String.valueOf(sec);
            }

        tview.setText(mints +":"+ second);
    }

    public void buclick(View view) {

        bugo =(Button)findViewById(R.id.bugo);
        if(isclicked == true){
            countDownTimer =new CountDownTimer(sbar.getProgress()*1000+100,100){
                @Override
                public void onTick(long millisUntilFinished) {
                    UpdateTimer((int) millisUntilFinished/1000);
                }

                @Override
                public void onFinish() {
                    MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(),R.raw.rooster);
                    mediaPlayer.start();
                    resetTimer();
                }
            };
            countDownTimer.start();
            sbar.setEnabled(false);
            address ="Stop!";
            isclicked =false ;
            bugo.setText(address);
        }else if(isclicked==false)
        {
            resetTimer();
        }
    }
    public void resetTimer(){
        countDownTimer.cancel();
        sbar.setEnabled(true);
        address ="Go!";
        tview.setText("00:30");
        bugo.setText(address);
        sbar.setProgress(30);
        isclicked =true ;
    }
}