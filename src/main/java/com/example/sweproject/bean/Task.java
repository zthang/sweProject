package com.example.sweproject.bean;

import java.util.Date;
import java.util.ArrayList;

public class Task//已接受的任务多接受者 接受时间 状态
{
    private int taskID;
    private String type;
    private int releaser;
    private String title;
    private Date releaseDate;
    private Date dueDate;
    private int leftHours;
    //返回前端但是不显示
    private int fromLocation;
    private int toLocation;
    //只有查看详细时返回
    private String description_1;
    private String description_2;
    private String bonousType;
    private int bonousAmount;
    private String bonousDescription;

    private Date acceptedDate;
    private int accepter;
    private String state;

    private String nickname_r;
    private String nickname_a;
    private String from;
    private String to;

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

    public Date getAcceptedDate() {
        return acceptedDate;
    }

    public void setAcceptedDate(Date acceptedDate) {
        this.acceptedDate = acceptedDate;
    }

    public int getAccepter() {
        return accepter;
    }

    public void setAccepter(int accepter) {
        this.accepter = accepter;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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
    //需要数据库返回任务编号、任务类型、用户昵称、标题、发布时间、截止日期、任务执行期限、执行地点、交付地点、一段描述、二段描述

    public int getTaskID() {
        return taskID;
    }

    public void setTaskID(int taskID) {
        this.taskID = taskID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getReleaser() {
        return releaser;
    }

    public void setReleaser(int releaser) {
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

    public int getLeftHours() {
        return leftHours;
    }

    public void setLeftHours(int leftHours) {
        this.leftHours = leftHours;
    }

    public int getFromLocation() {
        return fromLocation;
    }

    public void setFromLocation(int fromLocation) {
        this.fromLocation = fromLocation;
    }

    public int getToLocation() {
        return toLocation;
    }

    public void setToLocation(int toLocation) {
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
}
