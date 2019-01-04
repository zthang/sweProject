package com.example.sweproject.service;

import com.example.sweproject.bean.Task;

import java.util.ArrayList;

public interface TaskService
{
    int addNewTask(Task task);
    ArrayList<Task> getUnAcceptedTasksByID(int releaserID);
    ArrayList<Task> getAllTasks(int userID);
    ArrayList<Task> getAcceptedTasksByID(int releaserID);
    ArrayList<Task> getTasksByAccepterID(int accepterID);
    int acceptTask(int accepterID,int taskID);
    Task getTaskInfoByID(int taskID);
    int updateTaskState(int taskID,String state);
}
