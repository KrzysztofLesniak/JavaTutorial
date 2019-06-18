package com.example.javatutorial;

import android.provider.BaseColumns;

public final class QuizContract {

    private QuizContract() {
    }

    public static class LessonsTable implements BaseColumns{
        public static final String TABLE_NAME = "quiz_lesson";
        public static final String COLUMN_NAME = "name";
    }

    public static class LectureTable implements BaseColumns {
        public static final String TABLE_NAME = "lecture";
        public static final String COLUMN_LECTURE_TEXT = "lecture_text";
        public static final String COLUMN_LESSON_ID = "lesson_nr";
    }
//    public static class LaboratoryTable implements BaseColumns {
//        public static final String TABLE_NAME = "laboratory";
//        public static final String COLUMN_LABORATORY_TEXT = "laboratory_text";
//        public static final String COLUMN_LESSON_ID = "lesson_nr";
//    }
    public static class QuestionsTable implements BaseColumns {
        public static final String TABLE_NAME = "quiz_questions";
        public static final String COLUMN_QUESTION = "question";
        public static final String COLUMN_OPTION1 = "option1";
        public static final String COLUMN_OPTION2 = "option2";
        public static final String COLUMN_OPTION3 = "option3";
        public static final String COLUMN_OPTION4 = "option4";
        public static final String COLUMN_ANSWER_NR = "answer_nr";
        public static final String COLUMN_LESSON_ID = "lesson_nr";
    }
}
