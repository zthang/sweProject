package com.example.sweproject.service.implement;

import com.example.sweproject.bean.Address;
import com.example.sweproject.bean.AddressList;
import com.example.sweproject.bean.User;
import com.example.sweproject.bean.UserInfo;
import com.example.sweproject.dao.UserDao;
import com.example.sweproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service(value = "userService")
public class UserServiceImp implements UserService
{
    @Autowired
    private UserDao userDao;
    @Override
    public int addUser(User user)
    {
        if(userDao.insert(user)==1&&userDao.insertUserInfo(user)==1&&userDao.insertUserDormitory(user)==1)
            return 1;
        else
            return 0;
    }

    @Override
    public User getUserByUserName(String userName)
    {
        return userDao.getUserByUserName(userName);
    }

    @Override
    public int saveUserInfo(int userID, UserInfo userInfo)
    {
        return userDao.saveUserInfo(userID,userInfo);
    }
    @Override
    public int saveUserDormitory(int userID, Address address)
    {
        return userDao.saveUserDormitory(userID,address);
    }
    @Override
    public int insertUserAddress(int userID, Address address)
    {
        return userDao.insertUserAddress(userID,address);
    }
    @Override
    public UserInfo getUserInfo(int userID)
    {
        return userDao.getUserInfo(userID);
    }
    @Override
    public AddressList getUserAddresses(int userID)
    {
        return userDao.getUserAddresses(userID);
    }

    @Override
    public Address getUserDormitory(int userID)
    {
        return userDao.getUserDormitory(userID);
    }
}
