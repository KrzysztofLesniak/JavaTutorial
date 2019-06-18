package com.example.javatutorial;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.javatutorial.QuizContract.*;

import java.util.ArrayList;
import java.util.List;

public class QuizDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "javaTutorial.db";
    private static final int DATABASE_VERSION = 1;


    private static QuizDbHelper instance;

    private SQLiteDatabase db;

    public QuizDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized QuizDbHelper getInstance(Context context) {
        if (instance == null) {
            instance = new QuizDbHelper(context.getApplicationContext());
        }
        return instance;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;
        final String SQL_CREATE_LESSON_TABLE = "CREATE TABLE " +
                LessonsTable.TABLE_NAME + "( " +
                LessonsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                LessonsTable.COLUMN_NAME + " TEXT " + ")";

        final String SQL_CREATE_LECTURE_TABLE = "CREATE TABLE " +
                LectureTable.TABLE_NAME + "( " +
                LectureTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                LectureTable.COLUMN_LECTURE_TEXT + " TEXT, " +
                LectureTable.COLUMN_LESSON_ID + " INTEGER, " +
                "FOREIGN KEY(" + LectureTable.COLUMN_LESSON_ID + ") REFERENCES " +
                LessonsTable.TABLE_NAME + "(" + LessonsTable._ID + ")" + "ON DELETE CASCADE" + ")";

//        final String SQL_CREATE_LABORATORY_TABLE = "CREATE TABLE " +
//                LaboratoryTable.TABLE_NAME + "( " +
//                LaboratoryTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
//                LaboratoryTable.COLUMN_LABORATORY_TEXT + " TEXT, " +
//                LaboratoryTable.COLUMN_LESSON_ID + " INTEGER, " +
//                "FOREIGN KEY(" + LaboratoryTable.COLUMN_LESSON_ID + ") REFERENCES " +
//                LessonsTable.TABLE_NAME + "(" + LessonsTable._ID + ")" + "ON DELETE CASCADE" + ")";

        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuestionsTable.TABLE_NAME + " ( " +
                QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION4 + " TEXT, " +
                QuestionsTable.COLUMN_ANSWER_NR + " INTEGER, " +
                QuestionsTable.COLUMN_LESSON_ID + " INTEGER, " +
                "FOREIGN KEY(" + QuestionsTable.COLUMN_LESSON_ID + ") REFERENCES " +
                LessonsTable.TABLE_NAME + "(" + LessonsTable._ID + ")" + "ON DELETE CASCADE" +
                ")";

        db.execSQL(SQL_CREATE_LESSON_TABLE);
        db.execSQL(SQL_CREATE_LECTURE_TABLE);
   //     db.execSQL(SQL_CREATE_LABORATORY_TABLE);
        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillLessonsTable();
   //     fillLaboratoryTable();
        fillLectureTable();
        fillQuestionsTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + LessonsTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + LectureTable.TABLE_NAME);
    //    db.execSQL("DROP TABLE IF EXISTS " + LaboratoryTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + QuestionsTable.TABLE_NAME);
        onCreate(db);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    private void fillLectureTable() {
        Lecture c1 = new Lecture("<h1>About the Java Technology</h1><div id=\"PageContent\">\n" +
                "\n" +
                "<p>Java technology is both a programming language and a platform.</p>\n" +
                "<h2>The Java Programming Language</h2>\n" +
                "<p>The Java programming language is a high-level language that can be characterized by all of the following buzzwords:</p>\n" +
                "<table summary=\"\">\n" +
                "<tbody><tr>\n" +
                "<td>\n" +
                "<ul>\n" +
                "<li>Simple</li>\n" +
                "<li>Object oriented</li>\n" +
                "<li>Distributed</li>\n" +
                "<li>Multithreaded</li>\n" +
                "<li>Dynamic</li>\n" +
                "</ul>\n" +
                "</td>\n" +
                "<td>\n" +
                "<ul>\n" +
                "<li>Architecture neutral</li>\n" +
                "<li>Portable</li>\n" +
                "<li>High performance</li>\n" +
                "<li>Robust</li>\n" +
                "<li>Secure</li>\n" +
                "</ul>\n" +
                "</td>\n" +
                "</tr>\n" +
                "</tbody></table>\n" +
                "<p>Each of the preceding buzzwords is explained in \n" +
                "<p><em>The Java Language Environment</em>, a white paper written by James Gosling and Henry McGilton.</p>\n" +
                "<p>In the Java programming language, all source code is first written in plain text files ending with the <code>.java</code> extension. Those source files are then compiled into <code>.class</code> files by the <code>javac</code> compiler. A <code>.class</code> file does not contain code that is native to your processor; it instead contains <em>bytecodes</em> — the machine language of the Java Virtual Machine. The java launcher tool then runs your application with an instance of the Java Virtual Machine.</p>\n" +
                "<p>Because the Java VM is available on many different operating systems, the same <code>.class</code> files are capable of running on Microsoft Windows, the Solaris™ Operating System (Solaris OS), Linux, or Mac OS. Some virtual machines, such as the \n" +
                "<p>Java SE HotSpot at a Glance, perform additional steps at runtime to give your application a performance boost. This includes various tasks such as finding performance bottlenecks and recompiling (to native code) frequently used sections of code.</p>\n" +
                "<p>Through the Java VM, the same application is capable of running on multiple platforms.</p></center><h2>The Java Platform</h2>\n" +
                "<p>A <em>platform</em> is the hardware or software environment in which a program runs. We've already mentioned some of the most popular platforms like Microsoft Windows, Linux, Solaris OS, and Mac OS. Most platforms can be described as a combination of the operating system and underlying hardware. The Java platform differs from most other platforms in that it's a software-only platform that runs on top of other hardware-based platforms.</p>\n" +
                "<p>The Java platform has two components:</p>\n" +
                "<ul>\n" +
                "<li>The <em>Java Virtual Machine</em></li>\n" +
                "<li>The <em>Java Application Programming Interface</em> (API)</li>\n" +
                "</ul>\n" +
                "<p>You've already been introduced to the Java Virtual Machine; it's the base for the Java platform and is ported onto various hardware-based platforms.</p>\n" +
                "<p>The API is a large collection of ready-made software components that provide many useful capabilities. It is grouped into libraries of related classes and interfaces; these libraries are known as <em>packages</em>. The next section, \n" +
                "<p>The API and Java Virtual Machine insulate the program from the underlying hardware.</p></center><p>As a platform-independent environment, the Java platform can be a bit slower than native code. However, advances in compiler and virtual machine technologies are bringing performance close to that of native code without threatening portability.</p>\n" +
                "<p><a name=\"FOOT\" id=\"FOOT\">The terms\"Java Virtual Machine\" and \"JVM\" mean a Virtual Machine for the Java platform.</a></p>\n" +
                "\n" +
                "        </div>",1);
        insertLecture(c1);
        Lecture c2 = new Lecture("Donec eget egestas arcu. Cras imperdiet mattis sem vitae porta. Phasellus tempus, sem vitae luctus molestie, risus nibh lobortis ipsum, at tempor neque elit sed est. Nunc ligula est, porttitor sit amet imperdiet vitae, eleifend finibus erat. Nunc lectus est, ultricies ac sem non, maximus dapibus justo. Donec et viverra nulla. Maecenas sit amet pretium nisl. Sed posuere, nisl dictum faucibus porta, tellus arcu efficitur lectus, at blandit magna felis sed urna.", 2);
        insertLecture(c2);
        Lecture c3 = new Lecture("Donec eu tellus sed risus condimentum porta ut ac dui. Vestibulum id nisi sapien. Nulla mattis metus vitae nisi tristique, sed consequat urna sagittis. Suspendisse felis arcu, blandit ac consectetur in, cursus id lectus. Cras sapien diam, auctor non hendrerit sed, ornare non nibh. Maecenas pulvinar tempor libero eu dapibus. Proin ac molestie odio.", 3);
        insertLecture(c3);
        Lecture c4 = new Lecture("Nunc dignissim odio non massa pulvinar ullamcorper. Vestibulum eu leo mattis, placerat lorem a, dignissim lectus. Vivamus non mi in orci eleifend ultricies. In mauris metus, interdum at lorem at, ultricies cursus purus. Curabitur a sodales arcu. Maecenas quis volutpat neque. Mauris ac efficitur elit, sed auctor est. Nulla feugiat magna a odio cursus tincidunt. Nulla semper dui molestie ex aliquam ornare. Interdum et malesuada fames ac ante ipsum primis in faucibus. Praesent viverra felis ac nibh pellentesque, id bibendum ante rhoncus. Phasellus vitae erat eu mi consectetur mollis. Nullam finibus turpis sit amet magna porttitor, eget dapibus libero luctus. Aenean viverra, lectus nec fringilla tincidunt, nisl tellus pretium nulla, vitae tristique magna arcu ac nisl.", 4);
        insertLecture(c4);
        Lecture c5 = new Lecture("Fusce sed lacinia turpis. Curabitur volutpat imperdiet vehicula. Praesent elementum lorem non massa dapibus bibendum. Fusce in fringilla urna. Aliquam rutrum purus libero, vel dapibus massa volutpat non. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Nam venenatis tortor ligula, sed auctor augue pulvinar euismod. Mauris purus neque, vehicula vitae accumsan interdum, blandit in turpis. Etiam vitae est efficitur, aliquam nisl nec, cursus lectus. Nullam felis odio, volutpat nec imperdiet in, ultrices lacinia massa. Ut nec urna ut ipsum congue bibendum at viverra augue. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas.", 5);
        insertLecture(c5);
    }

    private void insertLecture(Lecture lecture) {
        ContentValues cv = new ContentValues();
        cv.put(LectureTable.COLUMN_LECTURE_TEXT, lecture.getText());
        db.insert(LectureTable.TABLE_NAME, null, cv);
    }

//    private void fillLaboratoryTable() {
//        Laboratory c1 = new Laboratory("<html><b>Bold text</b></html>",1);
//        insertLaboratory(c1);
//        Laboratory c2 = new Laboratory("Donec eget egestas arcu. Cras imperdiet mattis sem vitae porta. Phasellus tempus, sem vitae luctus molestie, risus nibh lobortis ipsum, at tempor neque elit sed est. Nunc ligula est, porttitor sit amet imperdiet vitae, eleifend finibus erat. Nunc lectus est, ultricies ac sem non, maximus dapibus justo. Donec et viverra nulla. Maecenas sit amet pretium nisl. Sed posuere, nisl dictum faucibus porta, tellus arcu efficitur lectus, at blandit magna felis sed urna.", 2);
//        insertLaboratory(c2);
//        Laboratory c3 = new Laboratory("Donec eu tellus sed risus condimentum porta ut ac dui. Vestibulum id nisi sapien. Nulla mattis metus vitae nisi tristique, sed consequat urna sagittis. Suspendisse felis arcu, blandit ac consectetur in, cursus id lectus. Cras sapien diam, auctor non hendrerit sed, ornare non nibh. Maecenas pulvinar tempor libero eu dapibus. Proin ac molestie odio.", 3);
//        insertLaboratory(c3);
//        Laboratory c4 = new Laboratory("Nunc dignissim odio non massa pulvinar ullamcorper. Vestibulum eu leo mattis, placerat lorem a, dignissim lectus. Vivamus non mi in orci eleifend ultricies. In mauris metus, interdum at lorem at, ultricies cursus purus. Curabitur a sodales arcu. Maecenas quis volutpat neque. Mauris ac efficitur elit, sed auctor est. Nulla feugiat magna a odio cursus tincidunt. Nulla semper dui molestie ex aliquam ornare. Interdum et malesuada fames ac ante ipsum primis in faucibus. Praesent viverra felis ac nibh pellentesque, id bibendum ante rhoncus. Phasellus vitae erat eu mi consectetur mollis. Nullam finibus turpis sit amet magna porttitor, eget dapibus libero luctus. Aenean viverra, lectus nec fringilla tincidunt, nisl tellus pretium nulla, vitae tristique magna arcu ac nisl.", 4);
//        insertLaboratory(c4);
//        Laboratory c5 = new Laboratory("Fusce sed lacinia turpis. Curabitur volutpat imperdiet vehicula. Praesent elementum lorem non massa dapibus bibendum. Fusce in fringilla urna. Aliquam rutrum purus libero, vel dapibus massa volutpat non. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Nam venenatis tortor ligula, sed auctor augue pulvinar euismod. Mauris purus neque, vehicula vitae accumsan interdum, blandit in turpis. Etiam vitae est efficitur, aliquam nisl nec, cursus lectus. Nullam felis odio, volutpat nec imperdiet in, ultrices lacinia massa. Ut nec urna ut ipsum congue bibendum at viverra augue. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas.", 5);
//        insertLaboratory(c5);
//    }
//
//    private void insertLaboratory(Laboratory laboratory) {
//        ContentValues cv = new ContentValues();
//        cv.put(LaboratoryTable.COLUMN_LABORATORY_TEXT, laboratory.getText());
//        db.insert(LaboratoryTable.TABLE_NAME, null, cv);
//    }

    private void fillLessonsTable() {
        Lesson c1 = new Lesson("JAVA SE", 0);
        insertLesson(c1);
        Lesson c2 = new Lesson("Budowa GUI", 0);
        insertLesson(c2);
        Lesson c3 = new Lesson("Zdarzenia", 0);
        insertLesson(c3);
        Lesson c4 = new Lesson("JavaFX", 0);
        insertLesson(c4);
        Lesson c5 = new Lesson("Java Android", 0);
        insertLesson(c5);
    }

    private void insertLesson(Lesson lesson) {
        ContentValues cv = new ContentValues();
        cv.put(LessonsTable.COLUMN_NAME, lesson.getName());
        db.insert(LessonsTable.TABLE_NAME, null, cv);
    }

    private void fillQuestionsTable() {
        Question q1 = new Question("Jaka komenda służy do wyświetlania teksu na konsoli?",
                "System.out.println", "args.length", "System.exit(0);", " file.nextLine()",
                1, Lesson.Lesson1);
        insertQuestion(q1);
        Question q2 = new Question("Co zostanie wypisane na ekranie po wykonianiu kodu byte b = (byte)256; System.out.println(b);",
                "0", "kod nie skompiluje się", "256", "AX6",
                3, Lesson.Lesson1);
        insertQuestion(q2);
        Question q3 = new Question(" W wyrażeniu z dwoma składnikami bez rzutowania jeżeli jeden jest integer a drugo float to wynik wyrażenia będzie typu?",
                "integer", "float", "kod nie skompiluje się", "String",
                2, Lesson.Lesson1);
        insertQuestion(q3);
        Question q4 = new Question("Typ Char może być przypisany do typu Double bez rzutowania?",
                "tak", "tylko w Javie w wersji FX", "tylko w Javie w wersji EE", "nie",
                4, Lesson.Lesson1);
        insertQuestion(q4);
        Question q5 = new Question("Czy można przekształcić tablicę na obiekt bez rzutowania?",
                "tak", "nie", "tylko w Javie w wersji EE", "od Javy 8 już nie",
                1, Lesson.Lesson1);
        insertQuestion(q5);
        Question q11 = new Question(" Czy interfejs może być przekształcony w inny interfejs?",
                "nie wiem", "tak", "nie", "to zależy",
                3, Lesson.Lesson1);
        insertQuestion(q11);
        Question q12 = new Question("Jaką wartość powinna zwracać metoda hashCode",
                "Jeśli dwa obiekty są równe za pomocą equals to wartości zwracane przez hashCode również muszą być" +
                        "takie same", "Powinna zwracać wartość liczbową obliczoną na podstawie wskaźnika", "Jeśli dwa obiekty są różne wartości hashCode również muszą być inne", "metoda ta nic nie zwraca",
                1, Lesson.Lesson1);
        insertQuestion(q12);
        Question q13 = new Question("Jaki symbol oznacza wybranie danych z wszystkich kolumn tabeli?",
                "*", "?", "ALL", "EVERYTHING ",
                1, Lesson.Lesson1);
        insertQuestion(q13);
        Question q10 = new Question("Javowym IDE nie jest ?",
                "InteliJ IDEA", "NetBeans IDE ", "JDeveloper", "MavickPro",
                4, Lesson.Lesson1);
        insertQuestion(q10);
//        Question q12 = new Question("",
//                "", "", "", "",
//                1, Lesson.Lesson1);
//        insertQuestion(q11);
        Question q6 = new Question("Ile procent powierzchni Tatr leży w Polsce?",
                "około 70%", "około 90%", "około 20%", "około 40%",
                3, Lesson.Lesson5);
        insertQuestion(q6);
        Question q7 = new Question("Który z tych szczytów jest najwyższy?",
                "Mały Durny Szczyt", "Sepia Turnia", "Lodowa Kopa", "Łomnica",
                4, Lesson.Lesson4);
        insertQuestion(q7);
        Question q8 = new Question("Który z tych szczytów nie leży w Tatrach?",
                "Łabski Szczyt", "Gerlachowska Kopa", "Lawinowy Szczyt", "Mały Smoczy Szczyt",
                1, Lesson.Lesson3);
        insertQuestion(q8);
        Question q9 = new Question("Najwyższym szczytem Tatr jest znajdujący się całkowicie po słowackiej stronie:",
                "Kozi Wierch", "Starorobociański Wierch", "Gerlach", "Twarda Kopa",
                3, Lesson.Lesson2);
        insertQuestion(q9);
    }

    private void insertQuestion(Question question) {
        ContentValues cv = new ContentValues();
        cv.put(QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuestionsTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuestionsTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuestionsTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuestionsTable.COLUMN_OPTION4, question.getOption4());
        cv.put(QuestionsTable.COLUMN_ANSWER_NR, question.getAnswerNr());
        cv.put(QuestionsTable.COLUMN_LESSON_ID, question.getLessonID());
        db.insert(QuestionsTable.TABLE_NAME, null, cv);
    }

    public List<Lesson> getAllLesson() {
        List<Lesson> LessonList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + LessonsTable.TABLE_NAME, null);

        if (c.moveToFirst()) {
            do {
                Lesson lesson = new Lesson();
                lesson.setId(c.getInt(c.getColumnIndex(LessonsTable._ID)));
                lesson.setName(c.getString(c.getColumnIndex(LessonsTable.COLUMN_NAME)));
                LessonList.add(lesson);
            } while (c.moveToNext());
        }
        return LessonList;
    }


    public ArrayList<Question> getAllQuestions() {
        ArrayList<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_NAME, null);

        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setId(c.getInt(c.getColumnIndex(QuestionsTable._ID)));
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setOption4(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION4)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR)));
                question.setLessonID(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_LESSON_ID)));
                questionList.add(question);
            } while (c.moveToNext());
        }

        c.close();
        return questionList;
    }

    public ArrayList<Question> getQuestions(int lessonID) {
        ArrayList<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();

        String selection = QuestionsTable.COLUMN_LESSON_ID + " = ?";
        String[] selectionArgs = new String[]{String.valueOf(lessonID)};

        Cursor c = db.query(
                QuestionsTable.TABLE_NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setId(c.getInt(c.getColumnIndex(QuestionsTable._ID)));
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setOption4(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION4)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR)));
                question.setLessonID(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_LESSON_ID)));
                questionList.add(question);
            } while (c.moveToNext());
        }

        c.close();
        return questionList;
    }


    public List<Lecture> getLecture(int lessonID) {
        List<Lecture> lectureList = new ArrayList<>();
        db = getReadableDatabase();

        String[] selectionArgs = new String[]{String.valueOf(lessonID)};
        Cursor c = db.rawQuery("SELECT * FROM " + LectureTable.TABLE_NAME + " WHERE ?"
                , selectionArgs);


        if (c.moveToFirst()) {
            do {
                Lecture lecture = new Lecture();
                lecture.setId(c.getInt(c.getColumnIndex(LectureTable._ID)));
                lecture.setText(c.getString(c.getColumnIndex(LectureTable.COLUMN_LECTURE_TEXT)));
                lecture.setLessonID(c.getInt(c.getColumnIndex(LectureTable.COLUMN_LESSON_ID)));
                lectureList.add(lecture);
            } while (c.moveToNext());
        }

        c.close();
        return lectureList;
    }


//    public List<Laboratory> getLaboratory(int lessonID) {
//        List<Laboratory> laboratoryList = new ArrayList<>();
//        db = getReadableDatabase();
//
//        String[] selectionArgs = new String[]{String.valueOf(lessonID)};
//        Cursor c = db.rawQuery("SELECT * FROM " + LaboratoryTable.TABLE_NAME + " WHERE ?"
//                , selectionArgs);
//
//        if (c.moveToFirst()) {
//            do {
//                Laboratory laboratory = new Laboratory();
//                laboratory.setId(c.getInt(c.getColumnIndex(LaboratoryTable._ID)));
//                laboratory.setText(c.getString(c.getColumnIndex(LaboratoryTable.COLUMN_LABORATORY_TEXT)));
//                laboratory.setLessonID(c.getInt(c.getColumnIndex(LaboratoryTable.COLUMN_LESSON_ID)));
//                laboratoryList.add(laboratory);
//            } while (c.moveToNext());
//        }
//
//        c.close();
//        return laboratoryList;
//    }
}
