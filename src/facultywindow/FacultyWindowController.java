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
package facultywindow;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import pojo.Course;
import pojo.Student;
import utilities.ObjectReaderWriter;

/**
 * FXML Controller class
 *
 * @author Abhilash G <abhilashg@am.students.amrita.edu>
 */
public class FacultyWindowController implements Initializable {

    @FXML private TextField username;
    @FXML private ComboBox selectCoursesComboBox;
    @FXML private ComboBox selectSemesterComboBox;
    @FXML private TextField firstPeriodicalMarks;
    @FXML private TextField secondPeriodicalMarks;
    @FXML private TextField continousEvaluationMarks;
    @FXML private TextField endSemesterMarks;
    @FXML private TextField attendanceTextField;
    @FXML private Button doneButton;
    
    private Student selectedStudent;
    private int selectedSemester;
    private ArrayList<Course> selectedCourses;
    private ArrayList<Student> selectedStudents;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initializeSemesterComboBox();
        onDoneButtonClicked();
    }    
    
    @FXML
    public void onSemesterItemSelected(ActionEvent ae){
       System.out.println(username.getText());
       getSelectedStudent();
       selectCoursesComboBox.getItems().clear();
       onSemesterSelected();
       initializeCoursesComboBox();
    }    
    
    private Student getSelectedStudent() {
        ArrayList<Student> students 
                = new ObjectReaderWriter<ArrayList<Student>>("database.ser")
                        .readObjectFromFile();
        selectedStudents = students;
        for(Student student : students) {
            if(student.getUsername().equals(username.getText())) {
                selectedStudent = student;
            }
        }
        return selectedStudent;
    }
    
    public int onSemesterSelected() {
        selectedSemester = Integer.valueOf(selectSemesterComboBox
                .getSelectionModel().getSelectedItem().toString());
        return selectedSemester;
    }
    
    private void initializeSemesterComboBox() {
        selectSemesterComboBox.getItems().addAll(1,2,3,4,5,6,7,8);
    }
    
    private ArrayList<Course> setCourseList() {
        selectedCourses = selectedStudent.getCourseMap()
                .get(selectedSemester);
        return selectedCourses;
    }
    
    private void initializeCoursesComboBox() {
        ArrayList<String> list = selectedStudent.getCourseMap()
                .get(selectedSemester)
                .stream().map(Course::getCourseName)
                .collect(Collectors.toCollection(ArrayList::new));
        selectCoursesComboBox.getItems().addAll(list);
        setCourseList();
    }
    
    private void editAllDetails() {
        for(Course course : setCourseList()) {
            course.getMarks()
                    .setFirstInternal(Integer.parseInt(firstPeriodicalMarks
                            .getText()));
            course.getMarks()
                    .setSecondInternal(Integer.parseInt(firstPeriodicalMarks
                            .getText()));
            course.getMarks()
                    .setContinuousEvaluationMarks(Integer.parseInt(firstPeriodicalMarks
                            .getText()));
            course.getMarks()
                    .setEndSemester(Integer.parseInt(firstPeriodicalMarks
                            .getText()));
            course.setAttendance(Integer.parseInt(attendanceTextField.getText()));
        }
    }
    
    public void onDoneButtonClicked() {
        doneButton.setOnAction((event) -> {
            selectedStudents.set(0, selectedStudent);
            try {
                new ObjectReaderWriter<ArrayList<Student>>("database.ser")
                        .writeObjectToFile(selectedStudents);
            } catch (IOException ex) {
                Logger.getLogger(FacultyWindowController.class.getName())
                        .log(Level.SEVERE, null, ex);
            }
        });
    }
}
