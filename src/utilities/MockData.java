/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
import pojo.Course;
import pojo.Marks;
import pojo.Student;

/**
 * This class generates mock data and inserts the data into a file.
 * @author Abhilash G <abhilashg@am.students.amrita.edu>
 */
public class MockData {
    
    private final String fileSeparator = System.getProperty("file.separator");
    
    private final String fileDataPath = "src" + fileSeparator + "data" 
                + fileSeparator + "database.ser";
    
    public void insertMockData() {
        ArrayList<Student> studentList = new ArrayList<>();
        studentList.add(new Student("Raghu", "maut", getCourseMap()));
        studentList.add(new Student("Pussy", "billi", getCourseMap()));
        studentList.add(new Student("Suyash", "chu", getCourseMap()));
        studentList.add(new Student("Sid", "01", getCourseMap()));
        studentList.add(new Student("James", "bond", getCourseMap()));
        studentList.add(new Student("Elia", "martel", getCourseMap()));
        
        try {
            writeStduentListToFile(studentList);
        } catch (IOException ex) {
            Logger.getLogger(MockData.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<Student> readMockData() {
        ObjectInputStream objectInputStream = null;
        try {
            objectInputStream = 
                    new ObjectInputStream(new FileInputStream(fileDataPath));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MockData.class.getName())
                    .log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MockData.class.getName())
                    .log(Level.SEVERE, null, ex);            
        }
        
        ArrayList<Student> data = null;
        try {
            data = (ArrayList<Student>)objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(MockData.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return data;
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
        LinkedHashMap<Integer, ArrayList<Course>> courseMap = 
                new LinkedHashMap<>();
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
    
    public void writeStduentListToFile(ArrayList<Student> studentList) 
            throws IOException {

        File file = new File(fileDataPath);
        
        System.out.println("Data Written to : " + fileDataPath);
        
        file.createNewFile();
        
        ObjectOutputStream objectWriter = 
                new ObjectOutputStream(new FileOutputStream(file));
        
        objectWriter.writeObject(studentList);
    }
}
