package com.example.project.english.oralcomprehension;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.Collections;

public class AudioEntryMultiple implements Parcelable {
    private int audioId;
    private String answer;
    private ArrayList<String> possibleAnswers;

    public AudioEntryMultiple(int audioId, String answer, ArrayList<String> possibleAnswers) {
        this.audioId = audioId;
        this.answer = answer;
        this.possibleAnswers = possibleAnswers;
        Collections.shuffle(this.possibleAnswers);
    }

    protected AudioEntryMultiple(Parcel in) {
        audioId = in.readInt();
        answer = in.readString();
        possibleAnswers = in.createStringArrayList();
    }

    public static final Creator<AudioEntryMultiple> CREATOR = new Creator<AudioEntryMultiple>() {
        @Override
        public AudioEntryMultiple createFromParcel(Parcel in) {
            return new AudioEntryMultiple(in);
        }

        @Override
        public AudioEntryMultiple[] newArray(int size) {
            return new AudioEntryMultiple[size];
        }
    };

    public int getAudioId() {
        return audioId;
    }

    public String getAnswer() {
        return answer;
    }

    public ArrayList<String> getPossibleAnswers() {
        return possibleAnswers;
    }

    @Override
    public String toString() {
        return this.audioId + " -> " + this.answer;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.audioId);
        parcel.writeString(this.answer);
        parcel.writeStringList(this.possibleAnswers);
    }
}