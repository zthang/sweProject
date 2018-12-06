package com.example.sweproject.service.implement;

import com.example.sweproject.bean.Address;
import com.example.sweproject.bean.User;
import com.example.sweproject.bean.UserInfo;
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
    public int saveUserInfo(int userID, UserInfo userInfo,Address address)
    {
        if(userDao.saveUserInfo(userID,userInfo)==1&&userDao.saveUserDormitory(userID,address)==1)
            return 1;
        else
            return 0;
    }
}
