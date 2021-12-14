package app;

import entities.Course;
import entities.Student;
import entities.Room;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import utils.DataReader;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {
//Course
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
//Student
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
    //Room
    @FXML
    private TableView<Room> roomsTable;
    @FXML
    private TableColumn<Room, String> blockColumn;
    @FXML
    private TableColumn<Room, String> floorColumn;
    @FXML
    private TableColumn<Room, String> roomColumn;
    @FXML
    private TableColumn<Room, Integer> capacityColumn;



    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        teacherColumn.setCellValueFactory(new PropertyValueFactory<>("teacherIdentifier"));
        semesterColumn.setCellValueFactory(new PropertyValueFactory<>("semester"));
        classColumn.setCellValueFactory(new PropertyValueFactory<>("className"));
        ectsColumn.setCellValueFactory(new PropertyValueFactory<>("credits"));

        tableView.setItems(getCourses());

        studentNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        studentNumberCol.setCellValueFactory(new PropertyValueFactory<>("studentNumber"));
        studentSemesterCol.setCellValueFactory(new PropertyValueFactory<>("semester"));
        studentClassCol.setCellValueFactory(new PropertyValueFactory<>("className"));

        studentsTable.setItems(getStudents());

        blockColumn.setCellValueFactory(new PropertyValueFactory<>("block"));
        floorColumn.setCellValueFactory(new PropertyValueFactory<>("floor"));
        roomColumn.setCellValueFactory(new PropertyValueFactory<>("roomNumber"));
        capacityColumn.setCellValueFactory(new PropertyValueFactory<>("capacity"));

        roomsTable.setItems(getRooms());

//        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());

    }

    public ObservableList<Course> getCourses() {
        ArrayList<String[]>  arrayList = new DataReader().readDataFromFile("src/main/resources/domain/courses.txt");
        ObservableList<Course> courses = FXCollections.observableArrayList();

        arrayList.forEach(z -> courses.add(new Course(Integer.parseInt(z[0]),z[1],z[2],z[3],Integer.parseInt(z[4]))));

        return courses;
    }

    public ObservableList<Student> getStudents() {
        ArrayList<String[]>  studentArray = new DataReader().readDataFromFile("src/main/resources/domain/students.txt");
        ObservableList<Student> students = FXCollections.observableArrayList();

        studentArray.forEach(n -> students.add(new Student(Integer.parseInt(n[0]),n[1], Integer.parseInt(n[2]),n[3])));

        return students;
    }

    public ObservableList<Room> getRooms() {
        ArrayList<String[]>  roomArray = new DataReader().readDataFromFile("src/main/resources/domain/rooms.txt");
        ObservableList<Room> rooms = FXCollections.observableArrayList();

        roomArray.forEach(y -> rooms.add(new Room(y[0].substring(0, 1),y[0].split("\\.")[0].substring(1),y[0].split("\\.")[1],Integer.parseInt(y[1]))));


        return rooms;
    }



}


