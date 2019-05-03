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
package utilities;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import pojo.Student;

/**
 * This class contains useful helper methods that will be used throughout the
 * project
 * @author Abhilash G <abhilashg@am.students.amrita.edu>
 */
public class Utility {
    
    public static LinkedHashMap<String, ArrayList<Student>> getSortedStudents(String department,
                                                            ArrayList<Student> students) {
        
        LinkedHashMap<String, ArrayList<Student>> sortedStudentMap = new LinkedHashMap<>();

        ArrayList<Student> cseStudents = new ArrayList<>();
        ArrayList<Student> eceStudents = new ArrayList<>();
        ArrayList<Student> eeeStudents = new ArrayList<>();
        ArrayList<Student> meStudents = new ArrayList<>();

        for(Student student : students) {
            switch(student.getDepartment()) {
                case Constants.DEPARTMENT_CSE:
                    cseStudents.add(student);
                    sortedStudentMap.put(Constants.DEPARTMENT_CSE, 
                            cseStudents);
                    break;
                case Constants.DEPARTMENT_ECE:
                    eceStudents.add(student);
                    sortedStudentMap.put(Constants.DEPARTMENT_ECE, 
                            eceStudents);
                    break;
                case Constants.DEPARTMENT_EEE:
                    eeeStudents.add(student);
                    sortedStudentMap.put(Constants.DEPARTMENT_EEE, eeeStudents);
                    break;
                case Constants.DEPARTMENT_ME: 
                    meStudents.add(student);
                    sortedStudentMap.put(Constants.DEPARTMENT_ME, meStudents);
                    break;
                default:
                    break;
            }
        }
        return sortedStudentMap;
    } 
   
}
