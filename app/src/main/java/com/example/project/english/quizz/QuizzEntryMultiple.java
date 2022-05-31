package com.example.project.english.quizz;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class QuizzEntryMultiple extends QuizzEntrySimple implements Parcelable {
    private ArrayList<String> possibleAnswers;

    public QuizzEntryMultiple(String q, String a, ArrayList<String> p) {
        super(q, a);
        this.possibleAnswers = p;
    }

    public ArrayList<String> getPossibleAnswers() {
        return possibleAnswers;
    }

    //----------- Parcelable logic -----------//

    protected QuizzEntryMultiple(Parcel in) {
        super(in);
        possibleAnswers = in.createStringArrayList();
    }

    public static final Creator<QuizzEntryMultiple> CREATOR = new Creator<QuizzEntryMultiple>() {
        @Override
        public QuizzEntryMultiple createFromParcel(Parcel in) {
            return new QuizzEntryMultiple(in);
        }

        @Override
        public QuizzEntryMultiple[] newArray(int size) {
            return new QuizzEntryMultiple[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(super.getQuestion());
        parcel.writeString(super.getAnswer());
        parcel.writeStringList(this.possibleAnswers);
    }
}