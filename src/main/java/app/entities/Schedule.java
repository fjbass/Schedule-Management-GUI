package app.entities;

import java.util.List;

public class Schedule {
    private int weekNumber;
    private int week;
    private int hour;
    private List<Course> courseList;
    private List<Room> roomList;

    public Schedule() {
    }

    public Schedule(int weekNumber, int week, int hour, List<Course> courseList, List<Room> roomList) {
        this.weekNumber = weekNumber;
        this.week = week;
        this.hour = hour;
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

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }
}
