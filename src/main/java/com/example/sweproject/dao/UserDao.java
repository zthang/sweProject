package com.example.sweproject.dao;

import com.example.sweproject.bean.User;

public interface UserDao
{
    int insert(User user);
    User getUserByUserName(String userName);
}
