package com.example.javatutorial;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

public class quizActivity extends AppCompatActivity {
    public static final String EXTRA_SCORE = "extraScore";
    public static final String EXTRA_SIZE = "extraSize";

    public static final String EXTRA_LESSON_NAME = "extraLessonName";
    private static final long COUNTDOWN_IN_MILIS = 30000;

    private static final String KEY_SCORE = "keyScore";
    private static final String KEY_QUESTION_COUNT = "keyQuestionCount";
    private static final String KEY_MILIS_LEFT = "keyMilisLeft";
    private static final String KEY_ANSWERED = "keyAnswered";
    private static final String KEY_QUESTION_LIST = "keyQuestionList";

    private TextView textViewQuestion;
    private TextView textViewScore;
    private TextView textViewQuestionCount;
    private TextView textViewLesson;
    private TextView textViewCountDown;
    private RadioGroup rbGroup;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private RadioButton rb4;
    private Button buttonConfirmNext;

    private ColorStateList textColorDefaultRb;
    private ColorStateList textColorDefaultCd;

    private int lessonID;
    private String lessonName;

    private CountDownTimer countDownTimer;
    private long timeLeftInMilis;

    private ArrayList<Question> questionList;
    private int questionCounter;
    private int questionCountTotal;
    private Question currentQuestion;

    public int score;
    private boolean answered;

    private long backPressedTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_activity);

        textViewQuestion = findViewById(R.id.text_view_question);
        textViewScore = findViewById(R.id.text_view_score);
        textViewQuestionCount = findViewById(R.id.text_view_question_count);
        textViewLesson = findViewById(R.id.text_view_lesson);
        textViewCountDown = findViewById(R.id.text_view_countdown);
        rbGroup = findViewById(R.id.radio_group);
        rb1 = findViewById(R.id.radio_button1);
        rb2 = findViewById(R.id.radio_button2);
        rb3 = findViewById(R.id.radio_button3);
        rb4 = findViewById(R.id.radio_button4);
        buttonConfirmNext = findViewById(R.id.button_confirm_next);

        textColorDefaultRb = rb1.getTextColors();
        textColorDefaultCd = textViewCountDown.getTextColors();

        Intent intent = getIntent();
        lessonID = intent.getIntExtra(startActivity.EXTRA_LESSON_ID, 0);
        lessonName = intent.getStringExtra(startActivity.EXTRA_LESSON_NAME);

        textViewLesson.setText(lessonName);

        if (savedInstanceState == null) {
            QuizDbHelper dbHelper = QuizDbHelper.getInstance(this);
            questionList = dbHelper.getQuestions(lessonID);
            questionCountTotal = questionList.size();
            Collections.shuffle(questionList);

            showNextQuestion();
        }  else
        {
            questionList = savedInstanceState.getParcelableArrayList(KEY_QUESTION_LIST);
            questionCountTotal = 2;
            if(questionList == null){
                finishQuiz();
            }
            questionCounter = savedInstanceState.getInt(KEY_QUESTION_COUNT);
            currentQuestion = questionList.get(questionCounter - 1);
            score = savedInstanceState.getInt(KEY_SCORE);
            timeLeftInMilis = savedInstanceState.getLong(KEY_MILIS_LEFT);
            answered = savedInstanceState.getBoolean(KEY_ANSWERED);

            if(!answered){
                startCountDown();
            }
            else{
                updateCountDownText();
                showSolution();
            }
        }

        buttonConfirmNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!answered) {
                    if (rb1.isChecked() || rb2.isChecked() || rb3.isChecked() || rb4.isChecked()) {
                        checkAnswer();
                    } else {
                        Toast.makeText(quizActivity.this, "Wybierz odpowiedz", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    showNextQuestion();
                }
            }
        });
    }


    private void showNextQuestion() {
        rb1.setTextColor(textColorDefaultRb);
        rb2.setTextColor(textColorDefaultRb);
        rb3.setTextColor(textColorDefaultRb);
        rb4.setTextColor(textColorDefaultRb);
        rbGroup.clearCheck();

        if (questionCounter < questionCountTotal) {
            currentQuestion = questionList.get(questionCounter);

            textViewQuestion.setText((currentQuestion.getQuestion()));
            rb1.setText(currentQuestion.getOption1());
            rb2.setText(currentQuestion.getOption2());
            rb3.setText(currentQuestion.getOption3());
            rb4.setText(currentQuestion.getOption4());

            questionCounter++;
            textViewQuestionCount.setText(("Pytanie" + questionCounter + "/" + questionCountTotal));
            answered = false;
            buttonConfirmNext.setText("Wybierz");

            timeLeftInMilis = COUNTDOWN_IN_MILIS;
            startCountDown();
        } else {
            finishQuiz();
        }
    }


    private void startCountDown() {
        countDownTimer = new CountDownTimer(timeLeftInMilis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMilis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                timeLeftInMilis = 0;
                updateCountDownText();
                checkAnswer();
            }
        }.start();
    }

    private void updateCountDownText() {
        int minutes = (int) (timeLeftInMilis / 1000) / 60;
        int seconds = (int) (timeLeftInMilis / 1000) % 60;

        String timerFormatted = String.format((Locale.getDefault()), "%02d:%02d", minutes, seconds);
        textViewCountDown.setText(timerFormatted);

        if (timeLeftInMilis < 10000) {
            textViewCountDown.setTextColor(Color.RED);
        } else {
            textViewCountDown.setTextColor(textColorDefaultCd);
        }
    }


    private void checkAnswer() {
        answered = true;

        countDownTimer.cancel();

        RadioButton rbSelected = findViewById(rbGroup.getCheckedRadioButtonId());
        int answeredNr = rbGroup.indexOfChild(rbSelected) + 1;
        if (answeredNr == currentQuestion.getAnswerNr()) {
            score++;
            textViewScore.setText("Wynik: " + score);
        }
        showSolution();
    }

    private void showSolution() {
        rb1.setTextColor(Color.RED);
        rb2.setTextColor(Color.RED);
        rb3.setTextColor(Color.RED);
        rb4.setTextColor(Color.RED);

        switch (currentQuestion.getAnswerNr()) {
            case 1:
                rb1.setTextColor(Color.rgb(00, 99, 75));
                textViewQuestion.setText("Prawidłowa odpowiedz to A");
                break;
            case 2:
                rb2.setTextColor(Color.rgb(00, 99, 75));
                textViewQuestion.setText("Prawidłowa odpowiedz to B");
                break;
            case 3:
                rb3.setTextColor(Color.rgb(00, 99, 75));
                textViewQuestion.setText("Prawidłowa odpowiedz to C");
                break;
            case 4:
                rb4.setTextColor(Color.rgb(00, 99, 75));
                textViewQuestion.setText("Prawidłowa odpowiedz to D");
                break;
        }
        if (questionCounter < questionCountTotal) {
            buttonConfirmNext.setText("Następne");

        } else {
            buttonConfirmNext.setText("Finish");
        }
    }

    private void finishQuiz() {
        Intent intent = new Intent(quizActivity.this, highscoreActivity.class);
        intent.putExtra(EXTRA_LESSON_NAME, lessonName);
        intent.putExtra(EXTRA_SCORE, Integer.toString(score));
        intent.putExtra(EXTRA_SIZE, Integer.toString(questionCountTotal));
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            finishQuiz();
        } else {
            Toast.makeText(this, "Naciśnij wstecz jeszcze raz, aby skończyć quiz", Toast.LENGTH_SHORT).show();
        }
        backPressedTime = System.currentTimeMillis();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putInt(KEY_SCORE, score);
        outState.putInt(KEY_QUESTION_COUNT, questionCounter);
        outState.putLong(KEY_MILIS_LEFT, timeLeftInMilis);
        outState.putBoolean(KEY_ANSWERED, answered);
        outState.putParcelableArrayList(KEY_QUESTION_LIST, questionList);
    }
}


//    Caused by: java.lang.NullPointerException: Attempt to invoke virtual method 'int java.util.ArrayList.size()' on a null object reference
//        at com.example.javatutorial.quizActivity.onCreate(quizActivity.java:101)
//
// java.lang.RuntimeException: Unable to start activity ComponentInfo{com.example.javatutorial/com.example.javatutorial.quizActivity}: java.lang.NullPointerException: Attempt to invoke virtual method 'int java.util.ArrayList.size()' on a null object reference
//         at android.app.ActivityThread.performLaunchActivity(ActivityThread.java:2913)