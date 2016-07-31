package com.juliazluo.www.gradeandgpacalculator;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class GradeDetailsActivity extends AppCompatActivity {

    DatabaseHelper db;
    Grade grade;
    public static String COURSE_ID = "com.juliazluo.www.gradeandgpacalculator.COURSE_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        db = new DatabaseHelper(this);
        Intent intent = getIntent();
        long gradeID = intent.getExtras().getLong(GradesActivity.GRADE_ID);
        grade = db.getGrade(gradeID);
        setTitle(grade.getAssignmentName());

        TextView assignmentNameText = (TextView) findViewById(R.id.assignment_name_text);
        assignmentNameText.setText("Assignment name: " + grade.getAssignmentName());
        TextView gradeText = (TextView) findViewById(R.id.grade_text);
        gradeText.setText("Grade: " + grade.getGrade() + "%");
        TextView weightText = (TextView) findViewById(R.id.weight_text);
        weightText.setText("Weight: " + grade.getWeight()); //maybe display weight: value/total later
    }

    public void deleteGrade(View view) {
        Intent intent = new Intent(GradeDetailsActivity.this, GradesActivity.class);
        intent.putExtra(COURSE_ID, grade.getCourseID());
        startActivity(intent);

        db.deleteGrade(grade);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(GradeDetailsActivity.this, GradesActivity.class);
        intent.putExtra(COURSE_ID, grade.getCourseID());
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(GradeDetailsActivity.this, GradesActivity.class);
                intent.putExtra(COURSE_ID, grade.getCourseID());
                startActivity(intent);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
