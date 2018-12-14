package com.example.sweproject.bean;

import java.util.Date;
import java.util.ArrayList;

public class Task//已接受的任务多接受者 接受时间 状态
{
    private Integer taskID;
    private String type;
    private Integer releaser;
    private String title;
    private Date releaseDate;
    private Date dueDate;
    private Integer leftHours;
    //返回前端但是不显示
    private Integer fromLocation;
    private Integer toLocation;
    //只有查看详细时返回
    private String description_1;
    private String description_2;
    private String bonousType;
    private int bonousAmount;
    private String bonousDescription;

    private Date acceptedDate;
    private Integer accepter;
    private String state;

    private String nickname_r;
    private String nickname_a;
    private String from;
    private String to;

    public Integer getTaskID() {
        return taskID;
    }

    public void setTaskID(Integer taskID) {
        this.taskID = taskID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getReleaser() {
        return releaser;
    }

    public void setReleaser(Integer releaser) {
        this.releaser = releaser;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Integer getLeftHours() {
        return leftHours;
    }

    public void setLeftHours(Integer leftHours) {
        this.leftHours = leftHours;
    }

    public Integer getFromLocation() {
        return fromLocation;
    }

    public void setFromLocation(Integer fromLocation) {
        this.fromLocation = fromLocation;
    }

    public Integer getToLocation() {
        return toLocation;
    }

    public void setToLocation(Integer toLocation) {
        this.toLocation = toLocation;
    }

    public String getDescription_1() {
        return description_1;
    }

    public void setDescription_1(String description_1) {
        this.description_1 = description_1;
    }

    public String getDescription_2() {
        return description_2;
    }

    public void setDescription_2(String description_2) {
        this.description_2 = description_2;
    }

    public String getBonousType() {
        return bonousType;
    }

    public void setBonousType(String bonousType) {
        this.bonousType = bonousType;
    }

    public int getBonousAmount() {
        return bonousAmount;
    }

    public void setBonousAmount(int bonousAmount) {
        this.bonousAmount = bonousAmount;
    }

    public String getBonousDescription() {
        return bonousDescription;
    }

    public void setBonousDescription(String bonousDescription) {
        this.bonousDescription = bonousDescription;
    }

    public Date getAcceptedDate() {
        return acceptedDate;
    }

    public void setAcceptedDate(Date acceptedDate) {
        this.acceptedDate = acceptedDate;
    }

    public Integer getAccepter() {
        return accepter;
    }

    public void setAccepter(Integer accepter) {
        this.accepter = accepter;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getNickname_r() {
        return nickname_r;
    }

    public void setNickname_r(String nickname_r) {
        this.nickname_r = nickname_r;
    }

    public String getNickname_a() {
        return nickname_a;
    }

    public void setNickname_a(String nickname_a) {
        this.nickname_a = nickname_a;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
