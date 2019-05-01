/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo;

import java.io.Serializable;

/**
 *
 * @author Abhilash G <abhilashg@am.students.amrita.edu>
 */
public class Marks implements Serializable {

    private double firstInternal;
    private double secondInternal;
    private double endSemester;
    private double continousEvaluation;
    private double totalAverage;

    public Marks(double firstInternal,
                 double secondInternal,
                 double endSemester,
                 double continousEvaluation) {
        this.firstInternal = firstInternal;
        this.secondInternal = secondInternal;
        this.endSemester = endSemester;
        this.continousEvaluation = continousEvaluation;
    }
    
    public double getFirstInternal() {
        return firstInternal;
    }

    public void setFirstInternal(double value) {
        firstInternal = (value / 50) * 15;
    }

    public double getSecondInternal() {
        return secondInternal;
    }

    public void setSecondInternal(double value) {
        secondInternal = (value / 50) * 15;
    }

    public void setContinuousEvaluationMarks(double value) {
        continousEvaluation = value;
    }

    public double getContinousEvaluationMarks() {
        return continousEvaluation;
    }

    public double getEndSemester() {
        return endSemester;
    }

    public void setEndSemester(double value) {
        endSemester = value / 2;
    }

    public double getTotalAverage() {
        totalAverage = (getFirstInternal() + getFirstInternal() 
                + getEndSemester());
        return totalAverage;
    }

    public double getInternalMarks() {
        return getFirstInternal() + getSecondInternal();
    }

    public String getGrade() {
        if (totalAverage > 95) {
            return "O";
        } else if ((totalAverage < 95) && (totalAverage < 85)) {
            return "A+";
        } else if ((totalAverage < 85) && (totalAverage > 75)) {
            return "A";
        } else if ((totalAverage < 75) && (totalAverage > 65)) {
            return "B+";
        } else if ((totalAverage < 65) && (totalAverage > 55)) {
            return "B";
        } else if ((totalAverage < 55) && (totalAverage > 45)) {
            return "C";
        } else if ((totalAverage < 45) && (totalAverage > 35)) {
            return "P";
        } else {
            return "F";
        }
    }
    
    @Override
    public String toString() {
        return "\nInternal Marks : " + getInternalMarks() + 
                "\n End Semester Marks : " + getEndSemester()
                + "\n Average Marks: " + getTotalAverage() 
                + " \n" + "Grade: " + getGrade();
    }
}
