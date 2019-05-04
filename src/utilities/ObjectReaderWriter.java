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

/**
 * This is a generic utility class which reads and writes 
 * objects to a file.
 * @author Abhilash G <abhilashg@am.students.amrita.edu>
 */
public class ObjectReaderWriter<T> {
    
    private final String FILE_SEPARATOR 
            = System.getProperty("file.separator");
    
    private String fileDatapath = "src" + FILE_SEPARATOR + "data" 
                + FILE_SEPARATOR;
    
    public ObjectReaderWriter(String fileName) {
        fileDatapath = fileDatapath + fileName;
    }
    
    
    /**
     * This method writes an object of type T to a file
     * @param t : The Object to be written to the file
     * @throws IOException 
     */
    public void writeObjectToFile(T t) throws IOException {

        File file = new File(fileDatapath);
        System.out.println("Data Written to : " + fileDatapath);
        file.createNewFile();
        ObjectOutputStream objectWriter = 
                new ObjectOutputStream(new FileOutputStream(file));

        objectWriter.writeObject(t);
    }
    
    /**
     * This method reads an object of type T from a file
     * @return : An object of type T that is read from a file is returned
     */
    public T readObjectFromFile() {

        ObjectInputStream objectInputStream = null;
        try {
            objectInputStream
                    = new ObjectInputStream(new FileInputStream(fileDatapath));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MockData.class.getName())
                    .log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MockData.class.getName())
                    .log(Level.SEVERE, null, ex);
        }

        T selectedObject = null;
        try {
            selectedObject = (T) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(MockData.class.getName())
                    .log(Level.SEVERE, null, ex);
        }

        return selectedObject;
    }
}
