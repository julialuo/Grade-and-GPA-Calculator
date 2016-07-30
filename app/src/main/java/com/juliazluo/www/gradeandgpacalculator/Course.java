package com.juliazluo.www.gradeandgpacalculator;

/**
 * Created by julia on 2016-07-30.
 */
public class Course {

    private long id;
    private String name;
    private int average; //MAKE THIS DOUBLE IF DATABASE ALLOWS

    public Course(long id, String name, int average) {
        this.id = id;
        this.name = name;
        this.average = average;
    }

    public Course(String name, int average) {
        this.name = name;
        this.average = average;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getAverage() {
        return average;
    }

    public void setAverage(int average) {
        this.average = average;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
