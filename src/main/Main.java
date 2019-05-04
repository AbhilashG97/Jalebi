/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utilities.MockData;

/**
 *
 * @author Abhilash G <abhilashg@am.students.amrita.edu>
 */
public class Main extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {

        URL url = Paths.get("src/loginwindow/LoginWindow.fxml")
                    .toUri().toURL();
        
        Parent root = FXMLLoader.load(url);
       
        Scene scene = new Scene(root);
        
        primaryStage.setTitle("Student Management System");
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        
        MockData mockData = new MockData();
        mockData.insertStudentMockData();
        mockData.insertFacultyMockData();
        mockData.writeAdministratorListToFile();
        launch(args);
    }
    
}
