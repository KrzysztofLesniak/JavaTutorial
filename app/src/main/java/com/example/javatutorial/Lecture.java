package com.example.javatutorial;

import android.os.Parcel;
import android.os.Parcelable;

public class Lecture implements Parcelable {

    private int id;
    private String text;
    private int lessonID;

    public Lecture(){
    }

    public Lecture(String text,int lessonID) {
        this.text = text;
        this.lessonID = lessonID;
    }
    protected Lecture(Parcel in){
        id =in.readInt();
        text =in.readString();
         lessonID = in.readInt();
    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(text);
        dest.writeInt(lessonID);
    }
    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Lecture> CREATOR = new Creator<Lecture>() {
        @Override
        public Lecture createFromParcel(Parcel in) {
            return new Lecture(in);
        }

        @Override
        public Lecture[] newArray(int size) {
            return new Lecture[size];
        }
    };


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
