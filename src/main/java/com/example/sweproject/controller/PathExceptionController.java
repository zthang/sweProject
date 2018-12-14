package com.example.sweproject.controller;

import com.example.sweproject.bean.CommonMessage;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class PathExceptionController implements ErrorController
{
    @Override
    public String getErrorPath()
    {
        return "/error";
    }
    @RequestMapping(value = "/error")
    public Object error(HttpServletResponse resp, HttpServletRequest req)
    {
        CommonMessage commonMessage=new CommonMessage();
        commonMessage.setState(0);
        commonMessage.setMessage("请求路径错误！");
        return commonMessage;
    }
}
