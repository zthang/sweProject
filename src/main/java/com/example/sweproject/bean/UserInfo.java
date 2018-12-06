package com.example.sweproject.bean;

import java.util.ArrayList;

public class UserInfo
{
    private String nickname;
    private String sex;
    private String phoneNumber;
    private String mail;
    private String studentID;
    private String department;

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPhoneNumber() {

        return phoneNumber;
    }

    public String getMail() {
        return mail;
    }

    public String getStudentID() {
        return studentID;
    }

    public String getDepartment() {
        return department;
    }

    public String getNickname() {
        return nickname;
    }
}
