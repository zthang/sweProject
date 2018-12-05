package com.example.sweproject.bean;

public class CommonMessage
{
    private String message;//信息内容
    private int state;//成功信息还是失败信息1：0

    public String getMessage() {
        return message;
    }

    public int getState() {
        return state;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setState(int state) {
        this.state = state;
    }
}
