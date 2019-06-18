package com.example.javatutorial;

public class Lesson {

    public static final int Lesson1 = 1;
    public static final int Lesson2 = 2;
    public static final int Lesson3 = 3;
    public static final int Lesson4 = 4;
    public static final int Lesson5 = 5;
    public static final int Lesson6 = 6;
    private int id;
    private String name;
    private int highscore;

    public Lesson(){
    }

    public Lesson(String name, int score){
        this.name = name;
        this.highscore =score;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return getName();
    }

    public int getHighscore() {
        return highscore;
    }

    public void setHighscore(int highscore) {
        this.highscore = highscore;
    }
}
