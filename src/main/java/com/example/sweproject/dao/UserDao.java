package com.example.sweproject.dao;

import com.example.sweproject.bean.User;

import java.util.List;

public interface UserDao
{
    int insert(User user);
    List<User> selectUsers();
}
