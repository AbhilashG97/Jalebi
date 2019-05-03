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
package studentdashboard.announcementwindow;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import pojo.Faculty;
import pojo.Student;
import utilities.MockData;
import utilities.StudentReaderWriter;

/**
 * FXML Controller class
 *
 * @author Abhilash G <abhilashg@am.students.amrita.edu>
 */
public class AnnouncementWindowController implements Initializable {

    @FXML 
    private ListView announcementListView;
    
    @FXML
    private Button refreshButton;
    
    private Faculty faculty;
    private Student student;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initializeStudent();
        getFaculty();
        showAnnouncements();
    }    
    
    public void initializeStudent() {
        student = StudentReaderWriter.readStudentFromFile();
    }
    
    private Faculty getFaculty() {
      ArrayList<Faculty> facultyList = new MockData().readFacultyMockData();
        for(Faculty faculty : facultyList) {
            if(faculty.getDepartment().equals(student.getDepartment())) {
                this.faculty = faculty;
                break;
            }
        }
        return faculty;
    }
    
    public void showAnnouncements() {
        announcementListView.getItems().addAll(faculty.getAnnoucements());
    }
    
    public void onRefreshButtonClicked() {
        refreshButton.setOnAction((event) -> {
            announcementListView.getItems().clear();
            getFaculty();
            showAnnouncements();
        });
    }
    
}
