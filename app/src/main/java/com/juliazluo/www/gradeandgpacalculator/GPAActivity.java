package com.juliazluo.www.gradeandgpacalculator;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

public class GPAActivity extends AppCompatActivity {

    DatabaseHelper db;
    GPAListAdapter adapter;
    ArrayList<Course> courses;
    public static String HELP_TYPE = "com.juliazluo.www.gradeandgpacalculator.HELP_TYPE";

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

        if (courses.size() == 0) {
            LinearLayout GPALayout = (LinearLayout) findViewById(R.id.GPA);
            TextView message = new TextView(this);
            message.setText("You need to add your courses first before calculating GPA.");
            message.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);
            GPALayout.addView(message, 0);
        }

        double totalWeightedGP = 0, totalUnweightedGP = 0, weightedGPA, unweightedGPA;
        int totalCourses = 0;
        double totalCredits = 0;
        ConvertPercentage convert;
        NumberFormat round = new DecimalFormat("#0.00");

        for (Course course: courses) {
            if (course.getAverage() != -1) {
                convert = new ConvertPercentage(course.getAverage());
                totalWeightedGP += convert.getGp() * course.getCredits();
                totalUnweightedGP += convert.getGp();
                totalCredits += course.getCredits();
                totalCourses ++;
            }
        }

        TextView weightedGPAText = (TextView) findViewById(R.id.weighted_GPA_text);
        TextView unweightedGPAText = (TextView) findViewById(R.id.unweighted_GPA_text);

        if (totalCourses == 0) {
            weightedGPAText.setText("Weighted GPA: N/A");
            unweightedGPAText.setText("Unweighted GPA: N/A");
        }
        else {
            weightedGPA = totalWeightedGP / totalCredits;
            unweightedGPA = totalUnweightedGP / totalCourses;

            weightedGPAText.setText("Weighted GPA: " + round.format(weightedGPA));
            unweightedGPAText.setText("Unweighted GPA: " + round.format(unweightedGPA));
        }
    }

    public void weightedGPAHelp (View view) {
        Intent intent = new Intent(GPAActivity.this, HelpActivity.class);
        intent.putExtra(HELP_TYPE, "WeightedGPA");
        startActivity(intent);
    }

    public void unweightedGPAHelp (View view) {
        Intent intent = new Intent(GPAActivity.this, HelpActivity.class);
        intent.putExtra(HELP_TYPE, "UnweightedGPA");
        startActivity(intent);
    }
}
