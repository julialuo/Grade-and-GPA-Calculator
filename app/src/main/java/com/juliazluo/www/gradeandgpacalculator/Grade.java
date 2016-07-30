package com.juliazluo.www.gradeandgpacalculator;

/**
 * Created by julia on 2016-07-30.
 */
public class Grade {

    private long id;
    private Course course;
    private String assignmentName;
    private int grade;
    private int weight;

    public Grade(Course course, String assignmentName, int grade, int weight) {
        this.course = course;
        this.assignmentName = assignmentName;
        this.grade = grade;
        this.weight = weight;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getAssignmentName() {
        return assignmentName;
    }

    public void setAssignmentName(String assignmentName) {
        this.assignmentName = assignmentName;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}