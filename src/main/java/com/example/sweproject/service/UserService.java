package com.example.sweproject.service;

import com.example.sweproject.bean.Address;
import com.example.sweproject.bean.AddressList;
import com.example.sweproject.bean.User;
import com.example.sweproject.bean.UserInfo;

import java.util.ArrayList;

public interface UserService
{
    int addUser(User user);
    User getUserByUserName(String userName);

    int saveUserInfo(int userID,UserInfo userInfo);
    int insertUserAddress(int userID,Address address);
    int saveUserDormitory(int userID,Address address);
    UserInfo getUserInfo(int userID);
    AddressList getUserAddresses(int userID);
    ArrayList<String>getDormitoryList();
    Address getUserDormitory(int userID);
}
