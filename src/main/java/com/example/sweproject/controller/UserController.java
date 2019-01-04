package com.example.sweproject.controller;

import com.example.sweproject.bean.*;
import com.example.sweproject.service.LocationService;
import com.example.sweproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class UserController
{
    @Autowired
    private UserService userService;
    @Autowired
    private LocationService locationService;
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public CommonMessage addUser(UserInfo userInfo)
    {
        CommonMessage message=new CommonMessage();
        if(userService.getUserInfoByPhoneNumber(userInfo.getPhoneNumber())==null&& userService.getUserInfoByNickname(userInfo.getNickname())==null)
        {
            userInfo.setPassword(DigestUtils.md5DigestAsHex(userInfo.getPassword().getBytes()));
            message.setState(userService.addUser(userInfo));
            message.setMessage("注册成功！");
            return message;
        }
        else if(userService.getUserInfoByPhoneNumber(userInfo.getPhoneNumber())!=null)
        {
            message.setState(0);
            message.setMessage("已存在该手机号，注册失败！");
            return message;
        }
        else
        {
            message.setState(0);
            message.setMessage("已存在该昵称，注册失败！");
            return message;
        }
    }
    @RequestMapping(value = "/loginByPhone",method = RequestMethod.POST)
    public LoginMessage loginByPhone(UserInfo userInfo)
    {
        LoginMessage loginMessage=new LoginMessage();
        UserInfo temp= userService.getUserInfoByPhoneNumber(userInfo.getPhoneNumber());
        if(temp==null)
        {
            loginMessage.setState(0);
            loginMessage.setMessage("手机号不存在！");
            return loginMessage;
        }
        else if(!temp.getPassword().equals(DigestUtils.md5DigestAsHex(userInfo.getPassword().getBytes())))
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
            loginMessage.setNickname(temp.getNickname());
            return loginMessage;
        }
    }
    @RequestMapping(value = "/loginByMail",method = RequestMethod.POST)
    public LoginMessage loginByMail(UserInfo userInfo)
    {
        LoginMessage loginMessage=new LoginMessage();
        UserInfo temp= userService.getUserInfoByMail(userInfo.getMail());
        if(temp==null)
        {
            loginMessage.setState(0);
            loginMessage.setMessage("邮箱不存在！");
            return loginMessage;
        }
        else if(!temp.getPassword().equals(DigestUtils.md5DigestAsHex(userInfo.getPassword().getBytes())))
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
    public CommonMessage saveUserInfo(UserInfo userInfo)
    {
        CommonMessage commonMessage=new CommonMessage();
        if(userService.getUserInfoByNickname(userInfo.getNickname())==null|| userService.getUserInfoByNickname(userInfo.getNickname()).getUserID().equals(userInfo.getUserID()))
        {
            if(userService.getUserInfoByPhoneNumber(userInfo.getPhoneNumber())==null|| userService.getUserInfoByPhoneNumber(userInfo.getPhoneNumber()).getUserID().equals(userInfo.getUserID()))
            {
                if(userService.getUserInfoByMail(userInfo.getMail())==null|| userService.getUserInfoByMail(userInfo.getMail()).getUserID().equals(userInfo.getUserID()))
                {
                    commonMessage.setState(userService.saveUserInfo(userInfo));
                    if(commonMessage.getState()!=0)
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
                else
                {
                    commonMessage.setState(0);
                    commonMessage.setMessage("该邮箱已被使用！");
                    return commonMessage;
                }
            }
            else
            {
                commonMessage.setState(0);
                commonMessage.setMessage("该电话号码已被使用！");
                return commonMessage;
            }
        }
        else
        {
            commonMessage.setState(0);
            commonMessage.setMessage("该昵称已被使用！");
            return commonMessage;
        }
    }
    @RequestMapping(value = "/insertUserAddress",method = RequestMethod.POST)
    public CommonMessage insertUserAddress(int userID,Address address)
    {
        CommonMessage commonMessage=new CommonMessage();
        Address temp= locationService.getLocationByName(address.getAddress());
        if(temp==null)
        {
            locationService.addLocation(address);
            commonMessage.setState(userService.insertUserAddress(userID,address));
            if(commonMessage.getState()==1)
            {
                commonMessage.setMessage("插入地址成功！");
                return commonMessage;
            }
            else
            {
                commonMessage.setMessage("插入地址失败！");
                return commonMessage;
            }
        }
        else
        {
            address.setAddressID(temp.getAddressID());
            if(locationService.getUserLocationByName(userID,address.getAddress(),address.getDetailAddress())==null)
            {
                commonMessage.setState(userService.insertUserAddress(userID,address));
                if(commonMessage.getState()==1)
                {
                    commonMessage.setMessage("插入地址成功！");
                    return commonMessage;
                }
                else
                {
                    commonMessage.setMessage("插入地址失败！");
                    return commonMessage;
                }
            }
            else
            {
                commonMessage.setState(0);
                commonMessage.setMessage("已有该地址，请勿重复添加！");
                return commonMessage;
            }
        }

    }
    @RequestMapping(value = "/getUserInfo",method = RequestMethod.POST)
    public UserInfo getUserInfo(int userID)
    {
        return userService.getUserInfo(userID);
    }
    @RequestMapping(value = "/getUserAddresses",method = RequestMethod.POST)
    public ArrayList<Address> getUserAddresses(int userID)
    {
        return userService.getUserAddresses(userID);
    }
    @RequestMapping(value = "/getDormitoryList",method = RequestMethod.POST)
    public ArrayList<String>getDormitoryList()
    {
        return userService.getDormitoryList();
    }
}
