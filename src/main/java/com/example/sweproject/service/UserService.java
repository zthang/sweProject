package com.example.sweproject.service;

import com.example.sweproject.bean.User;

public interface UserService
{
    int addUser(User user);
    User getUserByUserName(String userName);
}
