package com.example.sweproject.bean;

import java.util.ArrayList;

public class TaskList
{
    private int userID;
    private ArrayList<Task>taskList;

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }
}
