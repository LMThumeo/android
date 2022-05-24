package com.example.btl.model;

import java.io.Serializable;

public class StudyClass implements Serializable {

    private int id, teacherId;
    private String subject, group, room, day, timeStart, timeEnd;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }

    public StudyClass(int teacherId, String subject, String group, String room, String day, String timeStart, String timeEnd) {
        this.teacherId = teacherId;
        this.subject = subject;
        this.group = group;
        this.room = room;
        this.day = day;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
    }

    public StudyClass(int id, int teacherId, String subject, String group, String room, String day, String timeStart, String timeEnd) {
        this.id = id;
        this.teacherId = teacherId;
        this.subject = subject;
        this.group = group;
        this.room = room;
        this.day = day;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
    }

    public StudyClass() {

    }
}
