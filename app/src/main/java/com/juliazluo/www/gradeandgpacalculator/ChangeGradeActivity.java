package com.juliazluo.www.gradeandgpacalculator;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ChangeGradeActivity extends AppCompatActivity {

    DatabaseHelper db;
    Grade grade;
    public static String GRADE_ID = "com.juliazluo.www.gradeandgpacalculator.GRADE_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_grade);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        db = new DatabaseHelper(this);
        Intent intent = getIntent();
        long gradeID = intent.getExtras().getLong(GradeDetailsActivity.GRADE_ID);
        grade = db.getGrade(gradeID);

        EditText changeAssignmentName = (EditText) findViewById(R.id.edit_change_assignment_name);
        EditText changeGrade = (EditText) findViewById(R.id.edit_change_grade);
        EditText changeWeight = (EditText) findViewById(R.id.edit_change_weight);
        changeAssignmentName.setText(grade.getAssignmentName());
        changeGrade.setText(String.valueOf(grade.getGrade()));
        changeWeight.setText(String.valueOf(grade.getWeight()));
    }

    public void confirmChange(View view) {
        EditText changeAssignmentName = (EditText) findViewById(R.id.edit_change_assignment_name);
        EditText changeGrade = (EditText) findViewById(R.id.edit_change_grade);
        EditText changeWeight = (EditText) findViewById(R.id.edit_change_weight);
        String assignmentName = changeAssignmentName.getText().toString();
        double assignmentGrade = Double.parseDouble(changeGrade.getText().toString());
        int weight = Integer.parseInt(changeWeight.getText().toString());

        grade.setAssignmentName(assignmentName);
        grade.setGrade(assignmentGrade);
        grade.setWeight(weight);
        db.updateGrade(grade);

        Intent intent = new Intent(ChangeGradeActivity.this, GradeDetailsActivity.class);
        intent.putExtra(GRADE_ID, grade.getId());
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(ChangeGradeActivity.this, GradeDetailsActivity.class);
                intent.putExtra(GRADE_ID, grade.getId());
                startActivity(intent);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
