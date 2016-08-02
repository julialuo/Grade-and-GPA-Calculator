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
    private final ArrayList<Grade> grades;

    public GradesListAdapter(Context context, ArrayList<Grade> gradeItems) {
        super(context, R.layout.courses_list_item, gradeItems);
        this.context = context;
        this.grades = gradeItems;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.grades_list_item, parent, false);

        TextView assignmentName = (TextView) rowView.findViewById(R.id.grades_list_name);
        TextView grade = (TextView) rowView.findViewById(R.id.grades_list_grade);
        TextView weight = (TextView) rowView.findViewById(R.id.grades_list_weight);

        assignmentName.setText(grades.get(position).getAssignmentName());
        grade.setText(String.valueOf(grades.get(position).getGrade()) + "%");
        weight.setText(String.valueOf(grades.get(position).getWeight()) + "/" + GradesActivity.totalWeight);

        return rowView;
    }
}
