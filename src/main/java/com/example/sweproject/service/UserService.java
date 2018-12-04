package com.example.sweproject.service;

import com.example.sweproject.bean.User;
import com.example.sweproject.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "userService")
public class UserService
{
    @Autowired
    private UserDao userDao;
    public int addUser(User user)
    {
        return userDao.insert(user);
    }
}
