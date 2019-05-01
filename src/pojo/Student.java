/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 *
 * @author Abhilash G <abhilashg@am.students.amrita.edu>
 */
public class Student implements Serializable {
    
    private final String username;
    private String password;
    private LinkedHashMap<Integer, ArrayList<Course>> courseMap; 

    public Student(String username, String password, 
            LinkedHashMap<Integer, ArrayList<Course>> courseMap) {
        this.username = username;
        this.password = password;
        this.courseMap = courseMap;
    }

    public LinkedHashMap<Integer, ArrayList<Course>> getCourseMap() {
        return courseMap;
    }

    public void setCourseMap(LinkedHashMap<Integer, ArrayList<Course>> courseMap) {
        this.courseMap = courseMap;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }
    
    @Override
    public String toString() {
        return "Student{" + "username=" + username + ", password=" + password + 
                ", courseMap=" + courseMap + '}';
    }
 
}
