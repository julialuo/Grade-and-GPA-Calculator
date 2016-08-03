package com.juliazluo.www.gradeandgpacalculator;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

/**
 * Created by julia on 2016-08-02.
 */
public class CoursesListAdapter extends ArrayAdapter<Course> {

    private final Context context;
    private final ArrayList<Course> courses;

    public CoursesListAdapter(Context context, ArrayList<Course> courseItems) {
        super(context, R.layout.courses_list_item, courseItems);
        this.context = context;
        this.courses = courseItems;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        NumberFormat round = new DecimalFormat("#0.00");

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.courses_list_item, parent, false);

        TextView courseName = (TextView) rowView.findViewById(R.id.course_list_name);
        TextView courseAverage = (TextView) rowView.findViewById(R.id.course_list_average);

        courseName.setText(courses.get(position).getName());

        if (courses.get(position).getAverage() == -1)
            courseAverage.setText("N/A");
        else
            courseAverage.setText(String.valueOf(round.format(courses.get(position).getAverage())) + "%");

        return rowView;
    }
}
