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
import java.util.LinkedHashMap;

/**
 *
 * @author Abhilash G <abhilashg@am.students.amrita.edu>
 */
public class Student implements Serializable {
    
    private final String username;
    private String password;
    private String department;
    private LinkedHashMap<Integer, ArrayList<Course>> courseMap;
    private LinkedHashMap<Integer, Fine> fineMap;

    public Student(String username,
                   String password,
                   String department,
                   LinkedHashMap<Integer, ArrayList<Course>> courseMap,
                   LinkedHashMap<Integer, Fine> fineMap) {
        this.username = username;
        this.password = password;
        this.department = department;
        this.courseMap = courseMap;
        this.fineMap = fineMap;
    }

    public LinkedHashMap<Integer, ArrayList<Course>> getCourseMap() {
        return courseMap;
    }

    public void setCourseMap(LinkedHashMap<Integer, ArrayList<Course>> courseMap) {
        this.courseMap = courseMap;
    }

    public LinkedHashMap<Integer, Fine> getFineMap() {
        return fineMap;
    }

    public void setFineMap(LinkedHashMap<Integer, Fine> fineMap) {
        this.fineMap = fineMap;
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

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Student{" + "username=" + username + ", password=" + password 
                + ", department=" + department + ", courseMap=" + courseMap 
                + ", fineMap=" + fineMap + '}';
    }
}
