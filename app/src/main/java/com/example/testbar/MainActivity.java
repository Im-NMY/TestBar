package com.example.testbar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {
    private ProgressBar MyBar;
    private Button MyButton;
    private Handler handler = new Handler();


    private void doStartMyBar()  {
        this.MyBar.setIndeterminate(true);
        Thread thread = new Thread(new Runnable()  {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        MyButton.setEnabled(false);
                        MyBar.setVisibility(View.VISIBLE);
                    }
                });
                SystemClock.sleep(5000); // Sleep 5 seconds.

                MyBar.setIndeterminate(false);
                handler.post(new Runnable() {
                    public void run() {
                        MyButton.setEnabled(true);
                        MyBar.setVisibility(View.GONE);
                    }
                });
            }
        });
        thread.start();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyBar = findViewById(R.id.MyBar);
        MyButton = findViewById(R.id.MyButton);
        MyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doStartMyBar();
            }
        });
    }
}