package com.example.sweproject.service;

import com.example.sweproject.bean.Address;
import com.example.sweproject.bean.UserInfo;

import java.util.ArrayList;

public interface UserService
{
    int addUser(UserInfo userInfo);
    UserInfo getUserInfoByPhoneNumber(String phoneNumber);
    UserInfo getUserInfoByMail(String mail);
    UserInfo getUserInfoByNickname(String nickname);

    int saveUserInfo(UserInfo userInfo);
    int insertUserAddress(int userID,Address address);
    UserInfo getUserInfo(int userID);
    ArrayList<Address> getUserAddresses(int userID);
    ArrayList<String>getDormitoryList();

    int addUserCredit(int userID,int num);
    int addUserBalance(int userID,int num);
}
