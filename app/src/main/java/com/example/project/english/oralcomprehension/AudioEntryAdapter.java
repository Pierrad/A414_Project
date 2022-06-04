package com.example.project.english.oralcomprehension;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.project.R;
import com.example.project.english.quizz.QuizzEntrySimple;

import java.util.ArrayList;

public class AudioEntryAdapter extends ArrayAdapter<AudioEntryMultiple> {

    public AudioEntryAdapter(Context context, ArrayList<AudioEntryMultiple> entries) {
        super(context, 0, entries);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        AudioEntryMultiple entry = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.quizz_english_result_item, parent, false);
        }

        TextView answer = convertView.findViewById(R.id.englishQuizzResultAnswer);
        answer.setText(entry.getAnswer());
        return convertView;
    }
}