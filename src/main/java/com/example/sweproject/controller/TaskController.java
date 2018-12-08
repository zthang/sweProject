package com.example.sweproject.controller;

import com.example.sweproject.bean.CommonMessage;
import com.example.sweproject.bean.Task;
import com.example.sweproject.service.implement.TaskServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskController {

    @Autowired
    private TaskServiceImp taskServiceImp;
    @RequestMapping(value = "/acceptTask",method = RequestMethod.POST)
    public CommonMessage acceptTask(int id,int acceptWay)
    {
        CommonMessage message=new CommonMessage();
        Task task=taskServiceImp.getTaskById(id);
        if (task==null){
            message.setMessage("该任务不在数据库");
            message.setState(0);
            return message;
        }
        if (task.isAccepted()){
            message.setMessage("该任务已经被接受");
            message.setState(0);
            return message;
        }
        task.setAccepted(true);
        task.setAcceptWay(acceptWay);
        if (taskServiceImp.updateTaskAcceptedWay(task)==1){
            message.setMessage("接受成功");
            message.setState(1);
            return message;
        }
        else {
            message.setMessage("接受失败");
            message.setState(0);
            return message;
        }
    }
}
