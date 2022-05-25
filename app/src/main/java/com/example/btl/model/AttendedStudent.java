package com.example.btl.model;

import java.io.Serializable;

public class AttendedStudent implements Serializable {

    private Student student;
    private String date, time;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public AttendedStudent(Student student, String date, String time) {
        this.student = student;
        this.date = date;
        this.time = time;
    }

    public AttendedStudent() {

    }
}
