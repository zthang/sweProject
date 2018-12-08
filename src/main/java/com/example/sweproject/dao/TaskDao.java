package com.example.sweproject.dao;

import com.example.sweproject.bean.Task;
import org.apache.ibatis.annotations.Param;

public interface TaskDao {


    public Task getTaskById(int id);
    public int updateTaskAcceptedWay(@Param("task")Task task);

}
