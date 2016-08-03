package com.juliazluo.www.gradeandgpacalculator;

/**
 * Created by julia on 2016-07-30.
 */
public class Course {

    private long id;
    private String name;
    private double average, credits;

    public Course(long id, String name, double average, double credits) {
        this.id = id;
        this.name = name;
        this.average = average;
        this.credits = credits;
    }

    public Course(String name, double average, double credits) {
        this.name = name;
        this.average = average;
        this.credits = credits;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCredits() {
        return credits;
    }

    public void setCredits(double credits) {
        this.credits = credits;
    }
}
