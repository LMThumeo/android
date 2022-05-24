package com.example.btl.model;

import java.io.Serializable;

public class StudentClass implements Serializable {

    private int id, studentId, classId;

    public StudentClass() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public StudentClass(int studentId, int classId) {
        this.studentId = studentId;
        this.classId = classId;
    }

    public StudentClass(int id, int studentId, int classId) {
        this.id = id;
        this.studentId = studentId;
        this.classId = classId;
    }
}
