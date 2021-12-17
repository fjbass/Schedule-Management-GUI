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
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.ResourceBundle;

public class MainController implements Initializable {
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
    Label mondayMorningLabel;
    @FXML
    Label tuesdayMorningLabel;
    @FXML
    Label wednesdayMorningLabel;
    @FXML
    Label thursdayMorningLabel;
    @FXML
    Label fridayMorningLabel;

    @FXML
    Label mondayMiddayLabel;
    @FXML
    Label tuesdayMiddayLabel;
    @FXML
    Label wednesdayMiddayLabel;
    @FXML
    Label thursdayMiddayLabel;
    @FXML
    Label fridayMiddayLabel;

    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        fridayMiddayLabel.setText("aaaa");
        choiceCourses.getItems().addAll(getCourses());
        choiceRooms.getItems().addAll(getRooms());

        choiceWeek.getItems().addAll(List.of("Week 35", "Week 36", "Week 37"));
        choiceDay.getItems().addAll(List.of("Monday", "Tuesday", "Wednesday", "Thurday", "Friday"));
        choiceHours.getItems().addAll(List.of("08:20 - 11:50", "12:45 - 16:05"));
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


