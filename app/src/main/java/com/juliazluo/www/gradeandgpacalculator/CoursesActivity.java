package com.juliazluo.www.gradeandgpacalculator;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class CoursesActivity extends AppCompatActivity {

    public static ArrayList<String> courseNames = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        courseNames.add("Science");
        courseNames.add("Computers");
        LinearLayout layout = (LinearLayout) findViewById(R.id.courses);

        for(int i = 0; i < courseNames.size(); i++) {
            Button courseBtn = new Button(this);
            courseBtn.setText(courseNames.get(i));
            layout.addView(courseBtn);
        }

        Button addCourse = new Button(this);
        addCourse.setText("Add new course");
        layout.addView(addCourse);
    }

}
