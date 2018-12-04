package com.example.sweproject.controller;

import com.example.sweproject.bean.User;
import com.example.sweproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController
{
    @Autowired
    private UserService userService;
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public int addUser(User user)
    {
        return userService.addUser(user);
    }
}
