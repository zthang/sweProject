package com.example.sweproject.service;

import com.example.sweproject.bean.Task;
import com.example.sweproject.bean.TaskList;

public interface TaskService
{
    int addNewTask(Task task);
    TaskList getUnAcceptedTasksByID(int releaserID);
    TaskList getAllTasks(int userID);
    TaskList getAcceptedTasksByID(int releaserID);
    TaskList getTasksByAccepterID(int accepterID);
    int acceptTask(int accepterID,int taskID);
    Task getTaskInfoByID(int taskID);
    int updateTaskState(int taskID,String state);
}
