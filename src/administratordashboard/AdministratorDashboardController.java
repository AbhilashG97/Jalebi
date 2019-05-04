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
package administratordashboard;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import loginwindow.LoginWindowController;
import pojo.Fine;
import pojo.Student;
import utilities.Constants;
import utilities.CustomAlert;
import utilities.ObjectReaderWriter;

/**
 * FXML Controller class
 *
 * @author Abhilash G <abhilashg@am.students.amrita.edu>
 */
public class AdministratorDashboardController implements Initializable {
    
    @FXML private TextField usenameTextField;   
    @FXML private PasswordField passwordField;
    @FXML private TextField departmentTextField;
    @FXML private TextField fineTextField;
    @FXML private Button createStudentButton;
    @FXML private ComboBox selectSemesterComboBox;
    @FXML private MenuItem aboutMenuItem;
    @FXML private MenuItem logoutMenuItem;
    @FXML private MenuBar menuBar;
    
    private Student student;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initializeComboBox();
        onCreateStudentButtonClicked();
    }    
    
    public void initializeComboBox() {
        selectSemesterComboBox.getItems().addAll(1,2,3,4,5,6,7,8);
    }
    
    public final String getUsernameText() {
        return usenameTextField.getText();
    }
    
    public final String getDepartmentText() {
        return departmentTextField.getText();
    }
    
    public final String getFineText() {
        return fineTextField.getText();
    }
    
    public final String getEnteredPassword() {
        return passwordField.getText();
    }
    
    public void onCreateStudentButtonClicked() {
        createStudentButton.setOnAction((event) -> {
            student = new Student(getUsernameText(), getEnteredPassword(),
                                  getDepartmentText(), getFineMap());
            ObjectReaderWriter<ArrayList<Student>> objectReaderWriter 
                    = new ObjectReaderWriter<>("database.ser");
            ArrayList<Student> studentList 
                    = objectReaderWriter.readObjectFromFile();
            studentList.add(student);
            try {
                objectReaderWriter.writeObjectToFile(studentList);
            } catch (IOException ex) {
                Logger.getLogger(AdministratorDashboardController.class.getName())
                        .log(Level.SEVERE, null, ex);
            }
            
            new CustomAlert(Alert.AlertType.INFORMATION, "Student created successfully!!")
                    .showAlert();
            
            clearAllFields();
        });
    }
    
    private LinkedHashMap<Integer, Fine> getFineMap() {
        LinkedHashMap<Integer, Fine> fineMap = new LinkedHashMap<>();
        Fine fine;
        switch(getDepartmentText().toUpperCase()) {
            case Constants.DEPARTMENT_CSE:
                fine = new Fine();
                fine.setCSEDepartmentFine(Integer
                                .valueOf(getFineText()));
                fineMap.put(Integer.valueOf(selectSemesterComboBox.getSelectionModel()
                       .getSelectedItem().toString()),
                        fine);
                break;
                
            case Constants.DEPARTMENT_ECE:
                fine = new Fine();
                fine.setECEDepartmentFine(Integer
                                .valueOf(getFineText()));
                fineMap.put(Integer.valueOf(selectSemesterComboBox.getSelectionModel()
                       .getSelectedItem().toString()),
                        fine);
                break;
                
            case Constants.DEPARTMENT_EEE:
                fine = new Fine();
                fine.setEEEDepartmentFine(Integer
                                .valueOf(getFineText()));
                fineMap.put(Integer.valueOf(selectSemesterComboBox.getSelectionModel()
                       .getSelectedItem().toString()),
                        fine);
                break;
            
            case Constants.DEPARTMENT_ME:
                fine = new Fine();
                fine.setCSEDepartmentFine(Integer
                                .valueOf(getFineText()));
                fineMap.put(Integer.valueOf(selectSemesterComboBox.getSelectionModel()
                       .getSelectedItem().toString()),
                        fine);
                break;
        }
        return fineMap;
    }
    
    private void clearAllFields() {
        usenameTextField.setText("");
        passwordField.setText("");
        departmentTextField.setText("");
        selectSemesterComboBox.getSelectionModel().clearSelection();
        fineTextField.setText("");
    }
    
    public void logout(ActionEvent event) {

        URL url = null;
        try {
            url = Paths.get("src/loginwindow/LoginWindow.fxml")
                    .toUri().toURL();
        } catch (MalformedURLException ex) {
            Logger.getLogger(LoginWindowController.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(url);

        Parent parent = null;
        try {
            parent = loader.load(url);
        } catch (IOException ex) {
            Logger.getLogger(LoginWindowController.class.getName())
                    .log(Level.SEVERE, null, ex);
        }

        Scene studentDashboardScene = new Scene(parent);

        Stage stage = (Stage) menuBar.getScene().getWindow();
        stage.setScene(studentDashboardScene);
        stage.show();
    }

    public void about() {
        new CustomAlert(Alert.AlertType.INFORMATION, "Student Management System "
                + "V1.0\n" + "Alpha Version 1.0")
                .showAlert();
    }   
}
