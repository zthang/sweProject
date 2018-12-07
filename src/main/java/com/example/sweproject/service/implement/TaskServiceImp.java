package com.example.sweproject.service.implement;

import com.example.sweproject.bean.Task;
import com.example.sweproject.dao.TaskDao;
import com.example.sweproject.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "taskService")
public class TaskServiceImp implements TaskService {

    @Autowired
    private TaskDao taskDao;
    @Override
    public Task getTaskById(int id) {
        return taskDao.getTaskById(id);
    }

    @Override
    public int updateTaskAcceptedWay(Task task) {
        return taskDao.updateTaskAcceptedWay(task);
    }

}
