package com.example.sweproject.socket;

import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

@Component
@ServerEndpoint(value = "/myWebSocket/{userCode}")
public class WebSocketServer {
    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;
    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
    private static CopyOnWriteArraySet<WebSocketServer> webSocketSet = new CopyOnWriteArraySet<WebSocketServer>();
    //user
    private String currentUser;
    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;
    //保存离线消息
    public static Map<String,CopyOnWriteArrayList<String>>messageList=new HashMap<>();
    /**
     * 连接建立成功调用的方法*/
    @OnOpen
    public void onOpen(@PathParam("userCode") String userCode, Session session)throws IOException {
        this.currentUser = userCode;
        this.session = session;
        webSocketSet.add(this);     //加入set中
        addOnlineCount();           //在线数加1
        System.out.println("有新连接加入！当前在线人数为" + getOnlineCount() +",userCode:"+userCode);

        //如果该用户有未接收的离线消息
        if(WebSocketServer.messageList.get(userCode)!=null)
        {
            CopyOnWriteArrayList<String> userMessages=WebSocketServer.messageList.get(userCode);
            for(String message:userMessages)
            {
                sendMessageTo(message,userCode);
                System.out.println(userCode+"发送成功");
            }
            WebSocketServer.messageList.remove(userCode);
        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        webSocketSet.remove(this);  //从set中删除
        subOnlineCount();           //在线数减1
        System.out.println("有一连接关闭！当前在线人数为" + getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
         */
    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("来自客户端的消息:" + message);
    }

    /**
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("发生错误");
    }

    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    /**
     * 发送给指定用户
     * @param message
     * @param userCode
     * @throws IOException
     */
    public static void sendMessageTo(String message,String userCode) throws IOException {
        for (WebSocketServer item : webSocketSet)
        {
            if (item.currentUser.equals(userCode))
            {
                item.session.getBasicRemote().sendText(message);
                return;
            }
        }
            //如果没有该用户 则为离线消息

            //如果该用户的离线队列还不存在
            if(messageList.get(userCode)==null)
            {
                CopyOnWriteArrayList userMessages=new CopyOnWriteArrayList();
                userMessages.add(message);
                messageList.put(userCode,userMessages);
                System.out.println(userCode+"列表创建");
            }
            else
            {
                CopyOnWriteArrayList userMessages=messageList.get(userCode);
                userMessages.add(message);
                System.out.println(userCode+"新增信息");
            }
    }

    /**
     * 群发自定义消息
     */
    public static void sendInfo(String message) throws IOException {
        System.out.println(message);
        for (WebSocketServer item : webSocketSet) {
            try {
                item.sendMessage(message);
            } catch (IOException e) {
                continue;
            }
        }
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocketServer.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocketServer.onlineCount--;
    }
}
