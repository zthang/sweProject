package com.example.sweproject.bean;

public class Task {
    String content;//任务内容，待定
    int id;//任务ID,唯一
    boolean isAccepted;//是否接受
    int acceptWay;//接受方式，

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isAccepted() {
        return isAccepted;
    }

    public void setAccepted(boolean accepted) {
        isAccepted = accepted;
    }

    public int getAcceptWay() {
        return acceptWay;
    }

    public void setAcceptWay(int acceptWay) {
        this.acceptWay = acceptWay;
    }
}
