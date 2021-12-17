package app.controllers;

import app.entities.Student;
import app.utils.FileHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class StudentController implements Initializable {
    @FXML
    private TextField deleteStudentTextField;
    @FXML
    private TextField studentNameTextField;
    @FXML
    private TextField studentNumberTextField;
    @FXML
    private TextField studentSemesterTextField;
    @FXML
    private TextField studentClassTextField;
    @FXML
    private TableView<Student> studentsView;
    @FXML
    private TableColumn<Student, String> studentNameCol;
    @FXML
    private TableColumn<Student, Integer> studentNumberCol;
    @FXML
    private TableColumn<Student, Integer> studentSemesterCol;
    @FXML
    private TableColumn<Student, String> studentClassCol;

    private static final String STUDENT_PATH = "src/main/resources/domain/students.txt";

    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        studentNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        studentNumberCol.setCellValueFactory(new PropertyValueFactory<>("studentNumber"));
        studentSemesterCol.setCellValueFactory(new PropertyValueFactory<>("semester"));
        studentClassCol.setCellValueFactory(new PropertyValueFactory<>("className"));

        studentsView.setItems(getStudents());

        studentsView.setEditable(true);
        studentClassCol.setCellFactory(TextFieldTableCell.forTableColumn());
    }

    public ObservableList<Student> getStudents() {
        ObservableList<Student> students = FXCollections.observableArrayList();

        List<String[]> studentArray = FileHandler.readDataFromFile(STUDENT_PATH);
        studentArray.forEach(n -> students.add(new Student(Integer.parseInt(n[0]), n[1], Integer.parseInt(n[2]), n[3])));
        return students;
    }

    @FXML
    public void changeClassNameCellEvent(TableColumn.CellEditEvent<Student, String> studentStringCellEditEvent) {
        Student selectedStudent = studentsView.getSelectionModel().getSelectedItem();
        String selectedStudentString = new Student(selectedStudent.getSemester(), studentStringCellEditEvent.getOldValue(), selectedStudent.getStudentNumber(), selectedStudent.getName()).toString();
        String updatedStudentString = new Student(selectedStudent.getSemester(), studentStringCellEditEvent.getNewValue(), selectedStudent.getStudentNumber(), selectedStudent.getName()).toString();
        FileHandler.updateDataFromFile(selectedStudentString, updatedStudentString, STUDENT_PATH);

        selectedStudent.setClassName(studentStringCellEditEvent.getNewValue());
    }

    @FXML
    public void addNewStudent() {
        Student newStudent = new Student(Integer.parseInt(studentSemesterTextField.getText()), studentNameTextField.getText(),
                Integer.parseInt(studentNumberTextField.getText()), studentClassTextField.getText() + "\n");
        studentsView.getItems().add(newStudent);

        try {
            Files.write(Path.of(STUDENT_PATH), newStudent.toString().getBytes(StandardCharsets.UTF_8),
                    StandardOpenOption.APPEND, StandardOpenOption.CREATE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void deleteButtonPushed() {
        int studentNumber = !deleteStudentTextField.getText().equals("") ? Integer.parseInt(deleteStudentTextField.getText())
                : studentsView.getSelectionModel().getSelectedItem().getStudentNumber();

        ObservableList<Student> studentList = studentsView.getItems();
        Student selectedStudentByNumber = studentList.stream().filter(e -> e.getStudentNumber() == studentNumber).findFirst().orElse(null);
        FileHandler.deleteSelectedDataFromFile(Objects.requireNonNull(selectedStudentByNumber), STUDENT_PATH);

        studentList.remove(selectedStudentByNumber);
    }
}
