package com.example.sweproject.controller;

import com.example.sweproject.bean.*;
import com.example.sweproject.service.implement.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class UserController
{
    @Autowired
    private UserServiceImp userServiceImp;
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public CommonMessage addUser(User user)
    {
        CommonMessage message=new CommonMessage();
        if(userServiceImp.getUserByUserName(user.getUserName())==null)
        {
            message.setState(userServiceImp.addUser(user));
            message.setMessage("注册成功！");
            return message;
        }
        else
        {
            message.setState(0);
            message.setMessage("已存在该用户名，注册失败！");
            return message;
        }
    }
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public LoginMessage login(User user)
    {
        LoginMessage loginMessage=new LoginMessage();
        User temp=userServiceImp.getUserByUserName(user.getUserName());
        if(temp==null)
        {
            loginMessage.setState(0);
            loginMessage.setMessage("用户名不存在！");
            return loginMessage;
        }
        else if(!temp.getPassword().equals(user.getPassword()))
        {
            loginMessage.setState(0);
            loginMessage.setMessage("密码错误！");
            return loginMessage;
        }
        else
        {
            loginMessage.setState(1);
            loginMessage.setMessage("登陆成功！");
            loginMessage.setUserID(temp.getUserID());
            loginMessage.setAuthority(temp.getAuthority());
            return loginMessage;
        }
    }

    @RequestMapping(value="/saveUserInfo",method = RequestMethod.POST)
    public CommonMessage saveUserInfo(int userID, UserInfo userInfo)
    {
        CommonMessage commonMessage=new CommonMessage();
        commonMessage.setState(userServiceImp.saveUserInfo(userID,userInfo));
        if(commonMessage.getState()==1)
        {
            commonMessage.setMessage("保存成功！");
            return commonMessage;
        }
        else
        {
            commonMessage.setMessage("保存失败！");
            return commonMessage;
        }
    }
    @RequestMapping(value = "/insertUserAddress",method = RequestMethod.POST)
    public CommonMessage insertUserAddress(int userID,Address address)
    {
        CommonMessage commonMessage=new CommonMessage();
        commonMessage.setState(userServiceImp.insertUserAddress(userID,address));
        if(commonMessage.getState()==1)
        {
            commonMessage.setMessage("插入成功！");
            return commonMessage;
        }
        else
        {
            commonMessage.setMessage("插入失败！");
            return commonMessage;
        }
    }
    @RequestMapping(value = "/saveUserDormitory",method = RequestMethod.POST)
    public CommonMessage saveUserDormitory(int userID,Address address)
    {
        CommonMessage commonMessage=new CommonMessage();

        commonMessage.setState(userServiceImp.saveUserDormitory(userID,address));
        if(commonMessage.getState()==1)
        {
            commonMessage.setMessage("保存成功！");
            return commonMessage;
        }
        else
        {
            commonMessage.setMessage("保存失败！");
            return commonMessage;
        }
    }
    @RequestMapping(value = "/getUserInfo",method = RequestMethod.GET)
    public UserInfo getUserInfo(int userID)
    {
        return userServiceImp.getUserInfo(userID);
    }
    @RequestMapping(value = "/getUserAddresses",method = RequestMethod.GET)
    public AddressList getUserAddresses(int userID)
    {
        return userServiceImp.getUserAddresses(userID);
    }
    @RequestMapping(value="/getUserDormitory",method = RequestMethod.GET)
    public Address getUserDormitory(int userID)
    {
        return userServiceImp.getUserDormitory(userID);
    }
    @RequestMapping(value = "/getDormitoryList",method = RequestMethod.GET)
    public ArrayList<String>getDormitoryList()
    {
        return userServiceImp.getDormitoryList();
    }
}
