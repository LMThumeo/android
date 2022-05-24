package com.example.btl.model;

import java.io.Serializable;

public class Teacher implements Serializable {

    private int id;
    private String username, password, name, mail, seniority, department;

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

    public String getSeniority() {
        return seniority;
    }

    public void setSeniority(String seniority) {
        this.seniority = seniority;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Teacher(String username, String password, String name, String mail, String seniority, String department) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.mail = mail;
        this.seniority = seniority;
        this.department = department;
    }

    public Teacher(int id, String username, String password, String name, String mail, String seniority, String department) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.mail = mail;
        this.seniority = seniority;
        this.department = department;
    }

    public Teacher() {
    }
}
