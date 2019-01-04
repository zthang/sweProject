package com.example.sweproject.service.implement;

import com.example.sweproject.bean.Task;
import com.example.sweproject.dao.TaskDao;
import com.example.sweproject.service.TaskService;
import com.example.sweproject.utils.TaskUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service(value = "taskService")
public class TaskServiceImp implements TaskService
{
    @Autowired
    private TaskDao taskDao;

    @Override
    public int addNewTask(Task task)
    {
        return taskDao.addNewTask(task);
    }

    @Override
    public ArrayList<Task> getUnAcceptedTasksByID(int releaserID)
    {
        return new TaskUtil().addInfo(taskDao.getUnAcceptedTasksByID(releaserID));
    }

    @Override
    public ArrayList<Task> getAllTasks(int userID)
    {
        return new TaskUtil().addInfo(taskDao.getAllTasks(userID));
    }

    @Override
    public ArrayList<Task> getAcceptedTasksByID(int releaserID)
    {
        return new TaskUtil().addInfo(taskDao.getAcceptedTasksByID(releaserID));
    }

    @Override
    public ArrayList<Task> getTasksByAccepterID(int accepterID)
    {
        return new TaskUtil().addInfo(taskDao.getTasksByAccepterID(accepterID));
    }

    @Override
    public int acceptTask(int accepterID, int taskID)
    {
        return taskDao.acceptTask(accepterID,taskID);
    }

    @Override
    public Task getTaskInfoByID(int taskID)
    {
        return new TaskUtil().addInfo(taskDao.getTaskInfoByID(taskID));
    }

    @Override
    public int updateTaskState(int taskID, String state)
    {
        return taskDao.updateTaskState(taskID,state);
    }
}
