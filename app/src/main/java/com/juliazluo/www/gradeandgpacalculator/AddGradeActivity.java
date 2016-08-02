package com.juliazluo.www.gradeandgpacalculator;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddGradeActivity extends AppCompatActivity {

    DatabaseHelper db;
    long courseID;
    public static String COURSE_ID = "com.juliazluo.www.gradeandgpacalculator.COURSE_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_grade);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        db = new DatabaseHelper(this);
        Intent intent = getIntent();
        courseID = intent.getExtras().getLong(GradesActivity.COURSE_ID);
    }

    public void addGrade(View view) {
        //BULLETPROOF FOR INVALID INPUT LATER
        EditText editAssignmentName = (EditText) findViewById(R.id.edit_assignment_name);
        EditText editGrade = (EditText) findViewById(R.id.edit_grade);
        EditText editWeight = (EditText) findViewById(R.id.edit_weight);
        String assignmentName = editAssignmentName.getText().toString();
        double assignmentGrade = Double.parseDouble(editGrade.getText().toString());
        int weight = Integer.parseInt(editWeight.getText().toString());

        db.addGrade(courseID, assignmentName, assignmentGrade, weight);
        Intent intent = new Intent(AddGradeActivity.this, GradesActivity.class);
        intent.putExtra(COURSE_ID, courseID);
        startActivity(intent);
    }

}
