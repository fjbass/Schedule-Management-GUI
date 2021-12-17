package app.controllers;

import app.entities.Course;
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

public class CourseController implements Initializable {
    @FXML
    TextField addCourseCreditsTextField;
    @FXML
    TextField addCourseNameTextField;
    @FXML
    TextField addCourseTeacherTextField;
    @FXML
    TextField addCourseSemesterTextField;
    @FXML
    TextField addCourseClassTextField;

    @FXML
    TextField deleteCourseCreditsTextField;
    @FXML
    TextField deleteCreditsTextField;
    @FXML
    TextField deleteNameTextField;
    @FXML
    TextField deleteTeacherTextField;
    @FXML
    TextField deleteSemesterTextField;

    @FXML
    private TableView<Course> coursesView;
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

    private Course selectedCourse;
    private static final String COURSE_PATH = "src/main/resources/domain/courses.txt";

    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        teacherColumn.setCellValueFactory(new PropertyValueFactory<>("teacherIdentifier"));
        semesterColumn.setCellValueFactory(new PropertyValueFactory<>("semester"));
        classColumn.setCellValueFactory(new PropertyValueFactory<>("className"));
        ectsColumn.setCellValueFactory(new PropertyValueFactory<>("credits"));

        coursesView.setItems(getCourses());

        coursesView.setEditable(true);
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        classColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        teacherColumn.setCellFactory(TextFieldTableCell.forTableColumn());
    }

    public ObservableList<Course> getCourses() {
        ObservableList<Course> courses = FXCollections.observableArrayList();

        List<String[]> arrayList = FileHandler.readDataFromFile(COURSE_PATH);
        arrayList.forEach(z -> courses.add(new Course(Integer.parseInt(z[0]), z[1], z[2], z[3], Integer.parseInt(z[4]))));
        return courses;
    }

    public void changeCourseName(TableColumn.CellEditEvent<Course, String> courseStringCellEditEvent) {
        selectedCourse = coursesView.getSelectionModel().getSelectedItem();
        String selectedCourseString = new Course(selectedCourse.getSemester(), selectedCourse.getClassName(), courseStringCellEditEvent.getOldValue(), selectedCourse.getTeacherIdentifier(), selectedCourse.getCredits()).toString();
        String updatedCourseString = new Course(selectedCourse.getSemester(), selectedCourse.getClassName(), courseStringCellEditEvent.getNewValue(), selectedCourse.getTeacherIdentifier(), selectedCourse.getCredits()).toString();
        FileHandler.updateDataFromFile(selectedCourseString, updatedCourseString, COURSE_PATH);

        selectedCourse.setClassName(courseStringCellEditEvent.getNewValue());
    }

    public void changeClassName(TableColumn.CellEditEvent<Course, String> courseStringCellEditEvent) {
        selectedCourse = coursesView.getSelectionModel().getSelectedItem();
        String selectedCourseString = new Course(selectedCourse.getSemester(), courseStringCellEditEvent.getOldValue(), selectedCourse.getName(), selectedCourse.getTeacherIdentifier(), selectedCourse.getCredits()).toString();
        String updatedCourseString = new Course(selectedCourse.getSemester(), courseStringCellEditEvent.getNewValue(), selectedCourse.getName(), selectedCourse.getTeacherIdentifier(), selectedCourse.getCredits()).toString();
        FileHandler.updateDataFromFile(selectedCourseString, updatedCourseString, COURSE_PATH);

        selectedCourse.setClassName(courseStringCellEditEvent.getNewValue());
    }

    public void changeTeacherIdentifier(TableColumn.CellEditEvent<Course, String> courseStringCellEditEvent) {
        selectedCourse = coursesView.getSelectionModel().getSelectedItem();
        String selectedCourseString = new Course(selectedCourse.getSemester(), selectedCourse.getClassName(), selectedCourse.getName(), courseStringCellEditEvent.getOldValue(), selectedCourse.getCredits()).toString();
        String updatedCourseString = new Course(selectedCourse.getSemester(), selectedCourse.getClassName(), selectedCourse.getName(), courseStringCellEditEvent.getNewValue(), selectedCourse.getCredits()).toString();
        FileHandler.updateDataFromFile(selectedCourseString, updatedCourseString, COURSE_PATH);

        selectedCourse.setClassName(courseStringCellEditEvent.getNewValue());
    }

    @FXML
    public void addNewCourse() {
        Course newCourse = new Course(Integer.parseInt(addCourseSemesterTextField.getText()), addCourseClassTextField.getText(),
                addCourseNameTextField.getText(), addCourseTeacherTextField.getText(), Integer.parseInt(addCourseCreditsTextField.getText()));
        coursesView.getItems().add(newCourse);

        try {
            Files.write(Path.of(COURSE_PATH), ("\n" + newCourse).getBytes(StandardCharsets.UTF_8),
                    StandardOpenOption.APPEND, StandardOpenOption.CREATE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void deleteCourse() {
        selectedCourse = coursesView.getSelectionModel().getSelectedItem();
        ObservableList<Course> courseList = coursesView.getItems();
        FileHandler.deleteSelectedDataFromFile(Objects.requireNonNull(selectedCourse), COURSE_PATH);

        courseList.remove(selectedCourse);
    }

    public void resetFilter() {
        coursesView.setItems(getCourses());
    }

    public void filterBySDJ() {
        coursesView.setItems(getCourses().filtered(z -> z.getName().equals("SDJ")));
    }

    public void filterBySEP() {
        coursesView.setItems(getCourses().filtered(z -> z.getName().equals("SEP")));
    }

    public void filterByRWD() {
        coursesView.setItems(getCourses().filtered(z -> z.getName().equals("RWD")));
    }

    public void filterByDMA() {
        coursesView.setItems(getCourses().filtered(z -> z.getName().equals("DMA")));
    }
}
