package app;

import entities.Course;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable{

    @FXML private TableView<Course> tableView;
    @FXML private TableColumn<Course, String> nameColumn;
    @FXML private TableColumn<Course, String> teacherColumn;
    @FXML private TableColumn<Course, Integer> semesterColumn;
    @FXML private TableColumn<Course, String> classColumn;
    @FXML private TableColumn<Course, Integer> ectsColumn;

    @Override
    public void initialize (URL url, ResourceBundle rb){
        System.out.println("q");
        System.out.println("q");
        System.out.println("q");
        System.out.println("q");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        teacherColumn.setCellValueFactory(new PropertyValueFactory<>("teacherIdentifier"));
        semesterColumn.setCellValueFactory(new PropertyValueFactory<>("semester"));
        classColumn.setCellValueFactory(new PropertyValueFactory<>("className"));
        ectsColumn.setCellValueFactory(new PropertyValueFactory<>("credits"));

        System.out.println("q");
        System.out.println("q");
        System.out.println("q");
        System.out.println("q");
//        System.out.println(new Course().getCourses());
        tableView.setItems(new Course().getCourses());
    }

}