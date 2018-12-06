package com.example.sweproject.service;

import com.example.sweproject.bean.Address;
import com.example.sweproject.bean.User;
import com.example.sweproject.bean.UserInfo;

public interface UserService
{
    int addUser(User user);
    User getUserByUserName(String userName);

    int saveUserInfo(int userID,UserInfo userInfo);
    int savaUserDormitory(int userID, Address address);
}
