package com.example.project.english.quizz;

import android.os.Parcel;
import android.os.Parcelable;

public class QuizzEntrySimple implements Parcelable {
    private String question;
    private String answer;

    public QuizzEntrySimple(String q, String a) {
        this.question = q;
        this.answer = a;
    }

    public String getQuestion() {
        return this.question;
    }

    public String getAnswer() {
        return this.answer;
    }

    protected void setQuestion(String question) {
        this.question = question;
    }

    protected void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return this.question + " -> " + this.answer;
    }

    //----------- Parcelable logic -----------//

    protected QuizzEntrySimple(Parcel in) {
        question = in.readString();
        answer = in.readString();
    }

    public static final Creator<QuizzEntrySimple> CREATOR = new Creator<QuizzEntrySimple>() {
        @Override
        public QuizzEntrySimple createFromParcel(Parcel in) {
            return new QuizzEntrySimple(in);
        }

        @Override
        public QuizzEntrySimple[] newArray(int size) {
            return new QuizzEntrySimple[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.question);
        parcel.writeString(this.answer);
    }
}