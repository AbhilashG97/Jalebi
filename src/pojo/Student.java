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
public class Student implements Serializable {
    
    private final String username;
    private String password;

    public Student(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Student{" + "username=" + username + ", password=" + password + '}';
    }
}
