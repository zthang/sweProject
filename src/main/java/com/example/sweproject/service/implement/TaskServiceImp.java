package com.example.sweproject.service.implement;

import com.example.sweproject.bean.Task;
import com.example.sweproject.bean.TaskList;
import com.example.sweproject.dao.TaskDao;
import com.example.sweproject.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public TaskList getUnAcceptedTasksByID(int releaserID)
    {
        return taskDao.getUnAcceptedTasksByID(releaserID);
    }

    @Override
    public TaskList getAllTasks(int userID)
    {
        return taskDao.getAllTasks(userID);
    }

    @Override
    public TaskList getAcceptedTasksByID(int releaserID)
    {
        return taskDao.getAcceptedTasksByID(releaserID);
    }

    @Override
    public TaskList getTasksByAccepterID(int accepterID)
    {
        return taskDao.getTasksByAccepterID(accepterID);
    }

    @Override
    public int acceptTask(int accepterID, int taskID)
    {
        return taskDao.acceptTask(accepterID,taskID);
    }

    @Override
    public Task getTaskInfoByID(int taskID)
    {
        return taskDao.getTaskInfoByID(taskID);
    }
}
