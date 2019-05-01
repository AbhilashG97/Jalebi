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
public class Course implements Serializable {
   
    private String courseName;
    private int credits;
    private String courseCode;
    private Marks marks;
    
    public Course(String courseName, int credits, String courseCode,
                  Marks marks) {
        this.courseName = courseName;
        this.credits = credits;
        this.courseCode = courseCode;
        this.marks = marks;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public Marks getMarks() {
        return marks;
    }

    public void setMarks(Marks marks) {
        this.marks = marks;
    }

    @Override
    public String toString() {
        return "Course{" + "courseName=" + courseName + ", credits=" + credits + ", courseCode=" + courseCode + ", marks=" + marks + '}';
    }
}
