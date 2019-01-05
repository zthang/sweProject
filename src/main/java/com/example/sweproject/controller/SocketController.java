package com.example.sweproject.controller;

import com.example.sweproject.socket.WebSocketServer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;

@RestController
public class SocketController {
    @Resource
    WebSocketServer myWebSocket;

    @RequestMapping("many")
    public String helloManyWebSocket(){
        //向所有人发送消息
        //myWebSocket.sendMessage("你好~！");

        return "发送成功";
    }

    @RequestMapping("one")
    public String helloOneWebSocket(String sessionId) throws IOException {
        //向某个人发送消息
        //myWebSocket.sendMessage(sessionId,"你好~！，单个用户");

        return "发送成功";
    }


}