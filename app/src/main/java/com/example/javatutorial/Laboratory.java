package com.example.javatutorial;

public class Laboratory {
    private int id;
    private String text;
    private int lessonID;

    public Laboratory(){

    }

    public Laboratory(String text, int lessonID) {
        this.text = text;
        this.lessonID = lessonID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getLessonID() {
        return lessonID;
    }

    public void setLessonID(int lessonID) {
        this.lessonID = lessonID;
    }


}
