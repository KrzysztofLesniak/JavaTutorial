package com.example.javatutorial;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 1;
    public static final String SHARED_PREFS = "sharedPrefs";
    private long backPressedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button buttonStart = findViewById(R.id.button_start);
        Button buttonCredits = findViewById(R.id.button_credits);
        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start();
            }
        });
        buttonCredits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                credits();
            }
        });
    }

    private void start(){
        Intent intent = new Intent(MainActivity.this, startActivity.class);
        startActivity(intent);
    }
    private void credits(){
        Intent intent = new Intent(MainActivity.this, creditsActivity.class);
        startActivity(intent);
    }


    @Override
    public void onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            finish();
            moveTaskToBack(true);
        } else {
            Toast.makeText(this, "Naciśnij wstecz jeszcze raz, aby zamknąć aplikację", Toast.LENGTH_SHORT).show();
        }
        backPressedTime = System.currentTimeMillis();
    }
}
