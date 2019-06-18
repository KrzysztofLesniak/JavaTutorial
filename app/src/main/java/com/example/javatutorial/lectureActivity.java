package com.example.javatutorial;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class lectureActivity extends AppCompatActivity {

    private List<Lecture> lectureList;
    private TextView textViewLectureText;
    private TextView textViewLectureName;
    private Lecture lec = new Lecture();

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lecture_activity);
        textViewLectureText = findViewById(R.id.text_view_text);
        textViewLectureName = findViewById(R.id.text_view_Lesson_name);
        Intent intent = getIntent();
        int lessonID = intent.getIntExtra(startActivity.EXTRA_LESSON_ID, 0);
        String lessonName = intent.getStringExtra(startActivity.EXTRA_LESSON_NAME);

        QuizDbHelper dbHelper = QuizDbHelper.getInstance(this);
        lectureList = dbHelper.getLecture(lessonID);
        lec = lectureList.get(lessonID-1);
        textViewLectureName.setText(lessonName);
        textViewLectureText.setText(Html.fromHtml(lec.getText()));
        }

    @Override
    public void onBackPressed() {
            finish();
    }


}
