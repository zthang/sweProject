package com.example.sweproject.dao;

import com.example.sweproject.bean.Task;
import com.example.sweproject.bean.TaskList;
import org.apache.ibatis.annotations.Param;

public interface TaskDao
{
    int addNewTask(@Param("taskInfo")Task task);

    TaskList getAcceptedTasksByID(@Param("releaserID")int releaserID);//获取某人发布的已接受的任务
    TaskList getUnAcceptedTasksByID(@Param("releaserID")int releaserID);//获取某人发布的未接受的任务
    TaskList getAllTasks(@Param("userID")int userID);//某人查看任务广场获取任务列表
    TaskList getTasksByAccepterID(@Param("accepterID")int accepterID);//某人查看自己接受的任务（包括未完成的和已完成的）
    Task getTaskInfoByID(@Param("taskID")int taskID);

    int acceptTask(@Param("accepterID")int accepterID,@Param("taskID")int taskID);//某人接受了某个任务
}
