package com.example.sweproject.service.implement;

import com.example.sweproject.bean.Address;
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
    public int addUser(UserInfo userInfo)
    {
        return userDao.register(userInfo);
    }

    @Override
    public UserInfo getUserInfoByPhoneNumber(String phoneNumber)
    {
        return userDao.getUserInfoByPhoneNumber(phoneNumber);
    }

    @Override
    public UserInfo getUserInfoByMail(String mail)
    {
        return userDao.getUserInfoByMail(mail);
    }

    @Override
    public UserInfo getUserInfoByNickname(String nickname)
    {
        return userDao.getUserInfoByNickname(nickname);
    }

    @Override
    public int saveUserInfo(UserInfo userInfo)
    {
        return userDao.saveUserInfo(userInfo);
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
    public ArrayList<Address> getUserAddresses(int userID)
    {
        return userDao.getUserAddresses(userID);
    }
    @Override
    public ArrayList<String> getDormitoryList()
    {
        return userDao.getDormitoryList();
    }

    @Override
    public int addUserCredit(int userID, int num)
    {
        return userDao.addUserCredit(userID,num);
    }

    @Override
    public int addUserBalance(int userID, int num)
    {
        return userDao.addUserBalance(userID,num);
    }

    @Override
    public int setUserPic(int userID, String url)
    {
        return userDao.setUserPic(userID,url);
    }

    @Override
    public String getUserPic(int userID) {
        return userDao.getUserPic(userID);
    }
}
