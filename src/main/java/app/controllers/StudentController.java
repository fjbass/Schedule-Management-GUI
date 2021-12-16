package app.controllers;

import entities.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import utils.DataReader;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class StudentController implements Initializable {
    @FXML
    private TableView<Student> studentsTable;
    @FXML
    private TableColumn<Student, String> studentNameCol;
    @FXML
    private TableColumn<Student, Integer> studentNumberCol;
    @FXML
    private TableColumn<Student, Integer> studentSemesterCol;
    @FXML
    private TableColumn<Student, String> studentClassCol;

    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        studentNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        studentNumberCol.setCellValueFactory(new PropertyValueFactory<>("studentNumber"));
        studentSemesterCol.setCellValueFactory(new PropertyValueFactory<>("semester"));
        studentClassCol.setCellValueFactory(new PropertyValueFactory<>("className"));

        studentsTable.setItems(getStudents());
    }

    public ObservableList<Student> getStudents() {
        ObservableList<Student> students = FXCollections.observableArrayList();

        List<String[]> studentArray = new DataReader().readDataFromFile("src/main/resources/domain/students.txt");
        studentArray.forEach(n -> students.add(new Student(Integer.parseInt(n[0]), n[1], Integer.parseInt(n[2]), n[3])));
        return students;
    }
}
