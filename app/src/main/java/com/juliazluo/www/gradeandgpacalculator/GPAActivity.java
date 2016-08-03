package com.juliazluo.www.gradeandgpacalculator;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

public class GPAActivity extends AppCompatActivity {

    DatabaseHelper db;
    GPAListAdapter adapter;
    ArrayList<Course> courses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gpa);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        db = new DatabaseHelper(this);
        courses = db.getAllCourses();
        adapter = new GPAListAdapter(this, courses);
        ExpandableListView listView = (ExpandableListView) findViewById(R.id.GPA_list);
        listView.setAdapter(adapter);

        double totalGP = 0, GPA;
        double totalCredits = 0;
        ConvertPercentage convert;
        NumberFormat round = new DecimalFormat("#0.00");

        for (Course course: courses) {
            if (course.getAverage() != -1) {
                convert = new ConvertPercentage(course.getAverage());
                totalGP += convert.getGp() * course.getCredits();
                totalCredits += course.getCredits();
            }
        }

        GPA = totalGP/totalCredits;
        TextView GPAText = (TextView) findViewById(R.id.GPA_text);
        GPAText.setText("GPA: " + round.format(GPA));
    }
}
