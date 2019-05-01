package loginwindow;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
import utilities.MockData;

/**
 * FXML Controller class
 *
 * @author Abhilash G <abhilashg@am.students.amrita.edu>
 */
public class LoginWindowController implements Initializable {
    
    @FXML private TextField usernameTextField;
    @FXML private PasswordField passwordField;
    @FXML private Button loginButton;
    
    /**
     * Initializes the controller class
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        onLoginButtonClicked();
    }    
    
    public void onLoginButtonClicked() {
        loginButton.setOnAction((event) -> {
            if(isUserAuthenticated()) {
                openStudentDashBoard(event); 
            } else {
                System.err.println("Ah oh, did you enter everything correctly?");
            }      
        });            
    }
    
    /**
     * A method that authenticated the user
     * @return true if the user is authenticated else return false
     */
    private boolean isUserAuthenticated() {
        ArrayList<Student> data = new MockData().readMockData();
        
        return data.stream().anyMatch((student) -> (student.getPassword()
                .equals(passwordField.getText().trim()) 
                && student.getUsername()
                        .equals(usernameTextField.getText().trim())));
    }
    
    /**
     * This is a helper method which opens the StudentDashboard Scene
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
        
        Parent parent = null;
        try {
            parent = FXMLLoader.load(url);
        } catch (IOException ex) {
            Logger.getLogger(LoginWindowController.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        
        Scene studentDashboardScene = new Scene(parent);
        
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(studentDashboardScene);
        stage.show();
    }   
}

