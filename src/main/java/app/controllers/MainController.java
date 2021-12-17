package app.controllers;

import app.entities.Course;
import app.entities.Room;
import app.entities.Schedule;
import app.utils.FileHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    TableColumn mondayColumn;
    @FXML
    TableColumn tuesdayColumn;
    @FXML
    TableColumn wednesdayColumn;
    @FXML
    TableColumn thursdayColumn;
    @FXML
    TableColumn fridayColumn;
    @FXML
    TableView<Schedule> scheduleView;
    @FXML
    ChoiceBox choiceCourses;
    @FXML
    ChoiceBox choiceRooms;
    @FXML
    ChoiceBox choiceWeek;
    @FXML
    ChoiceBox choiceDay;
    @FXML
    ChoiceBox choiceHours;

    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        mondayColumn.setCellValueFactory(new PropertyValueFactory<>("mondaySchedule"));
        tuesdayColumn.setCellValueFactory(new PropertyValueFactory<>("tuesdaySchedule"));
        wednesdayColumn.setCellValueFactory(new PropertyValueFactory<>("wednesdaySchedule"));
        thursdayColumn.setCellValueFactory(new PropertyValueFactory<>("thursdaySchedule"));
        fridayColumn.setCellValueFactory(new PropertyValueFactory<>("fridaySchedule"));

        choiceCourses.getItems().addAll(getCourses());
        choiceRooms.getItems().addAll(getRooms());
        choiceWeek.getItems().addAll(List.of("Week 35", "Week 36", "Week 37"));
        choiceDay.getItems().addAll(List.of("Monday", "Tuesday", "Wednesday", "Thursday", "Friday"));
        choiceHours.getItems().addAll(List.of("08:20 - 11:50", "12:45 - 16:05"));

        choiceCourses.getSelectionModel().selectFirst();
        choiceRooms.getSelectionModel().selectFirst();
        choiceWeek.getSelectionModel().selectFirst();
        choiceDay.getSelectionModel().selectFirst();
        choiceHours.getSelectionModel().selectFirst();
    }

    @FXML
    public void addNewSchedule() {
        scheduleView.getItems().add(new Schedule());
    }

    public ObservableList<Course> getCourses() {
        ObservableList<Course> courses = FXCollections.observableArrayList();

        List<String[]> arrayList = FileHandler.readDataFromFile("src/main/resources/domain/courses.txt");
        arrayList.forEach(z -> courses.add(new Course(Integer.parseInt(z[0]), z[1], z[2], z[3], Integer.parseInt(z[4]))));
        return courses;
    }

    public ObservableList<Room> getRooms() {
        ObservableList<Room> rooms = FXCollections.observableArrayList();

        List<String[]> roomArray = FileHandler.readDataFromFile("src/main/resources/domain/rooms.txt");
        roomArray.forEach(y -> rooms.add(new Room(y[0].substring(0, 1), y[0].split("\\.")[0].substring(1), y[0].split("\\.")[1], Integer.parseInt(y[1]))));
        return rooms;
    }


}


