package com.example.sweproject.controller;

import com.example.sweproject.bean.ResultEntity;
import com.example.sweproject.bean.Task;
import com.example.sweproject.service.TaskService;
import com.example.sweproject.service.UserService;
import com.example.sweproject.socket.WebSocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;

@RestController
public class TaskController
{
    @Autowired
    private TaskService taskService;
    @Autowired
    private UserService userService;
    @RequestMapping(value = "/addNewTask",method = RequestMethod.POST)
    public ResultEntity addNewTask(Task task)
    {
        ResultEntity commonMessage=new ResultEntity();
        commonMessage.setState(taskService.addNewTask(task));
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
    public ResultEntity getALlTasks(int userID)
    {
        ResultEntity resultEntity=new ResultEntity();
        ArrayList<Task> temp=taskService.getAllTasks(userID);
        resultEntity.setState(temp==null?0:1);
        resultEntity.setData(temp);
        return resultEntity;
    }
    @RequestMapping(value = "/getUnacceptedTasksByID",method = RequestMethod.POST)
    public ResultEntity getUnacceptedTasksByID(int releaserID)
    {
        ResultEntity resultEntity=new ResultEntity();
        ArrayList<Task> temp=taskService.getUnAcceptedTasksByID(releaserID);
        resultEntity.setState(temp==null?0:1);
        resultEntity.setData(temp);
        return resultEntity;
    }
    @RequestMapping(value = "/getAcceptedTasksByID",method = RequestMethod.POST)
    public ResultEntity getAcceptedTasksByID(int releaserID)
    {
        ResultEntity resultEntity=new ResultEntity();
        ArrayList<Task> temp=taskService.getAcceptedTasksByID(releaserID);
        resultEntity.setState(temp==null?0:1);
        resultEntity.setData(temp);
        return resultEntity;
    }
    @RequestMapping(value = "/getTasksByAccepterID",method = RequestMethod.POST)
    public ResultEntity getTasksByAccepterID(int accepterID)
    {
        ResultEntity resultEntity=new ResultEntity();
        ArrayList<Task> temp=taskService.getTasksByAccepterID(accepterID);
        resultEntity.setState(temp==null?0:1);
        resultEntity.setData(temp);
        return resultEntity;
    }
    @RequestMapping(value = "/acceptTask",method = RequestMethod.POST)
    public ResultEntity acceptTask(int accepterID,int taskID)throws IOException
    {
        ResultEntity commonMessage=new ResultEntity();
        Task temp=taskService.getTaskInfoByID(taskID);
        if(temp!=null&&temp.getAccepter()==0)
        {
            commonMessage.setState(taskService.acceptTask(accepterID,taskID));
            if(commonMessage.getState()==1)
            {
                WebSocketServer.sendMessageTo("您的任务已被接受！",temp.getReleaser().toString());
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
    @RequestMapping(value = "/getTaskInfoByID",method = RequestMethod.POST)
    public ResultEntity getTaskInfoByID(int taskID)
    {
        ResultEntity resultEntity=new ResultEntity();
        Task temp=taskService.getTaskInfoByID(taskID);
        resultEntity.setState(temp==null?0:1);
        resultEntity.setData(temp);
        return resultEntity;
    }
    @RequestMapping(value = "/relCompleteTask",method = RequestMethod.POST)
    public ResultEntity RelCompleteTask(int taskID)throws IOException
    {
        ResultEntity commonMessage=new ResultEntity();
        Task temp= taskService.getTaskInfoByID(taskID);
        if(temp==null)
        {
            commonMessage.setState(0);
            commonMessage.setMessage("该任务并不存在！");
            return commonMessage;
        }
        if(temp.getState().equals("在进行")||temp.getState().equals("待审核")||temp.getState().equals("已超时")||temp.getState().equals("超时待审核"))
        {
            //创建一条任务状态记录
            userService.addUserBalance(temp.getReleaser(),-temp.getBonousAmount());
            userService.addUserBalance(temp.getAccepter(),temp.getBonousAmount());
            userService.addUserCredit(temp.getReleaser(),10);//基础信誉分增加              //积分更改 发布人信誉分增加
            if(temp.getState().equals("在进行")||temp.getState().equals("待审核"))//若任务未超时 接受人信誉分增加10
                userService.addUserCredit(temp.getAccepter(),10);//基础信誉分增加10
            else
                userService.addUserCredit(temp.getAccepter(),5);//基础信誉分增加 超时只增加5
            taskService.updateTaskState(taskID,"已完成");
            commonMessage.setState(1);
            commonMessage.setMessage("ok!👌");
            WebSocketServer.sendMessageTo("发布人已经确认任务完成，您已获得信誉分奖励！",temp.getAccepter().toString());
            return commonMessage;
        }
        else
        {
            commonMessage.setState(0);
            commonMessage.setMessage("该任务状态有变化，请刷新！");//当前任务状态已经不支持完成任务了
            return commonMessage;
        }
    }
    @RequestMapping(value = "/acpCompleteTask",method = RequestMethod.POST)
    public ResultEntity acpCompleteTask(int taskID)throws IOException
    {
        //创建一条任务状态记录

        ResultEntity commonMessage=new ResultEntity();
        Task temp= taskService.getTaskInfoByID(taskID);
        if(temp==null)
        {
            commonMessage.setState(0);
            commonMessage.setMessage("该任务并不存在！");
            return commonMessage;
        }
        if(temp.getState().equals("在进行"))//如果未超时 更改状态为待审核
            commonMessage.setState(taskService.updateTaskState(taskID,"待审核"));
        else if(temp.getState().equals("已超时"))//如果超时 更改状态为超时待审核
            commonMessage.setState(taskService.updateTaskState(taskID,"超时待审核"));
        else
        {
            commonMessage.setState(0);
            commonMessage.setMessage("该任务状态有变化，请刷新！");
            return commonMessage;
        }
        if(commonMessage.getState()==1)
        {
            commonMessage.setMessage("已确认，请等待对方回应！");
            WebSocketServer.sendMessageTo("对方已经完成任务，请及时回应！",temp.getReleaser().toString());
        }
        else
            commonMessage.setMessage("更新任务状态失败！");
        return commonMessage;
    }
    @RequestMapping(value = "/cancelTask",method = RequestMethod.POST)
    public ResultEntity cancelTask(int userID,int taskID)throws IOException
    {
        //创建一条任务状态记录

        ResultEntity commonMessage=new ResultEntity();
        Task temp= taskService.getTaskInfoByID(taskID);
        if(temp==null)
        {
            commonMessage.setState(0);
            commonMessage.setMessage("任务并不存在！");
            return commonMessage;
        }
        if(temp.getReleaser().equals(userID))//发布人取消任务
        {
            if(temp.getState()==null)//任务未接受时取消任务 直接取消
            {
                commonMessage.setState(taskService.updateTaskState(taskID,"已取消"));
                if(commonMessage.getState()==1)
                    commonMessage.setMessage("任务取消成功！");
                else
                    commonMessage.setMessage("任务状态更新失败！");
                return commonMessage;
            }
            else if(temp.getState().equals("在进行"))//在进行时取消 更改任务状态为发布者取消 等待对方回应
            {
                commonMessage.setState(taskService.updateTaskState(taskID,"发布者取消"));
                if(commonMessage.getState()==1)
                {
                    commonMessage.setMessage("操作成功，请等待对方回应！");
                    WebSocketServer.sendMessageTo("对方请求取消任务，请作出回应！",temp.getAccepter().toString());
                }
                else
                    commonMessage.setMessage("任务状态更新失败！");
                return commonMessage;
            }
            else if(temp.getState().equals("已超时"))//超时情况下直接取消 接受人信誉减少
            {
                //写入任务日志 发布者选择取消任务 任务终止
                commonMessage.setState(taskService.updateTaskState(taskID,"已取消"));
                if(commonMessage.getState()==1)
                {
                    userService.addUserCredit(temp.getAccepter(),-10);
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
        else if(temp.getAccepter().equals(userID))//接受人取消任务
        {
            if(temp.getState().equals("在进行"))//任务进行中取消 状态变为接受者取消 等待对方回应
            {
                commonMessage.setState(taskService.updateTaskState(taskID,"接受者取消"));
                if(commonMessage.getState()==1)
                {
                    commonMessage.setMessage("操作成功，请等待对方回应！");
                    WebSocketServer.sendMessageTo("对方请求取消任务，请作出回应！",temp.getReleaser().toString());
                }
                else
                    commonMessage.setMessage("任务状态更新失败！");
                return commonMessage;
            }
            else if(temp.getState().equals("已超时"))//超时状态下取消 直接取消 接受人信誉分减少10
            {
                //写入任务日志 接受者选择取消任务 任务终止
                commonMessage.setState(taskService.updateTaskState(taskID,"已取消"));
                if(commonMessage.getState()==1)
                {
                    userService.addUserCredit(temp.getAccepter(),10*-1);
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
    @RequestMapping(value = "/relUnacceptComplete",method = RequestMethod.POST)//发布人不接受完成任务申请
    public ResultEntity relUnacceptComplete(int taskID)throws IOException
    {
        ResultEntity commonMessage=new ResultEntity();
        Task temp= taskService.getTaskInfoByID(taskID);
        if(temp==null)
        {
            commonMessage.setState(0);
            commonMessage.setMessage("任务并不存在！");
            return commonMessage;
        }
        if(temp.getState().equals("待审核")||temp.getState().equals("超时待审核"))
        {
            //向对方发送消息 记录日志 发布人不同意 任务变为异常状态
            commonMessage.setState(taskService.updateTaskState(taskID,"异常"));
            if(commonMessage.getState()==1)
            {
                commonMessage.setMessage("操作成功，请等待社区管理员介入！");
                WebSocketServer.sendMessageTo("对方认为您没有完成任务，请等待管理员介入！",temp.getAccepter().toString());
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
    @RequestMapping(value = "/acceptCancel",method = RequestMethod.POST)//某一方接受另一方的取消申请
    public ResultEntity acceptCancel(int userID,int taskID)throws IOException
    {
        ResultEntity commonMessage=new ResultEntity();
        Task temp= taskService.getTaskInfoByID(taskID);
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
                commonMessage.setState(taskService.updateTaskState(taskID,"已取消"));
                if(commonMessage.getState()==1)
                {
                    commonMessage.setMessage("任务取消成功！");
                    WebSocketServer.sendMessageTo("对方同意取消申请，任务已取消！",temp.getAccepter().toString());
                }
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
                commonMessage.setState(taskService.updateTaskState(taskID,"已取消"));
                if(commonMessage.getState()==1)
                {
                    WebSocketServer.sendMessageTo("对方同意取消申请，任务已取消！",temp.getReleaser().toString());
                    commonMessage.setMessage("任务取消成功！");
                }
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
    @RequestMapping(value = "/unacceptCancel",method = RequestMethod.POST)
    public ResultEntity unacceptCancel(int userID,int taskID)throws IOException
    {
        ResultEntity commonMessage=new ResultEntity();
        Task temp= taskService.getTaskInfoByID(taskID);
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
                commonMessage.setState(taskService.updateTaskState(taskID,"在进行"));
                if(commonMessage.getState()==1)
                {
                    commonMessage.setMessage("操作成功！");
                    WebSocketServer.sendMessageTo("对方不同意取消申请，任务继续！",temp.getAccepter().toString());
                }
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
                commonMessage.setState(taskService.updateTaskState(taskID,"在进行"));
                if(commonMessage.getState()==1)
                {
                    WebSocketServer.sendMessageTo("对方不同意取消申请，任务继续！",temp.getReleaser().toString());
                    commonMessage.setMessage("操作成功！");
                }
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
    @RequestMapping(value = "/undoCancel",method = RequestMethod.POST)
    public ResultEntity undoCancel(int userID,int taskID)throws IOException
    {
        ResultEntity commonMessage=new ResultEntity();
        Task temp= taskService.getTaskInfoByID(taskID);
        if(temp==null)
        {
            commonMessage.setState(0);
            commonMessage.setMessage("任务并不存在！");
            return commonMessage;
        }
        if(temp.getState().equals("发布者取消"))
        {
            if(temp.getReleaser().equals(userID))//检查id是否是发布者
            {
                commonMessage.setState(taskService.updateTaskState(taskID,"在进行"));
                if(commonMessage.getState()==1)
                {
                    commonMessage.setMessage("撤销成功！");
                    WebSocketServer.sendMessageTo("对方撤销了取消申请，任务继续！",temp.getAccepter().toString());
                }
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
        else if(temp.getState().equals("接受者取消"))//检查id是否是接受者
        {
            if(temp.getAccepter().equals(userID))
            {
                commonMessage.setState(taskService.updateTaskState(taskID,"在进行"));
                if(commonMessage.getState()==1)
                {
                    WebSocketServer.sendMessageTo("对方撤销了取消申请，任务继续！",temp.getReleaser().toString());
                    commonMessage.setMessage("撤销成功！");
                }
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
