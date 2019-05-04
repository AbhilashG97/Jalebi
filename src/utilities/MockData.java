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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import pojo.Administrator;
import pojo.Course;
import pojo.Faculty;
import pojo.Fine;
import pojo.Marks;
import pojo.Student;

/**
 * This class generates mock data and also provides methods which can be used 
 * to read/ write the mock data to a file. 
 *
 * @author Abhilash G <abhilashg@am.students.amrita.edu>
 */
public class MockData {

    private final String fileSeparator = System.getProperty("file.separator");

    private final String fileDataPath = "src" + fileSeparator + "data"
            + fileSeparator + "database.ser";
    
    public ArrayList<Student> getStudentList() {
        ArrayList<Student> studentList = new ArrayList<>();
        studentList.add(new Student("raghu", "maut", Constants.DEPARTMENT_CSE,
                getCourseMap(), getMockFineMap()));
        studentList.add(new Student("pussycat", "billi", Constants.DEPARTMENT_CSE,
                getCourseMap(), getMockFineMap()));
        studentList.add(new Student("suyash", "chu", Constants.DEPARTMENT_ECE,
                getCourseMap(), getMockFineMap()));
        studentList.add(new Student("sid", "01", Constants.DEPARTMENT_ECE,
                getCourseMap(), getMockFineMap()));
        studentList.add(new Student("james", "bond", Constants.DEPARTMENT_EEE,
                getCourseMap(), getMockFineMap()));
        studentList.add(new Student("elia", "martel", Constants.DEPARTMENT_EEE,
                getCourseMap(), getMockFineMap())); 
        
        return studentList;
    }
    
    public void insertStudentMockData() {   
        try {
            writeStudentListToFile(getStudentList());
        } catch (IOException ex) {
            Logger.getLogger(MockData.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Student> readStudentMockData() {
        ObjectInputStream objectInputStream = null;
        try {
            objectInputStream
                    = new ObjectInputStream(new FileInputStream(fileDataPath));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MockData.class.getName())
                    .log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MockData.class.getName())
                    .log(Level.SEVERE, null, ex);
        }

        ArrayList<Student> data = null;
        try {
            data = (ArrayList<Student>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(MockData.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return data;
    }

    public ArrayList<Faculty> getFacultyList() {
        ArrayList<Faculty> facultyList = new ArrayList<>();
        
        facultyList.add(new Faculty(Constants.DEPARTMENT_CSE, "Fraghu", "death",
        "Professor", Utility.getSortedStudents(Constants.DEPARTMENT_CSE,
                getStudentList()).get(Constants.DEPARTMENT_CSE), 
                getAnnouncementList()));
        
        facultyList.add(new Faculty(Constants.DEPARTMENT_CSE, "Fjames", "jam",
        "Professor", Utility.getSortedStudents(Constants.DEPARTMENT_CSE,
                getStudentList()).get(Constants.DEPARTMENT_CSE), 
                getAnnouncementList()));
        
        facultyList.add(new Faculty(Constants.DEPARTMENT_ECE, "Fyum", "yum",
        "Professor", Utility.getSortedStudents(Constants.DEPARTMENT_CSE,
                getStudentList()).get(Constants.DEPARTMENT_CSE), 
                getAnnouncementList()));
        
        facultyList.add(new Faculty(Constants.DEPARTMENT_ECE, "Fhawk", "hawk",
        "Professor", Utility.getSortedStudents(Constants.DEPARTMENT_CSE,
                getStudentList()).get(Constants.DEPARTMENT_CSE), 
                getAnnouncementList()));
        
        return facultyList;
    }
    
    public void insertFacultyMockData() throws IOException {
        
    String fileSeparator = System.getProperty("file.separator");

    String fileDataPath = "src" + fileSeparator + "data"
            + fileSeparator + "faculty_database.ser";
    
        File file = new File(fileDataPath);
        System.out.println("Data Written to : " + fileDataPath);
        file.createNewFile();
        ObjectOutputStream objectWriter
                = new ObjectOutputStream(new FileOutputStream(file));

        objectWriter.writeObject(getFacultyList());
    }
    
    public ArrayList<Faculty> readFacultyMockData() {
        
    String fileSeparator = System.getProperty("file.separator");

    String fileDataPath = "src" + fileSeparator + "data"
            + fileSeparator + "faculty_database.ser";        
        
        ObjectInputStream objectInputStream = null;
        try {
            objectInputStream
                    = new ObjectInputStream(new FileInputStream(fileDataPath));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MockData.class.getName())
                    .log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MockData.class.getName())
                    .log(Level.SEVERE, null, ex);
        }

        ArrayList<Faculty> data = null;
        try {
            data = (ArrayList<Faculty>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(MockData.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return data;
    }    
    
    public void writeAdministratorListToFile() {
        try {
            new ObjectReaderWriter<ArrayList<Administrator>>("admin_database.ser")
                    .writeObjectToFile(getAdministratorList());
        } catch (IOException ex) {
            Logger.getLogger(MockData.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<Administrator> readAdministratorListFromFile() {
        return new ObjectReaderWriter<ArrayList<Administrator>>("admin_database.ser")
                .readObjectFromFile();
    }
    
    public ArrayList<Administrator> getAdministratorList() {
        
        ArrayList<Administrator> administratorList = new ArrayList<>();
        administratorList.add(new Administrator("Araghu", "maut"));
        administratorList.add(new Administrator("Asid", "happy"));        
        return administratorList;
    }
    
    private ArrayList<Course> getMockCourseList() {
        ArrayList<Course> courseList = new ArrayList<>();

        courseList.add(new Course("Natural Language Processing",
                4, "CSE865", new Marks(49, 49, 89, 25), 80));
        courseList.add(new Course("Pattern Recognition",
                4, "CSE853", new Marks(49, 50, 99, 29), 90));
        courseList.add(new Course("Software Engineering",
                4, "CSE313", new Marks(50, 50, 100, 30), 75));
        courseList.add(new Course("Compiler Design",
                4, "CSE311", new Marks(49, 49, 89, 28), 85));
        courseList.add(new Course("Computer Networks",
                4, "CSE312", new Marks(49, 49, 89, 27), 77));
        courseList.add(new Course("Softskills",
                4, "SKL101", new Marks(49, 49, 89, 29), 88));
        return courseList;
    }

    private LinkedHashMap<Integer, ArrayList<Course>> getCourseMap() {
        LinkedHashMap<Integer, ArrayList<Course>> courseMap
                = new LinkedHashMap<>();
        courseMap.put(1, getMockCourseList());
        courseMap.put(2, getMockCourseList());
        courseMap.put(3, getMockCourseList());
        courseMap.put(4, getMockCourseList());
        courseMap.put(5, getMockCourseList());
        courseMap.put(6, getMockCourseList());
        courseMap.put(7, getMockCourseList());
        courseMap.put(8, getMockCourseList());

        return courseMap;
    }

    private LinkedHashMap<Integer, Fine> getMockFineMap() {
        LinkedHashMap<Integer, Fine> fineHashMap = new LinkedHashMap<>();
        fineHashMap.put(1, getMockFine());
        fineHashMap.put(2, getMockFine());
        fineHashMap.put(3, getMockFine());
        fineHashMap.put(4, getMockFine());
        fineHashMap.put(5, getMockFine());
        fineHashMap.put(6, getMockFine());
        fineHashMap.put(7, getMockFine());
        fineHashMap.put(8, getMockFine());
        return fineHashMap;
    }

    private Fine getMockFine() {
        Fine fine = new Fine();
        fine.setCSEDepartmentFine(100);
        fine.setLibraryFine(10000);
        fine.setEEEDepartmentFine(2000);
        fine.setECEDepartmentFine(3000);
        return fine;
    }
    
    private ArrayList<String> getAnnouncementList() {
        ArrayList<String> announcementList = new ArrayList<>();
        announcementList.add("Complete your software engineering assignments");
        announcementList.add("Prepare for lab exam on 23rd May");
        announcementList.add("Bring you lab records tomorrow");
        announcementList.add("Lab exam on UDP tomorrow. Best of luck!!");
        announcementList.add("There is compiler lab record assignment submission tomorrow");
        return announcementList;
    }
    
    private void writeStudentListToFile(ArrayList<Student> studentList)
            throws IOException {

        File file = new File(fileDataPath);
        System.out.println("Data Written to : " + fileDataPath);
        file.createNewFile();
        ObjectOutputStream objectWriter
                = new ObjectOutputStream(new FileOutputStream(file));

        objectWriter.writeObject(studentList);
    }
}
