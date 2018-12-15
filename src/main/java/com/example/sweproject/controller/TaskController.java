package com.example.sweproject.controller;

import com.example.sweproject.bean.CommonMessage;
import com.example.sweproject.bean.Task;
import com.example.sweproject.bean.TaskList;
import com.example.sweproject.service.UserService;
import com.example.sweproject.service.implement.TaskServiceImp;
import com.example.sweproject.service.implement.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class TaskController
{
    @Autowired
    private TaskServiceImp taskServiceImp;
    @Autowired
    private UserServiceImp userServiceImp;
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
        if(taskServiceImp.getTaskInfoByID(taskID).getAccepter()==0)
        {
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
        else
        {
            commonMessage.setState(0);
            commonMessage.setMessage("该任务已被接受！");
            return commonMessage;
        }
    }
    @RequestMapping(value = "getTaskInfoByID",method = RequestMethod.POST)
    public Task getTaskInfoByID(int taskID)
    {
        return taskServiceImp.getTaskInfoByID(taskID);
    }
    @RequestMapping(value = "relCompleteTask",method = RequestMethod.POST)
    public CommonMessage RelCompleteTask(int taskID)
    {
        CommonMessage commonMessage=new CommonMessage();
        Task temp=taskServiceImp.getTaskInfoByID(taskID);
        if(temp==null)
        {
            commonMessage.setState(0);
            commonMessage.setMessage("该任务并不存在！");
            return commonMessage;
        }
        if(temp.getState().equals("在进行")||temp.getState().equals("待审核")||temp.getState().equals("已超时")||temp.getState().equals("超时待审核"))
        {
            //创建一条任务状态记录
            userServiceImp.addUserBalance(temp.getReleaser(),-temp.getBonousAmount());
            userServiceImp.addUserBalance(temp.getAccepter(),temp.getBonousAmount());
            userServiceImp.addUserCredit(temp.getReleaser(),10);//基础信誉分增加
            if(temp.getState().equals("在进行")||temp.getState().equals("待审核"))//任务未超时
            {
                userServiceImp.addUserCredit(temp.getAccepter(),10);//基础信誉分增加
                taskServiceImp.updateTaskState(taskID,"已完成");
                commonMessage.setState(1);
                commonMessage.setMessage("ok!👌");
                return commonMessage;
            }
            else
            {
                userServiceImp.addUserCredit(temp.getAccepter(),5);//基础信誉分增加
                taskServiceImp.updateTaskState(taskID,"已完成");
                commonMessage.setState(1);
                commonMessage.setMessage("ok!👌");
                return commonMessage;
            }
        }
        else
        {
            commonMessage.setState(0);
            commonMessage.setMessage("该任务状态有变化，请刷新！");
            return commonMessage;
        }
    }
    @RequestMapping(value = "acpCompleteTask",method = RequestMethod.POST)
    public CommonMessage acpCompleteTask(int taskID)
    {
        //创建一条任务状态记录

        CommonMessage commonMessage=new CommonMessage();
        Task temp=taskServiceImp.getTaskInfoByID(taskID);
        if(temp==null)
        {
            commonMessage.setState(0);
            commonMessage.setMessage("该任务并不存在！");
            return commonMessage;
        }
        if(temp.getState().equals("在进行"))
        {
            commonMessage.setState(taskServiceImp.updateTaskState(taskID,"待审核"));
            if(commonMessage.getState()==1)
                commonMessage.setMessage("已确认，请等待对方回应！");
            else
                commonMessage.setMessage("更新任务状态失败！");
            return commonMessage;
        }
        else if(temp.getState().equals("已超时"))
        {
            commonMessage.setState(taskServiceImp.updateTaskState(taskID,"超时待审核"));
            if(commonMessage.getState()==1)
                commonMessage.setMessage("已确认，请等待对方回应！");
            else
                commonMessage.setMessage("更新任务状态失败！");
            return commonMessage;
        }
        else
        {
            commonMessage.setState(0);
            commonMessage.setMessage("该任务状态有变化，请刷新！");
            return commonMessage;
        }
    }
    @RequestMapping(value = "cancelTask",method = RequestMethod.POST)
    public CommonMessage cancelTask(int userID,int taskID)
    {
        //创建一条任务状态记录

        CommonMessage commonMessage=new CommonMessage();
        Task temp=taskServiceImp.getTaskInfoByID(taskID);
        if(temp==null)
        {
            commonMessage.setState(0);
            commonMessage.setMessage("任务并不存在！");
            return commonMessage;
        }
        if(temp.getReleaser().equals(userID))//发布人取消任务
        {
            if(temp.getState()==null)//任务未接受时取消任务
            {
                commonMessage.setState(taskServiceImp.updateTaskState(taskID,"已取消"));
                if(commonMessage.getState()==1)
                    commonMessage.setMessage("任务取消成功！");
                else
                    commonMessage.setMessage("任务状态更新失败！");
                return commonMessage;
            }
            else if(temp.getState().equals("在进行"))
            {
                commonMessage.setState(taskServiceImp.updateTaskState(taskID,"发布者取消"));
                if(commonMessage.getState()==1)
                    commonMessage.setMessage("操作成功，请等待对方回应！");
                else
                    commonMessage.setMessage("任务状态更新失败！");
                return commonMessage;
            }
            else if(temp.getState().equals("已超时"))
            {
                //写入任务日志 发布者选择取消任务 任务终止
                commonMessage.setState(taskServiceImp.updateTaskState(taskID,"已取消"));
                if(commonMessage.getState()==1)
                {
                    userServiceImp.addUserCredit(temp.getAccepter(),-10);
                    commonMessage.setMessage("取消成功！");
                }
                else
                    commonMessage.setMessage("任务状态更新失败！");
                return commonMessage;
            }
            else
            {
                commonMessage.setState(0);
                commonMessage.setMessage("该任务状态有变化，请刷新！");
                return commonMessage;
            }
        }
        else if(temp.getAccepter().equals(userID))//发布人取消任务
        {
            if(temp.getState().equals("在进行"))
            {
                commonMessage.setState(taskServiceImp.updateTaskState(taskID,"接受者取消"));
                if(commonMessage.getState()==1)
                    commonMessage.setMessage("操作成功，请等待对方回应！");
                else
                    commonMessage.setMessage("任务状态更新失败！");
                return commonMessage;
            }
            else if(temp.getState().equals("已超时"))
            {
                //写入任务日志 接受者选择取消任务 任务终止
                commonMessage.setState(taskServiceImp.updateTaskState(taskID,"已取消"));
                if(commonMessage.getState()==1)
                {
                    userServiceImp.addUserCredit(temp.getAccepter(),-10);
                    commonMessage.setMessage("取消成功！");
                }
                else
                    commonMessage.setMessage("任务状态更新失败！");
                return commonMessage;
            }
            else
            {
                commonMessage.setState(0);
                commonMessage.setMessage("该任务状态有变化，请刷新！");
                return commonMessage;
            }
        }
        else
        {
            commonMessage.setState(0);
            commonMessage.setMessage("id好像不对呀～");
            return commonMessage;
        }
    }
    @RequestMapping(value = "relUnacceptComplete",method = RequestMethod.POST)
    public CommonMessage relUnacceptComplete(int taskID)
    {
        CommonMessage commonMessage=new CommonMessage();
        Task temp=taskServiceImp.getTaskInfoByID(taskID);
        if(temp==null)
        {
            commonMessage.setState(0);
            commonMessage.setMessage("任务并不存在！");
            return commonMessage;
        }
        if(temp.getState().equals("待审核")||temp.getState().equals("超时待审核"))
        {
            //向对方发送消息 记录日志 发布人不同意 任务变为异常状态
            commonMessage.setState(taskServiceImp.updateTaskState(taskID,"异常"));
            if(commonMessage.getState()==1)
                commonMessage.setMessage("操作成功，请等待社区管理员介入！");
            else
                commonMessage.setMessage("任务状态更新失败！");
            return commonMessage;
        }
        else
        {
            commonMessage.setState(0);
            commonMessage.setMessage("该任务状态有变化，请刷新！");
            return commonMessage;
        }
    }
    @RequestMapping(value = "acceptCancel",method = RequestMethod.POST)
    public CommonMessage acceptCancel(int userID,int taskID)
    {
        CommonMessage commonMessage=new CommonMessage();
        Task temp=taskServiceImp.getTaskInfoByID(taskID);
        if(temp==null)
        {
            commonMessage.setState(0);
            commonMessage.setMessage("任务并不存在！");
            return commonMessage;
        }
        if(temp.getState().equals("接受者取消"))
        {
            if(temp.getReleaser().equals(userID))//发布人接受取消申请
            {
                //向对方发送消息 记录日志 发布人同意 任务变为已取消
                commonMessage.setState(taskServiceImp.updateTaskState(taskID,"已取消"));
                if(commonMessage.getState()==1)
                    commonMessage.setMessage("任务取消成功！");
                else
                    commonMessage.setMessage("任务状态更新失败！");
                return commonMessage;
            }
            else
            {
                commonMessage.setState(0);
                commonMessage.setMessage("id好像不对呀～");
                return commonMessage;
            }
        }
        else if(temp.getState().equals("发布者取消"))
        {
            if(temp.getAccepter().equals(userID))//接受者接受取消申请
            {
                commonMessage.setState(taskServiceImp.updateTaskState(taskID,"已取消"));
                if(commonMessage.getState()==1)
                    commonMessage.setMessage("任务取消成功！");
                else
                    commonMessage.setMessage("任务状态更新失败！");
                return commonMessage;
            }
            else
            {
                commonMessage.setState(0);
                commonMessage.setMessage("id好像不对呀～");
                return commonMessage;
            }
        }
        else
        {
            commonMessage.setState(0);
            commonMessage.setMessage("该任务状态有变化，请刷新！");
            return commonMessage;
        }
    }
    @RequestMapping(value = "unacceptCancel",method = RequestMethod.POST)
    public CommonMessage unacceptCancel(int userID,int taskID)
    {
        CommonMessage commonMessage=new CommonMessage();
        Task temp=taskServiceImp.getTaskInfoByID(taskID);
        if(temp==null)
        {
            commonMessage.setState(0);
            commonMessage.setMessage("任务并不存在！");
            return commonMessage;
        }
        if(temp.getState().equals("接受者取消"))
        {
            if(temp.getReleaser().equals(userID))//发布人不接受取消申请
            {
                //向对方发送消息 记录日志 发布人不同意 任务变为在进行
                commonMessage.setState(taskServiceImp.updateTaskState(taskID,"在进行"));
                if(commonMessage.getState()==1)
                    commonMessage.setMessage("操作成功！");
                else
                    commonMessage.setMessage("任务状态更新失败！");
                return commonMessage;
            }
            else
            {
                commonMessage.setState(0);
                commonMessage.setMessage("id好像不对呀～");
                return commonMessage;
            }
        }
        else if(temp.getState().equals("发布者取消"))
        {
            if(temp.getAccepter().equals(userID))//接受者不接受取消申请
            {
                commonMessage.setState(taskServiceImp.updateTaskState(taskID,"在进行"));
                if(commonMessage.getState()==1)
                    commonMessage.setMessage("操作成功！");
                else
                    commonMessage.setMessage("任务状态更新失败！");
                return commonMessage;
            }
            else
            {
                commonMessage.setState(0);
                commonMessage.setMessage("id好像不对呀～");
                return commonMessage;
            }
        }
        else
        {
            commonMessage.setState(0);
            commonMessage.setMessage("该任务状态有变化，请刷新！");
            return commonMessage;
        }
    }

}
