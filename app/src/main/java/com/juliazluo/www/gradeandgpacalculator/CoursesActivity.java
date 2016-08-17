package com.juliazluo.www.gradeandgpacalculator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.ArrayList;

public class CoursesActivity extends AppCompatActivity {

    ArrayList<Course> courses;
    CoursesListAdapter adapter;
    DatabaseHelper db;
    public static String COURSE_ID = "com.juliazluo.www.gradeandgpacalculator.COURSE_ID";
    public static String HELP_TYPE = "com.juliazluo.www.gradeandgpacalculator.HELP_TYPE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Courses");

        db = new DatabaseHelper(this);
        courses = new ArrayList<Course>();
        courses = db.getAllCourses();

        adapter = new CoursesListAdapter(this, courses);
        ListView listView = (ListView) findViewById(R.id.courses_list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

                Course course = courses.get(position);
                Intent intent = new Intent(CoursesActivity.this, GradesActivity.class);
                intent.putExtra(COURSE_ID, course.getId());
                startActivity(intent);
            }
        });

        LinearLayout coursesLayout = (LinearLayout) findViewById(R.id.courses);
        View newCourseBtn = LayoutInflater.from(this).inflate(R.layout.new_course_btn, coursesLayout, false);
        coursesLayout.addView(newCourseBtn);
    }

    public void newCourse(View view) {
        LinearLayout coursesLayout = (LinearLayout) findViewById(R.id.courses);
        LinearLayout newCourseBtn = (LinearLayout) findViewById(R.id.new_course_btn);
        coursesLayout.removeView(newCourseBtn);

        View newCourseView = LayoutInflater.from(this).inflate(R.layout.new_course_prompt, coursesLayout, false);
        coursesLayout.addView(newCourseView);
    }

    public void cancelAdd(View view) {
        LinearLayout coursesLayout = (LinearLayout) findViewById(R.id.courses);
        LinearLayout newCourseView = (LinearLayout) findViewById(R.id.new_course_prompt);
        coursesLayout.removeView(newCourseView);

        View newCourseBtn = LayoutInflater.from(this).inflate(R.layout.new_course_btn, coursesLayout, false);
        coursesLayout.addView(newCourseBtn);
    }

    public void addCourse(View view) {
        EditText editCourseName = (EditText) findViewById(R.id.edit_course_name);
        EditText editCredits = (EditText) findViewById(R.id.edit_credits);
        String courseName = editCourseName.getText().toString().trim();
        String creditsStr = editCredits.getText().toString().trim();
        double credits = -1;
        boolean validInput = true;

        if (courseName.equals("") || creditsStr.equals("")) {
            Toast.makeText(this, "Please fill all blanks", Toast.LENGTH_LONG).show();
            validInput = false;
        }
        else {
            try {
                credits = Double.parseDouble(creditsStr);

                if (credits <= 0) {
                    Toast.makeText(this, "Please enter a positive number for the credits",
                            Toast.LENGTH_LONG).show();
                    validInput = false;
                }
            }
            catch (NumberFormatException e) {
                Toast.makeText(this, "Please enter a positive number for the credits",
                        Toast.LENGTH_LONG).show();
                validInput = false;
            }
        }

        if (validInput) {
            db.addCourse(courseName, Double.parseDouble(creditsStr));
            courses.clear();
            courses.addAll(db.getAllCourses());
            adapter.notifyDataSetChanged();
            cancelAdd(view);
        }
    }

    public void creditHelp (View view) {
        Intent intent = new Intent(CoursesActivity.this, HelpActivity.class);
        intent.putExtra(HELP_TYPE, "Credit");
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(CoursesActivity.this, IntroActivity.class);
        startActivity(intent);
    }
}
