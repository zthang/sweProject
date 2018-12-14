package com.example.sweproject.controller;

import com.example.sweproject.bean.CommonMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

import java.util.Enumeration;

import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler
{
    private static Logger logger = LogManager.getLogger(GlobalExceptionHandler.class.getName());
    /*@Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request)
    {
        CommonMessage commonMessage=new CommonMessage();
        commonMessage.setState(0);
        commonMessage.setMessage("参数传递错误，检查参数格式！");
        return new ResponseEntity<Object>(commonMessage, NOT_EXTENDED);
    }*/
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public CommonMessage jsonHandler(HttpServletRequest request, Exception e) throws Exception
    {
        CommonMessage commonMessage = new CommonMessage();
        commonMessage.setMessage(e.getMessage());
        commonMessage.setState(0);
        log(e, request);
        return commonMessage;
    }

    private void log(Exception ex, HttpServletRequest request)
    {
        logger.error("************************异常开始*******************************");
        logger.error(ex);
        logger.error("请求地址：" + request.getRequestURL());
        Enumeration enumeration = request.getParameterNames();
        logger.error("请求参数");
        while (enumeration.hasMoreElements()) {
            String name = enumeration.nextElement().toString();
            logger.error(name + "---" + request.getParameter(name));
        }

        StackTraceElement[] error = ex.getStackTrace();
        for (StackTraceElement stackTraceElement : error) {
            logger.error(stackTraceElement.toString());
        }
        logger.error("************************异常结束*******************************");
    }
}
