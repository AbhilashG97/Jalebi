/*
 * Copyright 2019 Abhilash G <abhilashg@am.students.amrita.edu>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package pojo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * A Faculty POJO which represents a faculty in a university
 * @author Abhilash G <abhilashg@am.students.amrita.edu>
 */
public class Faculty implements Serializable {
    
    private String department;
    private String username;
    private String password;
    private String employeePosition;
    private ArrayList<Student> studentList;
    
    public Faculty(String department,
                   String username,
                   String password,
                   String employeePosition,
                   ArrayList<Student> studentList) {
        this.department = department;
        this.username = username;
        this.password = password;
        this.employeePosition = employeePosition;
        this.studentList = studentList;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmployeePosition() {
        return employeePosition;
    }

    public void setEmployeePosition(String employeePosition) {
        this.employeePosition = employeePosition;
    }

    public ArrayList<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(ArrayList<Student> studentList) {
        this.studentList = studentList;
    }

    @Override
    public String toString() {
        return "Faculty{" + "department=" + department + ", username=" 
                + username + ", password=" + password + ", employeePosition=" 
                + employeePosition + ", studentList=" + studentList + '}';
    }    
}
