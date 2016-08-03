package com.juliazluo.www.gradeandgpacalculator;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by julia on 2016-08-02.
 */
public class GradesListAdapter extends ArrayAdapter<Grade> {

    private final Context context;
    private ArrayList<Grade> grades;

    public GradesListAdapter(Context context, ArrayList<Grade> gradeItems) {
        super(context, R.layout.list_item, gradeItems);
        this.context = context;
        this.grades = gradeItems;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_item, parent, false);

        TextView assignmentName = (TextView) rowView.findViewById(R.id.blank_1);
        TextView weight = (TextView) rowView.findViewById(R.id.blank_2);
        TextView grade = (TextView) rowView.findViewById(R.id.blank_3);

        assignmentName.setText(grades.get(position).getAssignmentName());
        weight.setText("Weight: " + String.valueOf(grades.get(position).getWeight()) + "/" + GradesActivity.totalWeight);
        grade.setText(String.valueOf(grades.get(position).getGrade()) + "%");

        return rowView;
    }
}
