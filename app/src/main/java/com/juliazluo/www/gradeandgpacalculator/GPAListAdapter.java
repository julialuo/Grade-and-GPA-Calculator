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
public class GPAListAdapter extends ArrayAdapter<Course> {

    //ADD CONCEPT OF CREDIT HOURS INTO CALCULATOR

    private final Context context;
    private final ArrayList<Course> courses;

    public GPAListAdapter(Context context, ArrayList<Course> courseItems) {
        super(context, R.layout.grades_gpa_list_item, courseItems);
        this.context = context;
        this.courses = courseItems;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        NumberFormat round = new DecimalFormat("#0.00");

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.grades_gpa_list_item, parent, false);

        TextView courseName = (TextView) rowView.findViewById(R.id.blank_1);
        TextView courseGrade = (TextView) rowView.findViewById(R.id.blank_2);
        TextView courseGP = (TextView) rowView.findViewById(R.id.blank_3);

        ConvertPercentage convert = new ConvertPercentage(courses.get(position).getAverage());

        courseName.setText(courses.get(position).getName());

        if (courses.get(position).getAverage() == -1) {
            courseGrade.setText("N/A");
            courseGP.setText("GP value: N/A");
        }
        else {
            courseGrade.setText(String.valueOf(round.format(courses.get(position).getAverage())) + "% (" + convert.getLetterGrade() + ")");
            courseGP.setText("GP: " + String.valueOf(convert.getGp()));
        }

        return rowView;
    }
}
