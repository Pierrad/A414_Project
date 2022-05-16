package com.example.project.history.quizz;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.project.R;

import java.util.ArrayList;

public class QuizzEntriesAdapter extends ArrayAdapter<QuizzEntry> {

    public QuizzEntriesAdapter(Context context, ArrayList<QuizzEntry> entries) {
        super(context, 0, entries);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        QuizzEntry entry = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.quizz_history_result_item, parent, false);
        }

        TextView question = convertView.findViewById(R.id.quizzQuestionResultItem);
        TextView answer = convertView.findViewById(R.id.quizzAnswerResultItem);

        question.setText(entry.getQuestion());
        answer.setText(entry.getAnswer());

        return convertView;
    }
}