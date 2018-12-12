package com.example.sweproject.bean;

import java.util.Date;

public class AcceptedTask
{
    private int taskID;
    private String type;
    private String title;
    private Date releaseDate;
    private Date acceptedDate;
    private Date dueDate;
    private String releaser;
    private String accepter;
    private String fromLocation;
    private String description_1;
    private String description_2;
    private String state;
    private String bonousType;
    private int bonousAmount;
    private String bonousDescription;

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

    public Date getAcceptedDate() {
        return acceptedDate;
    }

    public void setAcceptedDate(Date acceptedDate) {
        this.acceptedDate = acceptedDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getReleaser() {
        return releaser;
    }

    public void setReleaser(String releaser) {
        this.releaser = releaser;
    }

    public String getAccepter() {
        return accepter;
    }

    public void setAccepter(String accepter) {
        this.accepter = accepter;
    }

    public String getFromLocation() {
        return fromLocation;
    }

    public void setFromLocation(String fromLocation) {
        this.fromLocation = fromLocation;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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
}
