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
import android.widget.Toast;

public class ChangeGradeActivity extends AppCompatActivity {

    DatabaseHelper db;
    Grade grade;
    public static String COURSE_ID = "com.juliazluo.www.gradeandgpacalculator.COURSE_ID";
    public static String HELP_TYPE = "com.juliazluo.www.gradeandgpacalculator.HELP_TYPE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_grade);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Edit Grade");

        db = new DatabaseHelper(this);
        Intent intent = getIntent();
        long gradeID = intent.getExtras().getLong(GradesActivity.GRADE_ID);
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
        String assignmentName = changeAssignmentName.getText().toString().trim();
        String gradeStr = changeGrade.getText().toString().trim();
        String weightStr = changeWeight.getText().toString().trim();
        double gradeValue = -1;
        int weight = -1;
        boolean validInput = true;

        if (assignmentName.equals("") || gradeStr.equals("") ||
                weightStr.trim().equals("")) {
            Toast.makeText(this, "Please fill all blanks", Toast.LENGTH_LONG).show();
            validInput = false;
        }

        else {
            boolean gradeError = true; //used to tell where potential exception occurred

            try {
                gradeValue = Double.parseDouble(gradeStr);
                gradeError = false;
                weight = Integer.parseInt(weightStr);

                if (gradeValue < 0 || gradeValue > 100) {
                    Toast.makeText(this, "The grade must be a numerical value between 0 and 100",
                            Toast.LENGTH_LONG).show();
                    validInput = false;
                }

                else if (weight <= 0) {
                    Toast.makeText(this, "The weight must be a positive whole number",
                            Toast.LENGTH_LONG).show();
                    validInput = false;
                }

            }
            catch (NumberFormatException e) {
                if (gradeError)
                    Toast.makeText(this, "The grade must be a numerical value between 0 and 100",
                            Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(this, "The weight must be a positive whole number",
                            Toast.LENGTH_LONG).show();

                validInput = false;
            }
        }

        if (validInput) {
            grade.setAssignmentName(assignmentName);
            grade.setGrade(gradeValue);
            grade.setWeight(weight);
            db.updateGrade(grade);

            Intent intent = new Intent(ChangeGradeActivity.this, GradesActivity.class);
            intent.putExtra(COURSE_ID, grade.getCourseID());
            startActivity(intent);
        }
    }

    public void gradeHelp (View view) {
        Intent intent = new Intent(ChangeGradeActivity.this, HelpActivity.class);
        intent.putExtra(HELP_TYPE, "Grade");
        startActivity(intent);
    }

    public void weightHelp (View view) {
        Intent intent = new Intent(ChangeGradeActivity.this, HelpActivity.class);
        intent.putExtra(HELP_TYPE, "Weight");
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(ChangeGradeActivity.this, GradesActivity.class);
                intent.putExtra(COURSE_ID, grade.getCourseID());
                startActivity(intent);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
