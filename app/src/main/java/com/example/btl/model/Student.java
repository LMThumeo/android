package com.example.btl.model;

import java.io.Serializable;

public class Student implements Serializable {

    private int id;
    private String username, password, name, mail, phone;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Student(String username, String password, String name, String mail, String phone) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.mail = mail;
        this.phone = phone;
    }

    public Student(int id, String username, String password, String name, String mail, String phone) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.mail = mail;
        this.phone = phone;
    }

    public Student() {
    }
}
