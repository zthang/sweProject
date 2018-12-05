package com.example.sweproject.service.implement;

import com.example.sweproject.bean.User;
import com.example.sweproject.dao.UserDao;
import com.example.sweproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "userService")
public class UserServiceImp implements UserService
{
    @Autowired
    private UserDao userDao;
    @Override
    public int addUser(User user)
    {
        return userDao.insert(user);
    }

    @Override
    public User getUserByUserName(String userName)
    {
        return userDao.getUserByUserName(userName);
    }
}
