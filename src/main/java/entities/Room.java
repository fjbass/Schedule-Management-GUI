package entities;

public class Room {
  //private String location;
    private String block;
    private String floor;
    private String roomNumber;
    private int capacity;

    public Room() {
    }

    public Room(String block, String floor, String roomNumber, int capacity) {
        this.block = block;
        this.floor = floor;
        this.roomNumber = roomNumber;
        this.capacity = capacity;
        //this.location = location
    }

    public String getBlock()
    {
        return block;
    }

    public void setBlock(String block)
    {
        this.block = block;
    }

    public String getFloor()
    {
        return floor;
    }

    public void setFloor(String floor)
    {
        this.floor = floor;
    }

    public String getRoomNumber()
    {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber)
    {
        this.roomNumber = roomNumber;
    }

    /*public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }*/

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
