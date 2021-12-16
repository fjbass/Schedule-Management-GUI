package app.controllers;

import app.entities.Course;
import app.utils.DataReader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CourseController implements Initializable {
    @FXML
    private TableView<Course> tableView;
    @FXML
    private TableColumn<Course, String> nameColumn;
    @FXML
    private TableColumn<Course, String> teacherColumn;
    @FXML
    private TableColumn<Course, Integer> semesterColumn;
    @FXML
    private TableColumn<Course, String> classColumn;
    @FXML
    private TableColumn<Course, Integer> ectsColumn;

    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        teacherColumn.setCellValueFactory(new PropertyValueFactory<>("teacherIdentifier"));
        semesterColumn.setCellValueFactory(new PropertyValueFactory<>("semester"));
        classColumn.setCellValueFactory(new PropertyValueFactory<>("className"));
        ectsColumn.setCellValueFactory(new PropertyValueFactory<>("credits"));

        tableView.setItems(getCourses());
    }

    public ObservableList<Course> getCourses() {
        ObservableList<Course> courses = FXCollections.observableArrayList();

        List<String[]> arrayList = new DataReader().readDataFromFile("src/main/resources/domain/courses.txt");
        arrayList.forEach(z -> courses.add(new Course(Integer.parseInt(z[0]), z[1], z[2], z[3], Integer.parseInt(z[4]))));
        return courses;
    }
}
