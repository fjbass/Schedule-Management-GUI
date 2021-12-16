package app;

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
import java.util.List;
import java.util.ResourceBundle;

public class RoomController implements Initializable {
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

    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        blockColumn.setCellValueFactory(new PropertyValueFactory<>("block"));
        floorColumn.setCellValueFactory(new PropertyValueFactory<>("floor"));
        roomColumn.setCellValueFactory(new PropertyValueFactory<>("roomNumber"));
        capacityColumn.setCellValueFactory(new PropertyValueFactory<>("capacity"));

        roomsTable.setItems(getRooms());
    }

    public ObservableList<Room> getRooms() {
        List<String[]> roomArray = new DataReader().readDataFromFile("src/main/resources/domain/rooms.txt");
        ObservableList<Room> rooms = FXCollections.observableArrayList();

        roomArray.forEach(y -> rooms.add(new Room(y[0].substring(0, 1), y[0].split("\\.")[0].substring(1), y[0].split("\\.")[1], Integer.parseInt(y[1]))));
        return rooms;
    }
}
