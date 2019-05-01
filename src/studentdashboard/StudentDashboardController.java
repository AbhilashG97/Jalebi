package studentdashboard;

import coursedetails.CourseDetailWindowController;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
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
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import pojo.Course;
import pojo.Student;
import utilities.MockData;

/**
 * FXML Controller class
 *
 * @author Abhilash G <abhilashg@am.students.amrita.edu>
 */
public class StudentDashboardController implements Initializable {

    @FXML
    private TextField semesterTextField;
    @FXML
    private Button getSubjectsButton;
    @FXML
    private ListView subjectListView;
    private Student student;
    private int selectedSemester;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        student = readFromDashboardData();
        onGetSubjectsButtonPressed();
        onListViewClickedTwice();
    }

    public void onGetSubjectsButtonPressed() {
        getSubjectsButton.setOnAction((event) -> {
            selectedSemester = Integer.parseInt(semesterTextField.getText());
            displayData(selectedSemester);
        });
    }

    public void displayData(int semester) {

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

    public void onListViewClickedTwice() {
        subjectListView.setOnMouseClicked((event) -> {
        if(event.getButton().equals(MouseButton.PRIMARY)){
            if(event.getClickCount() == 2){
                System.out.println("Double clicked");
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
        
        for(Course course : courseList) {
            if(course.getCourseName().equals(selectedCourseName)) {
                selectedCourse = course;
            }
        }
        
        System.out.println(selectedCourse);
        
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
        
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
    private Student readFromDashboardData() {

        String fileSeparator = System.getProperty("file.separator");

        String fileDataPath = "src" + fileSeparator + "data"
                + fileSeparator + "dashboard_data.ser";

        ObjectInputStream objectInputStream = null;
        try {
            objectInputStream
                    = new ObjectInputStream(new FileInputStream(fileDataPath));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MockData.class.getName())
                    .log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MockData.class.getName())
                    .log(Level.SEVERE, null, ex);
        }

        Student selectedStudent = null;
        try {
            selectedStudent = (Student) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(MockData.class.getName())
                    .log(Level.SEVERE, null, ex);
        }

        return selectedStudent;
    }
    
    public void restoreState() {
        
    }
    
}
