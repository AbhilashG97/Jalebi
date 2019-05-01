package studentdashboard;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
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
}
