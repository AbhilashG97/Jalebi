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

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import pojo.Fine;
import pojo.Student;
import utilities.Constants;
import utilities.StudentReaderWriter;

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
       initializeStudent();
    }
        
    public void initializeStudent() {
        student = StudentReaderWriter.readStudentFromFile();
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
        showCurrentSemesterFine();
        showTotalFine();
        if (student.getFineMap().containsKey(selectedSemester)) {
            student.getFineMap().get(selectedSemester).getFineHashMap()
                   .forEach((key, value) -> {
                       if(value != -1) {
                           fineListView.getItems().add(key + " " + value);
                       }
                   });
            
        } else {
            System.err.println("Ah oh, something is wrong!");
            fineListView.getItems().clear();
        }
    }

    private void showTotalFine() {
        LinkedHashMap<Integer, Fine> fineMap = student.getFineMap();
        int totalFine = 0;
        for(Fine fine : fineMap.values()) {
            for(int amount : fine.getFineHashMap().values()) {
                if(amount != -1) {
                    totalFine+=amount;
                }                
            }
        }
        try {
            totalFineLabel.setText(Constants.getRupeeSymbol()
                    + "" + totalFine);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(FinanceWindowController.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }
    
    private void showCurrentSemesterFine() {
        int currentSemesterFine = 0;
        if (student.getFineMap().containsKey(selectedSemester)) {
            for(int fine : student.getFineMap().get(selectedSemester)
                    .getFineHashMap().values()) {
                if(fine != -1) {
                    currentSemesterFine += fine;
                }
            }
        }
        try {
            currentSemesterFineLabel.setText(Constants.getRupeeSymbol()
                    + "" + currentSemesterFine);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(FinanceWindowController.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }
    
}
