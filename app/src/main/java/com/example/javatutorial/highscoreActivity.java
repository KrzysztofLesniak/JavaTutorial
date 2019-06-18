package com.example.javatutorial;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import static java.lang.Integer.parseInt;

public class highscoreActivity extends AppCompatActivity {

    private TextView textViewResultText;
    private TextView textViewResult;
    private TextView textViewLesson;
    private TextView textViewPoints;
    private Button buttonBack;
    private long backPressedTime;
    private int grade=2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.highscore_activity);
        textViewResultText = findViewById(R.id.text_view_result_text);
        textViewResult = findViewById(R.id.text_view_result);
        textViewPoints = findViewById(R.id.text_view_lesson_points);
        textViewLesson = findViewById(R.id.text_view_lesson_topic);
        buttonBack = findViewById(R.id.button_back);

        Intent intent = getIntent();
        String lessonName = intent.getStringExtra(quizActivity.EXTRA_LESSON_NAME);
        String scoreText = intent.getStringExtra(quizActivity.EXTRA_SCORE);
        String sizeText = intent.getStringExtra(quizActivity.EXTRA_SIZE);
        int size = parseInt(sizeText);
        int score = parseInt(scoreText);
        float sizeF = size;
        float scoreF = score;

        if (scoreF / sizeF< 0.5) {
            grade = 2;
        } else if (scoreF / sizeF < 0.7) {
            grade = 3;
        } else if (scoreF / sizeF < 0.9) {
            grade = 4;
        } else {
            grade = 5;
        }

        textViewLesson.setText("Lekcja: " + lessonName);
        if (grade < 3) {
            textViewResultText.setText("Niestety, " +
                    "nie udalo ci się zaliczyć");

        } else {
            textViewResultText.setText("Gratulację. " +
                    "Ocena pozytywna");
        }
        textViewResult.setText("Twoja ocena to: " + grade);
        textViewPoints.setText(score + "/" + size);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishActivity();
            }
        });

    }

    @Override
    public void onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            finishActivity();
        } else {
            Toast.makeText(this, "Naciśnij wstecz jeszcze raz, aby wyjść", Toast.LENGTH_SHORT).show();
        }
        backPressedTime = System.currentTimeMillis();
    }

    private void finishActivity() {
        Intent intent = new Intent(highscoreActivity.this, startActivity.class);
        startActivity(intent);
    }

}
