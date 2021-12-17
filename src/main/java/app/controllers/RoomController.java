package app.controllers;

import app.entities.Room;
import app.utils.FileHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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

public class RoomController implements Initializable {
    @FXML
    TextField addBlockTextField;
    @FXML
    TextField addFloorTextField;
    @FXML
    TextField addRoomTextField;
    @FXML
    TextField addCapacityTextField;
    @FXML
    Button addRoomButton;
    @FXML
    TextField deleteBlockTextField;
    @FXML
    TextField deleteFloorTextField;
    @FXML
    TextField deleteRoomTextField;
    @FXML
    Button deleteRoomButton;

    @FXML
    private TableView<Room> roomsView;
    @FXML
    private TableColumn<Room, String> blockColumn;
    @FXML
    private TableColumn<Room, String> floorColumn;
    @FXML
    private TableColumn<Room, String> roomColumn;
    @FXML
    private TableColumn<Room, Integer> capacityColumn;

    private Room selectedRoom;
    private static final String ROOM_PATH = "src/main/resources/domain/rooms.txt";

    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        blockColumn.setCellValueFactory(new PropertyValueFactory<>("block"));
        floorColumn.setCellValueFactory(new PropertyValueFactory<>("floor"));
        roomColumn.setCellValueFactory(new PropertyValueFactory<>("roomNumber"));
        capacityColumn.setCellValueFactory(new PropertyValueFactory<>("capacity"));

        roomsView.setItems(getRooms());

        roomsView.setEditable(true);
        blockColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        floorColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        roomColumn.setCellFactory(TextFieldTableCell.forTableColumn());
    }

    public ObservableList<Room> getRooms() {
        ObservableList<Room> rooms = FXCollections.observableArrayList();

        List<String[]> roomArray = FileHandler.readDataFromFile(ROOM_PATH);
        roomArray.forEach(y -> rooms.add(new Room(y[0].substring(0, 1), y[0].split("\\.")[0].substring(1), y[0].split("\\.")[1], Integer.parseInt(y[1]))));
        return rooms;
    }

    @FXML
    public void changeBlock(TableColumn.CellEditEvent<Room, String> roomStringCellEditEvent) {
        selectedRoom = roomsView.getSelectionModel().getSelectedItem();
        String selectedRoomString = new Room(roomStringCellEditEvent.getOldValue(), selectedRoom.getFloor(), selectedRoom.getRoomNumber(), selectedRoom.getCapacity()).toString();
        String updatedRoomString = new Room(roomStringCellEditEvent.getNewValue(), selectedRoom.getFloor(), selectedRoom.getRoomNumber(), selectedRoom.getCapacity()).toString();
        FileHandler.updateDataFromFile(selectedRoomString, updatedRoomString, ROOM_PATH);

        selectedRoom.setBlock(roomStringCellEditEvent.getNewValue());
    }

    @FXML
    public void changeFloor(TableColumn.CellEditEvent<Room, String> roomStringCellEditEvent) {
        selectedRoom = roomsView.getSelectionModel().getSelectedItem();
        String selectedRoomString = new Room(selectedRoom.getBlock(), roomStringCellEditEvent.getOldValue(), selectedRoom.getRoomNumber(), selectedRoom.getCapacity()).toString();
        String updatedRoomString = new Room(selectedRoom.getBlock(), roomStringCellEditEvent.getNewValue(), selectedRoom.getRoomNumber(), selectedRoom.getCapacity()).toString();
        FileHandler.updateDataFromFile(selectedRoomString, updatedRoomString, ROOM_PATH);

        selectedRoom.setBlock(roomStringCellEditEvent.getNewValue());
    }

    @FXML
    public void changeRoomNumber(TableColumn.CellEditEvent<Room, String> roomStringCellEditEvent) {
        selectedRoom = roomsView.getSelectionModel().getSelectedItem();
        String selectedRoomString = new Room(selectedRoom.getBlock(), selectedRoom.getFloor(), roomStringCellEditEvent.getOldValue(), selectedRoom.getCapacity()).toString();
        String updatedRoomString = new Room(selectedRoom.getBlock(), selectedRoom.getFloor(), roomStringCellEditEvent.getNewValue(), selectedRoom.getCapacity()).toString();
        FileHandler.updateDataFromFile(selectedRoomString, updatedRoomString, ROOM_PATH);

        selectedRoom.setBlock(roomStringCellEditEvent.getNewValue());
    }

    @FXML
    public void addNewRoom() {
        Room newRoom = new Room(addBlockTextField.getText(), addFloorTextField.getText(),
                addRoomTextField.getText(), Integer.parseInt(addCapacityTextField.getText()));
        roomsView.getItems().add(newRoom);

        try {
            Files.write(Path.of(ROOM_PATH), (newRoom + "\n").getBytes(StandardCharsets.UTF_8),
                    StandardOpenOption.APPEND, StandardOpenOption.CREATE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void deleteRoomButton() {
        selectedRoom = roomsView.getSelectionModel().getSelectedItem();
        ObservableList<Room> roomList = roomsView.getItems();
        FileHandler.deleteSelectedDataFromFile(Objects.requireNonNull(selectedRoom), ROOM_PATH);

        roomList.remove(selectedRoom);
    }

    public void filterBySmallRooms() {
        roomsView.setItems(getRooms().filtered(z -> z.getCapacity() < 30));
    }

    public void filterByMediumRooms() {
        roomsView.setItems(getRooms().filtered(z -> z.getCapacity() < 50));
    }

    public void filterByBigRooms() {
        roomsView.setItems(getRooms().filtered(z -> z.getCapacity() < 130));
    }

    public void resetFilter() {
        roomsView.setItems(getRooms());
    }
}
