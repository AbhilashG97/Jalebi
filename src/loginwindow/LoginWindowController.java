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
package loginwindow;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pojo.Student;
import utilities.CustomAlert;
import utilities.MockData;
import utilities.ObjectReaderWriter;

/**
 * FXML Controller class
 *
 * @author Abhilash G <abhilashg@am.students.amrita.edu>
 */
public class LoginWindowController implements Initializable {

    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button loginButton;
    @FXML
    private Student selectedStudent;

    /**
     * Initializes the controller class
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        onLoginButtonClicked();
    }

    /**
     * This method listens for click events and changes the scene if the Student
     * is authenticated.
     */
    public void onLoginButtonClicked() {
        loginButton.setOnAction((event) -> {
            
            if(usernameTextField.getText().charAt(0) == 'A') {
                openAdministratorDashboard(event);
                return;
            }
            
            if (isUserAuthenticated()) {
                try {
                    new ObjectReaderWriter<Student>("dashboard_data.ser")
                            .writeObjectToFile(getSelectedStudent());
                } catch (IOException ex) {
                    Logger.getLogger(LoginWindowController.class.getName())
                            .log(Level.SEVERE, null, ex);
                }
                openStudentDashBoard(event);
            } else {
                new CustomAlert("Incorrect username or password entered!")
                        .showAlert();
                System.err.println("Ah oh, did you enter everything correctly?");
            }
        });
    }

    /**
     * A method that authenticated the user
     *
     * @return true if the user is authenticated else return false
     */
    private boolean isUserAuthenticated() {

        ArrayList<Student> data = new MockData().readStudentMockData();

        for (Student student : data) {
            if (student.getPassword()
                    .equals(passwordField.getText().trim())
                    && student.getUsername()
                            .equals(usernameTextField.getText().trim())) {
                setSelectedStudent(student);
                return true;
            }
        }
        return false;
    }

    /**
     * This is a helper method which opens the StudentDashboard Scene
     *
     * @param event is needed to access the stage
     */
    private void openStudentDashBoard(ActionEvent event) {

        URL url = null;
        try {
            url = Paths.get("src/studentdashboard/StudentDashboard.fxml")
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

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(studentDashboardScene);
        stage.show();
    }
    
    private void openAdministratorDashboard(ActionEvent event) {
        URL url = null;
        try {
            url = Paths.get("src/administratordashboard/AdministratorDashboard.fxml")
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

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(studentDashboardScene);
        stage.show();        
    }

    public void setSelectedStudent(Student student) {
        selectedStudent = student;
    }

    public Student getSelectedStudent() {
        return selectedStudent;
    }
}
