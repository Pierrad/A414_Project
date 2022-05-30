package com.example.project.english.quizz;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.project.R;

import java.util.ArrayList;
import java.util.List;

public class QuizzEntryAdapter extends ArrayAdapter<QuizzEntrySimple> {

    public QuizzEntryAdapter(Context context, ArrayList<QuizzEntrySimple> entries) {
        super(context, 0, entries);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        QuizzEntrySimple entry = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.quizz_english_result_item, parent, false);
        }

        TextView question = convertView.findViewById(R.id.englishQuizzResultQuestion);
        TextView answer = convertView.findViewById(R.id.englishQuizzResultAnswer);
        question.setText(entry.getQuestion());
        answer.setText(entry.getAnswer());
        return convertView;
    }
}
