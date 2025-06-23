package vn.edu.tlu.dothithanhloan.btlnhom17.model;

public class Room {
    private int id;
    private String roomNumber;

    public Room(int id, String roomNumber) {
        this.id = id;
        this.roomNumber = roomNumber;
    }

    public Room(String roomNumber) { // Constructor for inserting new room
        this.roomNumber = roomNumber;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getRoomNumber() { return roomNumber; }
    public void setRoomNumber(String roomNumber) { this.roomNumber = roomNumber; }



    @Override
    public String toString() {
        return roomNumber; // Useful for Spinner/Dropdown
    }
}