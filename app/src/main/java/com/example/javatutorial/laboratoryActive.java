package com.example.javatutorial;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.github.barteksc.pdfviewer.PDFView;

import java.util.List;


public class laboratoryActive extends AppCompatActivity {

    PDFView labPDF;
    private String fileName;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.laboratory_active);

        Intent intent = getIntent();
        int lessonID = intent.getIntExtra(startActivity.EXTRA_LESSON_ID, 0);
        fileName = "JAVA_L_0" + lessonID + "_2018_2019_instrukcja.pdf";


        labPDF = findViewById(R.id.pdfView);
        labPDF.fromAsset(fileName).load();


//        textViewLaboratoryText = findViewById(R.id.text_view_text);
//        textViewLaboratoryName = findViewById(R.id.text_view_Lesson_name);
//
//        Intent intent = getIntent();
//        int lessonID = intent.getIntExtra(startActivity.EXTRA_LESSON_ID, 0);
//        String lessonName = intent.getStringExtra(startActivity.EXTRA_LESSON_NAME);
//
//        QuizDbHelper dbHelper = QuizDbHelper.getInstance(this);
//        labolatoryList = dbHelper.getLaboratory(lessonID);
//        lab = labolatoryList.get(lessonID-1);
//        textViewLaboratoryName.setText(lessonName);
//        textViewLaboratoryText.setText(Html.fromHtml(lab.getText()));
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
