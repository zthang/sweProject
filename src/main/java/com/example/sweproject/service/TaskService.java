package com.example.sweproject.service;

import com.example.sweproject.bean.Task;
import com.example.sweproject.bean.TaskList;

import java.util.ArrayList;

public interface TaskService
{
    int addNewTask(Task task);
    TaskList getUnAcceptedTasksByID(int releaserID);
    ArrayList<TaskList> getAllTasks(int userID);
    TaskList getAcceptedTasksByID(int releaserID);
    TaskList getTasksByAccepterID(int accepterID);
    int acceptTask(int accepterID,int taskID);
    Task getTaskInfoByID(int taskID);
    int updateTaskState(int taskID,String state);
}
