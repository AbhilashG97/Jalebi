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
package coursedetails;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import pojo.Course;
import studentdashboard.StudentDashboardController;

/**
 * FXML Controller class
 *
 * @author Abhilash G <abhilashg@am.students.amrita.edu>
 */
public class CourseDetailWindowController implements Initializable {
    
    @FXML private Label firstPeriodicalLabel;
    @FXML private Label secondPeriodicalLabel;
    @FXML private Label continousEvaluationLabel;
    @FXML private Label endSemesterLabel;
    @FXML private Label gradeLabel;
    @FXML private Button backButton;
    
    private Course course;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        onBackButtonPressed();
    }    
    
    public void initializeData(Course course) {
        this.course = course;
        displayData();
    }
    
    public void onBackButtonPressed() {
        backButton.setOnAction((event) -> {
            
            URL url = null;
            try {
                url = Paths.get("src/studentdashboard/StudentDashboard.fxml")
                        .toUri().toURL();
            } catch (MalformedURLException ex) {
                Logger.getLogger(CourseDetailWindowController.class.getName())
                        .log(Level.SEVERE, null, ex);
            }
            
            FXMLLoader loader = new FXMLLoader(url);

            Parent parent = null;
            try {
                parent = (Parent)loader.load();
            } catch (IOException ex) {
                Logger.getLogger(CourseDetailWindowController.class.getName())
                        .log(Level.SEVERE, null, ex);
            }
            
            Scene studentDashboardScene = new Scene(parent);
            StudentDashboardController controller = loader.getController();
            
            controller.restoreState();
            
            Stage stage = (Stage) ((Node) event.getSource())
                    .getScene().getWindow();
            stage.setScene(studentDashboardScene);
            stage.show();            
        });
    }
    
    private void displayData() {
        firstPeriodicalLabel.setText(String.valueOf(course.getMarks()
                .getFirstInternal()));
        secondPeriodicalLabel.setText(String.valueOf(course.getMarks()
                .getSecondInternal()));
        continousEvaluationLabel.setText(String.valueOf(course.getMarks()
                .getContinousEvaluationMarks()));
        endSemesterLabel.setText(String.valueOf(course.getMarks()
                .getEndSemester()));
        gradeLabel.setText(String.valueOf(course.getMarks()
                .getGrade()));        
    }
}
