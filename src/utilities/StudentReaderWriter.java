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
import java.util.logging.Level;
import java.util.logging.Logger;
import pojo.Student;

/**
 * This class writes a Student to a file and also reads 
 * a Student from a file
 * @author Abhilash G <abhilashg@am.students.amrita.edu>
 */
public class StudentReaderWriter {
    
    private static final String FILE_SEPARATOR 
            = System.getProperty("file.separator");
    
    private static final String FILE_DATAPATH = "src" + FILE_SEPARATOR + "data" 
                + FILE_SEPARATOR + "dashboard_data.ser";
    
    /**
     * This method writes a Student object to a file
     * @param student : Student object to be written to a file
     * @throws IOException 
     */
    public static void writeStudentToFile(Student student) throws IOException {

        File file = new File(FILE_DATAPATH);
        System.out.println("Data Written to : " + FILE_DATAPATH);
        file.createNewFile();
        ObjectOutputStream objectWriter = 
                new ObjectOutputStream(new FileOutputStream(file));

        objectWriter.writeObject(student);
    }
    
    /**
     * This method reads a Student object from a file
     * @return : A Student object that is read from a file is returned
     */
    public static Student readStudentFromFile() {

        ObjectInputStream objectInputStream = null;
        try {
            objectInputStream
                    = new ObjectInputStream(new FileInputStream(FILE_DATAPATH));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MockData.class.getName())
                    .log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MockData.class.getName())
                    .log(Level.SEVERE, null, ex);
        }

        Student selectedStudent = null;
        try {
            selectedStudent = (Student) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(MockData.class.getName())
                    .log(Level.SEVERE, null, ex);
        }

        return selectedStudent;
    }
    
}
