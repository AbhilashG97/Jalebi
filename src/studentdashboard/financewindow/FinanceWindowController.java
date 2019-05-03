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
package studentdashboard.financewindow;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import pojo.Course;
import pojo.Student;

/**
 * FXML Controller class
 *
 * @author Abhilash G <abhilashg@am.students.amrita.edu>
 */
public class FinanceWindowController implements Initializable {

    @FXML private ComboBox selectSemesterComboBox;
    @FXML private ListView fineListView;
    @FXML private Label currentSemesterFineLabel;
    @FXML private Label totalFineLabel;
    
    private Student student;
    
    private int selectedSemester;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       initializeComboBox();
    }
        
    public void initializeStudent(Student student) {
        this.student = student;
    }
    
    private void initializeComboBox() {
        selectSemesterComboBox.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8);
    }
    
    public void onComboBoxItemSelected(ActionEvent event) {
        selectedSemester = Integer.parseInt(selectSemesterComboBox
                .getSelectionModel().getSelectedItem().toString());
        displayData(selectedSemester);
    }

    public void displayData(int semester) {
        fineListView.getItems().clear();
        if (student.getFineMap().containsKey(selectedSemester)) {
            student.getFineMap().get(selectedSemester).getFineHashMap()
                   .forEach((key, value) -> {
                       fineListView.getItems().add(key + " " + value);
                   });
            
        } else {
            System.err.println("Ah oh, something is wrong!");
            fineListView.getItems().clear();
        }
    }    
}
