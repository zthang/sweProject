package com.example.sweproject.controller;

import com.example.sweproject.bean.CommonMessage;
import com.example.sweproject.bean.Task;
import com.example.sweproject.bean.TaskList;
import com.example.sweproject.service.implement.TaskServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class TaskController
{
    @Autowired
    private TaskServiceImp taskServiceImp;
    @RequestMapping(value = "/addNewTask",method = RequestMethod.POST)
    public CommonMessage addNewTask(Task task)
    {
        CommonMessage commonMessage=new CommonMessage();
        commonMessage.setState(taskServiceImp.addNewTask(task));
        if(commonMessage.getState()==1)
        {
            commonMessage.setMessage("发布成功！");
            return commonMessage;
        }
        else
        {
            commonMessage.setMessage("发布失败！");
            return commonMessage;
        }
    }
    @RequestMapping(value = "/getAllTasks",method = RequestMethod.POST)
    public TaskList getALlTasks(int userID)
    {
        return taskServiceImp.getAllTasks(userID);
    }
    @RequestMapping(value = "/getUnacceptedTasksByID",method = RequestMethod.POST)
    public TaskList getUnacceptedTasksByID(int releaserID)
    {
        return taskServiceImp.getUnAcceptedTasksByID(releaserID);
    }
    @RequestMapping(value = "/getAcceptedTasksByID",method = RequestMethod.POST)
    public TaskList getAcceptedTasksByID(int releaserID)
    {
        return taskServiceImp.getAcceptedTasksByID(releaserID);
    }
    @RequestMapping(value = "/getTasksByAccepterID",method = RequestMethod.POST)
    public TaskList getTasksByAccepterID(int accepterID)
    {
        return taskServiceImp.getTasksByAccepterID(accepterID);
    }
    @RequestMapping(value = "/acceptTask",method = RequestMethod.POST)
    public CommonMessage acceptTask(int accepterID,int taskID)
    {
        CommonMessage commonMessage=new CommonMessage();
        commonMessage.setState(taskServiceImp.acceptTask(accepterID,taskID));
        if(commonMessage.getState()==1)
        {
            commonMessage.setMessage("接受成功，请按时完成！");
            return commonMessage;
        }
        else
        {
            commonMessage.setMessage("接受失败！");
            return commonMessage;
        }
    }
    @RequestMapping(value = "getTaskInfoByID",method = RequestMethod.POST)
    public Task getTaskInfoByID(int taskID)
    {
        return taskServiceImp.getTaskInfoByID(taskID);
    }
}
