package com.example.sweproject.bean;

import java.util.ArrayList;

public class User
{
    private Integer userID;
    private String userName;
    private String password;
    private int authority;
    private UserInfo userInfo;
    private ArrayList<Address> addresses;
    private Address community;

    public void setAddresses(ArrayList<Address> addresses) {
        this.addresses = addresses;
    }

    public void setCommunity(Address community) {
        this.community = community;
    }

    public ArrayList<Address> getAddresses() {

        return addresses;
    }

    public Address getCommunity() {
        return community;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public UserInfo getUserInfo() {

        return userInfo;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public void setAuthority(int authority) {
        this.authority = authority;
    }

    public int getAuthority() {
        return authority;
    }
}
