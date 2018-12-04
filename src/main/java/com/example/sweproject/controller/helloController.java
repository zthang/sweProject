package com.example.sweproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class helloController
{
    @RequestMapping(value = "/")
    public String index()
    {
        return "Welcome!";
    }
    @RequestMapping(value = "hello")
    public String hello()
    {
        return "Hello";
    }
}
