package com.juliazluo.www.gradeandgpacalculator;

/**
 * Created by julia on 2016-07-30.
 */
public class Course {

    private long id;
    private String name;

    public Course(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Course(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
