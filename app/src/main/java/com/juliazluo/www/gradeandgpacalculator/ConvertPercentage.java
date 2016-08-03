package com.juliazluo.www.gradeandgpacalculator;

/**
 * Created by julia on 2016-08-02.
 */
public class ConvertPercentage {

    private String letterGrade;
    private double gp;

    public ConvertPercentage(double percentage) {
        if(percentage >= 90) {
            letterGrade = "A+";
            gp = 4;
        }
        else if(percentage >= 85){
            letterGrade = "A";
            gp = 4;
        }
        else if(percentage >= 80){
            letterGrade = "A-";
            gp = 3.7;
        }
        else if(percentage >= 77){
            letterGrade = "B+";
            gp = 3.33;
        }
        else if(percentage >= 73){
            letterGrade = "B";
            gp = 3;
        }
        else if(percentage >= 70){
            letterGrade = "B-";
            gp = 2.7;
        }
        else if(percentage >= 67){
            letterGrade = "C+";
            gp = 2.3;
        }
        else if(percentage >= 63){
            letterGrade = "C";
            gp = 2;
        }
        else if(percentage >= 60){
            letterGrade = "C-";
            gp = 1.7;
        }
        else if(percentage >= 57){
            letterGrade = "D+";
            gp = 1.3;
        }
        else if(percentage >= 53){
            letterGrade = "D";
            gp = 1;
        }
        else if(percentage >= 50){
            letterGrade = "D-";
            gp = 0.7;
        }
        else {
            letterGrade = "F";
            gp = 0;
        }
    }

    public String getLetterGrade() {
        return letterGrade;
    }

    public double getGp() {
        return gp;
    }
}
