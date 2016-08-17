package com.juliazluo.www.gradeandgpacalculator;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

/**
 * Created by julia on 2016-08-14.
 */
public class GradesListAdapter extends BaseExpandableListAdapter {

    DatabaseHelper db;
    private final Context context;
    private ArrayList<Grade> grades;
    private NumberFormat round = new DecimalFormat("#0.00");
    private Activity activity;

    public GradesListAdapter(Context context, ArrayList<Grade> gradeItems, Activity activity) {
        this.context = context;
        this.grades = gradeItems;
        this.activity = activity;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_details, null);
        }

        TextView gradeDetails = (TextView) convertView.findViewById(R.id.details_1);
        TextView weightDetails = (TextView) convertView.findViewById(R.id.details_2);

        gradeDetails.setText("Grade: " + round.format(grades.get(groupPosition).getGrade()) + "%");
        weightDetails.setText("Weight: " + String.valueOf(grades.get(groupPosition).getWeight()) +
            "/" + String.valueOf(GradesActivity.totalWeight));

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    @Override
    public int getGroupCount() {
        return grades.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return grades.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return grades.get(groupPosition).getId();
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {

        db = new DatabaseHelper(context);

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.grades_list_item, null);
        }

        TextView assignmentName = (TextView) convertView.findViewById(R.id.grades_list_name);
        TextView assignmentGrade = (TextView) convertView.findViewById(R.id.grades_list_grade);
        ImageButton editBtn = (ImageButton) convertView.findViewById(R.id.grades_list_edit);
        ImageButton deleteBtn = (ImageButton) convertView.findViewById(R.id.grades_list_delete);

        assignmentName.setText(grades.get(groupPosition).getAssignmentName());
        assignmentGrade.setText(round.format(grades.get(groupPosition).getGrade()) + "%");
        editBtn.setFocusable(false);
        deleteBtn.setFocusable(false);

        editBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(context, ChangeGradeActivity.class);
                intent.putExtra(GradesActivity.GRADE_ID, grades.get(groupPosition).getId());
                context.startActivity(intent);
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
                alertBuilder.setMessage("Are you sure you wish to delete '" + grades.get(groupPosition).getAssignmentName() +
                        "'?");
                alertBuilder.setCancelable(true);

                alertBuilder.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                db.deleteGrade(grades.get(groupPosition));
                                grades.remove(groupPosition);
                                notifyDataSetChanged();

                                if (grades.size() == 0) {
                                    LinearLayout gradesLayout = (LinearLayout) activity.findViewById(R.id.grades);
                                    TextView message = new TextView(context);
                                    message.setText("There are no grades for this course yet.");
                                    message.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);
                                    gradesLayout.addView(message, 0);
                                }
                            }
                        });

                alertBuilder.setNegativeButton(
                        "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert = alertBuilder.create();
                alert.show();
            }
        });

        return convertView;
    }
}
