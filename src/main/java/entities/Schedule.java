package entities;

import java.util.List;

public class Schedule {
    private int weekNumber;
    private List<Course> courseList;
    private List<Room> roomList;

    public Schedule() {
    }

    public Schedule(int weekNumber, List<Course> courseList, List<Room> roomList) {
        this.weekNumber = weekNumber;
        this.courseList = courseList;
        this.roomList = roomList;
    }

    public int getWeekNumber() {
        return weekNumber;
    }

    public void setWeekNumber(int weekNumber) {
        this.weekNumber = weekNumber;
    }

    public List<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }

    public List<Room> getRoomList() {
        return roomList;
    }

    public void setRoomList(List<Room> roomList) {
        this.roomList = roomList;
    }
}
