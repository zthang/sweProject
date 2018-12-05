package com.example.sweproject.bean;

public class LoginMessage
{
    private int state;
    private String message;
    private String userName;
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

    public String getUserName() {
        return userName;
    }

    public void setState(int state) {
        this.state = state;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setAuthority(int authority) {
        this.authority = authority;
    }
}
