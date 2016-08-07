package com.juliazluo.www.gradeandgpacalculator;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class HelpActivity extends AppCompatActivity {

    String helpType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        helpType = intent.getExtras().getString(AddGradeActivity.HELP_TYPE);

        TextView textView = (TextView) findViewById(R.id.credits_help_text);

        if (helpType.equals("Grade")) {
            textView = (TextView) findViewById(R.id.grade_help_text);
        }
        else if (helpType.equals("Weight")) {
            textView = (TextView) findViewById(R.id.weight_help_text);
        }
        else if (helpType.equals("WeightedGPA")) {
            textView = (TextView) findViewById(R.id.weighted_GPA_help_text);
        }
        else if (helpType.equals("UnweightedGPA")) {
            textView = (TextView) findViewById(R.id.unweighted_GPA_help_text);
        }

        focusOnView(textView);
    }

    private final void focusOnView(final TextView textView){
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                ScrollView scrollView = (ScrollView) findViewById(R.id.test);
                scrollView.smoothScrollTo(0, textView.getTop());
            }
        });
    }

}
