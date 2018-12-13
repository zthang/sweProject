package com.example.sweproject.bean;

public class LoginMessage
{
    private int state;
    private String message;
    private int userID;
    private String  authority;
    private String nickname;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAuthority() {
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

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
