package com.example.sweproject.controller;

import com.example.sweproject.socket.WebSocketServer;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

import static com.example.sweproject.socket.WebSocketServer.sendMessageTo;

@RestController
public class SocketController {
    @Resource
    WebSocketServer myWebSocket;

    @RequestMapping("many")
    public String helloManyWebSocket(){


        return "发送成功";
    }

    @RequestMapping("one")
    public String helloOneWebSocket(String sessionId) throws IOException {
        sendMessageTo("ooo",sessionId);

        return "发送成功";
    }


}