package com.example.sweproject.utils;

import com.example.sweproject.bean.Task;
import com.example.sweproject.bean.TaskList;
import com.example.sweproject.dao.LocationDao;
import com.example.sweproject.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;

@Component
public class TaskUtil
{
    @Autowired
    private UserDao userDao;
    @Autowired
    private LocationDao locationDao;

    public static TaskUtil taskUtil;
    @PostConstruct
    public void init()
    {
        taskUtil=this;
        taskUtil.userDao=this.userDao;
        taskUtil.locationDao=this.locationDao;
    }
    public TaskList addInfo(TaskList taskList)
    {
        if(taskList.getTaskList().size()!=0)
            for(Task t:taskList.getTaskList())
            {
                t.setNickname_r(taskUtil.userDao.getNicknameByID(t.getReleaser()));
                t.setNickname_a(taskUtil.userDao.getNicknameByID(t.getAccepter()));
                t.setFrom(taskUtil.locationDao.getLocationNameByID(t.getFromLocation()));
                t.setTo(taskUtil.locationDao.getLocationNameByID(t.getToLocation()));
            }
        return taskList;
    }
    public ArrayList<TaskList> addInfo(ArrayList<TaskList> taskList)
    {
        if(taskList.size()!=0)
            for(TaskList temp:taskList)
            {
                for(Task t:temp.getTaskList())
                {
                    t.setNickname_r(taskUtil.userDao.getNicknameByID(t.getReleaser()));
                    t.setNickname_a(taskUtil.userDao.getNicknameByID(t.getAccepter()));
                    t.setFrom(taskUtil.locationDao.getLocationNameByID(t.getFromLocation()));
                    t.setTo(taskUtil.locationDao.getLocationNameByID(t.getToLocation()));
                }
            }
        return taskList;
    }
    public Task addInfo(Task task)
    {
        if(task!=null)
        {
            task.setNickname_r(taskUtil.userDao.getNicknameByID(task.getReleaser()));
            task.setNickname_a(taskUtil.userDao.getNicknameByID(task.getAccepter()));
            task.setFrom(taskUtil.locationDao.getLocationNameByID(task.getFromLocation()));
            task.setTo(taskUtil.locationDao.getLocationNameByID(task.getToLocation()));
        }
        return task;
    }
}
