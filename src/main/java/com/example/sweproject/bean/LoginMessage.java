package com.example.sweproject.bean;

public class LoginMessage
{
    private int state;
    private String message;
    private int userID;
    private int authority;

    public int getAuthority() {
        return authority;
    }

    public int getState() {

        return state;
    }

    public String getMessage() {
        return message;
    }

    public int getUserID() {
        return userID;
    }

    public void setState(int state) {
        this.state = state;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setAuthority(int authority) {
        this.authority = authority;
    }
}
