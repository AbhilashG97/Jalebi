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
package studentdashboard;

import coursedetails.CourseDetailWindowController;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import loginwindow.LoginWindowController;
import pojo.Course;
import pojo.Student;
import studentdashboard.financewindow.FinanceWindowController;
import utilities.CustomAlert;
import utilities.StudentReaderWriter;

/**
 * FXML Controller class
 *
 * @author Abhilash G <abhilashg@am.students.amrita.edu>
 */
public class StudentDashboardController implements Initializable {

    @FXML
    private ComboBox selectSemesterComboBox;
    @FXML
    private ListView subjectListView;

    @FXML
    private MenuItem aboutMenuItem;
    @FXML
    private MenuItem logoutMenuItem;

    @FXML
    private MenuBar dashboardMenuBar;

    @FXML 
    private Button coursesNavigationButton;
    
    @FXML
    private Button financeNavigationButton;
    
    @FXML
    private Button announcementNavigationButton;
    
    @FXML 
    private BorderPane borderPane;
    
    @FXML AnchorPane anchorPane;
    
    private Student student;
    private int selectedSemester;

    private final String fileSeparator = System.getProperty("file.separator");

    private final String fileDataPath = "src" + fileSeparator + "data"
            + fileSeparator + "dashboard_restore_data.dat";

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        student = StudentReaderWriter.readStudentFromFile();
        initializeComboBox();
        onListViewItemClickedTwice();
        onFinanceNavigationButtonClicked();
        onAnnouncementNavigationButtonClicked();
    }

    public void onComboBoxItemSelected(ActionEvent event) {
        selectedSemester = Integer.parseInt(selectSemesterComboBox
                .getSelectionModel().getSelectedItem().toString());
        displayData(selectedSemester);
    }

    private void initializeComboBox() {
        selectSemesterComboBox.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8);
    }

    public void displayData(int semester) {
        subjectListView.getItems().clear();
        if (student.getCourseMap().containsKey(selectedSemester)) {
            student.getCourseMap().forEach((key, value) -> {
                if (Integer.parseInt(key.toString()) == selectedSemester) {
                    for (Course course : value) {
                        subjectListView.getItems().add(course.getCourseName());
                    }
                }
            });

        } else {
            System.err.println("Ah oh, something is wrong!");
            subjectListView.getItems().clear();
        }
    }

    public void onListViewItemClickedTwice() {
        subjectListView.setOnMouseClicked((event) -> {
            if (event.getButton().equals(MouseButton.PRIMARY)) {
                if (event.getClickCount() == 2) {
                    System.out.println("Double clicked");
                    try {
                        writeEnteredDataToFile();
                    } catch (IOException ex) {
                        Logger.getLogger(StudentDashboardController.class.getName())
                                .log(Level.SEVERE, null, ex);
                    }

                    openCourseDetailWindow(event);
                }
            }
        });
    }

    private void openCourseDetailWindow(MouseEvent event) {

        Course selectedCourse = null;

        String selectedCourseName = subjectListView.getSelectionModel()
                .getSelectedItem().toString();

        ArrayList<Course> courseList = student.getCourseMap()
                .get(selectedSemester);

        for (Course course : courseList) {
            if (course.getCourseName().equals(selectedCourseName)) {
                selectedCourse = course;
            }
        }

        FXMLLoader loader = new FXMLLoader();

        URL url = null;
        try {
            url = Paths.get("src/coursedetails/CourseDetailWindow.fxml")
                    .toUri().toURL();
        } catch (MalformedURLException ex) {
            Logger.getLogger(StudentDashboardController.class.getName())
                    .log(Level.SEVERE, null, ex);
        }

        loader.setLocation(url);
        Parent parent = null;

        try {
            parent = loader.load();
        } catch (IOException ex) {
            Logger.getLogger(StudentDashboardController.class.getName())
                    .log(Level.SEVERE, null, ex);
        }

        Scene scene = new Scene(parent);

        CourseDetailWindowController controller = loader.getController();
        controller.initializeData(selectedCourse);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void restoreState() {
        try {
            selectedSemester = readEnteredDataFromFile();
        } catch (IOException ex) {
            Logger.getLogger(StudentDashboardController.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        selectSemesterComboBox.getSelectionModel().select(selectedSemester - 1);
        displayData(selectedSemester);
    }
    
    public void onCoursesNavigationButtonClicked(ActionEvent event) {
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
        restoreState();
    }
    
    public void onFinanceNavigationButtonClicked() {
        financeNavigationButton.setOnAction((event) -> {
        
        FXMLLoader loader = new FXMLLoader();

        URL url = null;
        try {
            url = Paths.get("src/studentdashboard/financewindow"
                    + "/FinanceWindow.fxml")
                    .toUri().toURL();
        } catch (MalformedURLException ex) {
            Logger.getLogger(StudentDashboardController.class.getName())
                    .log(Level.SEVERE, null, ex);
        }

        loader.setLocation(url);
        
        try {
            anchorPane = loader.load();
        } catch (IOException ex) {
            Logger.getLogger(StudentDashboardController.class.getName())
                    .log(Level.SEVERE, null, ex);
        }

            borderPane.setCenter(anchorPane);
        });
    }
    
    public void onAnnouncementNavigationButtonClicked() {
        announcementNavigationButton.setOnAction((event) -> {
                    FXMLLoader loader = new FXMLLoader();

            URL url = null;
            try {
                url = Paths.get("src/studentdashboard/announcementwindow"
                        + "/AnnouncementWindow.fxml")
                        .toUri().toURL();
            } catch (MalformedURLException ex) {
                Logger.getLogger(StudentDashboardController.class.getName())
                        .log(Level.SEVERE, null, ex);
            }

            loader.setLocation(url);

            try {
                anchorPane = loader.load();
            } catch (IOException ex) {
                Logger.getLogger(StudentDashboardController.class.getName())
                        .log(Level.SEVERE, null, ex);
            }

                borderPane.setCenter(anchorPane);
        });
    }
    
    private void writeEnteredDataToFile() throws IOException {

        File file = new File(fileDataPath);

        System.out.println("Selected Semester Written to : " + fileDataPath);

        file.createNewFile();
        DataOutputStream writer
                = new DataOutputStream(new FileOutputStream(file));
        System.out.println("Value written " + selectedSemester);
        writer.writeInt(selectedSemester);
    }

    private int readEnteredDataFromFile() throws IOException {

        DataInputStream reader
                = new DataInputStream(new FileInputStream(fileDataPath));
        int value = reader.readInt();
        return value;
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

        Stage stage = (Stage) dashboardMenuBar.getScene().getWindow();
        stage.setScene(studentDashboardScene);
        stage.show();
    }

    public void about() {
        new CustomAlert(Alert.AlertType.INFORMATION, "Student Management System "
                + "V1.0\n" + "Alpha Version 1.0")
                .showAlert();
    }
    
}
