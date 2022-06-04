package com.example.project.history.quizz;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Collections;

public class QuizzEntry implements Parcelable {
    public static final Creator<QuizzEntry> CREATOR = new Creator<QuizzEntry>() {
        @Override
        public QuizzEntry createFromParcel(Parcel in) {
            return new QuizzEntry(in);
        }

        @Override
        public QuizzEntry[] newArray(int size) {
            return new QuizzEntry[size];
        }
    };
    private String question;
    private String answer;
    private ArrayList<String> allShuffleAnswers;

    public QuizzEntry(String q, String a, ArrayList<String> p) {
        this.question = q;
        this.answer = a;
        this.allShuffleAnswers = p;

        this.allShuffleAnswers.add(a);
        Collections.shuffle(this.allShuffleAnswers);
    }

    //----------- Parcelable logic -----------//
    protected QuizzEntry(Parcel in) {
        question = in.readString();
        answer = in.readString();
        allShuffleAnswers = in.createStringArrayList();
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public ArrayList<String> getAllShuffleAnswers() {
        return allShuffleAnswers;
    }

    @Override
    public String toString() {
        return question + " -> " + answer;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(question);
        parcel.writeString(answer);
        parcel.writeStringList(allShuffleAnswers);
    }
}
