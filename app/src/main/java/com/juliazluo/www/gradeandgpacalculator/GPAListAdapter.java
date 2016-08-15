package com.juliazluo.www.gradeandgpacalculator;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

/**
 * Created by julia on 2016-08-02.
 */
public class GPAListAdapter extends BaseExpandableListAdapter {

    private final Context context;
    private ArrayList<Course> courses;

    public GPAListAdapter(Context context, ArrayList<Course> courseItems) {
        this.context = context;
        this.courses = courseItems;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        NumberFormat round = new DecimalFormat("#0.00");

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_details, null);
        }

        TextView averageDetails = (TextView) convertView.findViewById(R.id.details_1);
        TextView creditDetails = (TextView) convertView.findViewById(R.id.details_2);

        ConvertPercentage convert = new ConvertPercentage(courses.get(groupPosition).getAverage());

        if (courses.get(groupPosition).getAverage() != -1)
            averageDetails.setText("Grade: " + round.format(courses.get(groupPosition).getAverage())
                    + "% (" + convert.getLetterGrade() + ")");
        else
            averageDetails.setText("Grade: N/A");
        creditDetails.setText("Credits: " + courses.get(groupPosition).getCredits());

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    @Override
    public int getGroupCount() {
        return courses.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return courses.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return courses.get(groupPosition).getId();
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.gpa_list_item, null);
        }

        TextView courseName = (TextView) convertView.findViewById(R.id.GPA_list_name);
        TextView courseGP = (TextView) convertView.findViewById(R.id.GPA_list_score);
        ConvertPercentage convert = new ConvertPercentage(courses.get(groupPosition).getAverage());

        courseName.setText(courses.get(groupPosition).getName());

        if (courses.get(groupPosition).getAverage() == -1)
            courseGP.setText("GP: N/A");
        else
            courseGP.setText("GP: " + String.valueOf(convert.getGp()));

        return convertView;
    }
}
