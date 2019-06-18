package com.example.javatutorial;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

public class startActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_QUIZ = 1;
    public static final String EXTRA_LESSON_NAME = "extraLessonName";
    public static final String EXTRA_LESSON_ID = "extraLessonId";
    public static final String SHARED_PREFS = "sharedPrefs";

    private Spinner spinnerLesson;

    private long backPressedTime;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_activity);

        spinnerLesson = findViewById(R.id.spinner_lesson);
        loadLessons();

        Button buttonBack = findViewById(R.id.button_back);
        Button buttonLecture = findViewById(R.id.button_lecture);
        Button buttonLab = findViewById(R.id.button_lab);
        Button buttonQuiz = findViewById(R.id.button_quiz);

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAcitivity();
            }
        });

        buttonLecture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startLecture();
            }
        });

        buttonLab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startLab();
            }
        });

        buttonQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startQuiz();
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            finishAcitivity();
        } else {
            Toast.makeText(this, "Naciśnij wstecz jeszcze raz, aby wyjść", Toast.LENGTH_SHORT).show();
        }
        backPressedTime = System.currentTimeMillis();
    }

    private void startQuiz(){
        Lesson selectedLesson = (Lesson) spinnerLesson.getSelectedItem();
        int lessonID = selectedLesson.getId();
        String lessonName = selectedLesson.getName();
        Intent intent = new Intent(startActivity.this, quizActivity.class);
        intent.putExtra(EXTRA_LESSON_ID,lessonID);
        intent.putExtra(EXTRA_LESSON_NAME,lessonName);
        startActivity(intent);
    }

    private void loadLessons() {
        QuizDbHelper dbHelper = QuizDbHelper.getInstance(this);
        List<Lesson> lessons = dbHelper.getAllLesson();

        ArrayAdapter<Lesson> adapterLessons = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, lessons);
        adapterLessons.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLesson.setAdapter(adapterLessons);
    }
 private void startLecture(){
     Lesson selectedLesson = (Lesson) spinnerLesson.getSelectedItem();
     int lessonID = selectedLesson.getId();
     String lessonName = selectedLesson.getName();
     Intent intent = new Intent(startActivity.this, lectureActivity.class);
     intent.putExtra(EXTRA_LESSON_ID,lessonID);
     intent.putExtra(EXTRA_LESSON_NAME,lessonName);
     startActivity(intent);
 }

    private void startLab(){
        Lesson selectedLesson = (Lesson) spinnerLesson.getSelectedItem();
        int lessonID = selectedLesson.getId();
        Intent intent = new Intent(startActivity.this, laboratoryActive.class);
        intent.putExtra(EXTRA_LESSON_ID,lessonID);
        startActivity(intent);
    }

    private void finishAcitivity(){
        Intent intent = new Intent(startActivity.this, MainActivity.class);
        startActivity(intent);
    }

}
