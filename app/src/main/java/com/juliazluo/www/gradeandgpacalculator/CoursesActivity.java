package com.juliazluo.www.gradeandgpacalculator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class CoursesActivity extends AppCompatActivity {

    ArrayList<Course> courses = new ArrayList<Course>();
    ArrayList<String> courseNames = new ArrayList<String>();
    ArrayAdapter adapter;
    public static String COURSE_ID = "com.juliazluo.www.gradeandgpacalculator.COURSE_ID";
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Courses");

        db = new DatabaseHelper(this);
        courses = db.getAllCourses();

        for(Course course: courses) {
            courseNames.add(course.getName());
        }

        adapter = new ArrayAdapter<String>(this, R.layout.courses_list_item, courseNames);
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

        View addCourseView = LayoutInflater.from(this).inflate(R.layout.add_course_prompt, coursesLayout, false);
        coursesLayout.addView(addCourseView);
    }

    public void cancelAdd(View view) {
        LinearLayout coursesLayout = (LinearLayout) findViewById(R.id.courses);
        LinearLayout addCourseView = (LinearLayout) findViewById(R.id.add_course_prompt);
        coursesLayout.removeView(addCourseView);

        View newCourseBtn = LayoutInflater.from(this).inflate(R.layout.new_course_btn, coursesLayout, false);
        coursesLayout.addView(newCourseBtn);
    }

    public void addCourse(View view) {
        EditText editText = (EditText) findViewById(R.id.edit_course_name);
        String courseName = editText.getText().toString();
        db.addCourse(courseName);
        courses = db.getAllCourses();
        courseNames.add(courseName);
        adapter.notifyDataSetChanged();
        cancelAdd(view);
    }
}
