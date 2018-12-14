package com.example.sweproject.bean;

import java.util.ArrayList;

public class TaskList
{
    private Integer userID;
    private ArrayList<Task>taskList;

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }
}
