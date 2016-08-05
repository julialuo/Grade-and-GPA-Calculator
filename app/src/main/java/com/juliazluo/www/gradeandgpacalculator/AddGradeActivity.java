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
import android.widget.Toast;

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
        EditText editAssignmentName = (EditText) findViewById(R.id.edit_assignment_name);
        EditText editGrade = (EditText) findViewById(R.id.edit_grade);
        EditText editWeight = (EditText) findViewById(R.id.edit_weight);
        String assignmentName = editAssignmentName.getText().toString().trim();
        String gradeStr = editGrade.getText().toString().trim();
        String weightStr = editWeight.getText().toString().trim();
        double grade = -1;
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
                grade = Double.parseDouble(gradeStr);
                gradeError = false;
                weight = Integer.parseInt(weightStr);

                if (grade < 0 || grade > 100) {
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
            db.addGrade(courseID, assignmentName, grade, weight);
            Intent intent = new Intent(AddGradeActivity.this, GradesActivity.class);
            intent.putExtra(COURSE_ID, courseID);
            startActivity(intent);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(AddGradeActivity.this, GradesActivity.class);
                intent.putExtra(COURSE_ID, courseID);
                startActivity(intent);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
