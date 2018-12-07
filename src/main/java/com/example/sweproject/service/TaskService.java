package com.example.sweproject.service;

import com.example.sweproject.bean.Task;

public interface TaskService {
    Task getTaskById(int id);
    int updateTaskAcceptedWay(Task task);
}
