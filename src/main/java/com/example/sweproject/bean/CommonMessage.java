package com.example.sweproject.bean;

public class CommonMessage
{
    private String message;//信息内容
    private Integer state;//成功信息还是失败信息1：0

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
