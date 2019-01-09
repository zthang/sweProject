package com.example.sweproject.controller;

import com.alibaba.druid.support.json.JSONUtils;
import com.example.sweproject.socket.WebSocketServer;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.websocket.EncodeException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
    public String helloOneWebSocket(String sessionId) throws IOException,EncodeException {
        Map map=new HashMap();
        map.put("message","sss");
        map.put("state",1);
        sendMessageTo(JSONUtils.toJSONString(map),sessionId);

        return "发送成功";
    }
}