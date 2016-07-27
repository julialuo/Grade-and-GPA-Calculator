package com.juliazluo.www.gradeandgpacalculator;

import android.provider.BaseColumns;

/**
 * Created by julia on 2016-07-27.
 */
public class CoursesTable {

    public CoursesTable(){}

    public static abstract class CoursesTableInfo implements BaseColumns {
        public static final String DATABASE_NAME = "course_data";
        public static final String TABLE_NAME = "course_names";
        public static final String COURSE_NAME = "course_name";
        public static final String COURSE_ID = "course_id";
    }
}
