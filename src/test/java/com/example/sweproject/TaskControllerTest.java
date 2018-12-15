package com.example.sweproject;

import com.example.sweproject.bean.Task;
import com.example.sweproject.bean.TaskList;
import com.example.sweproject.controller.TaskController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SweprojectApplication.class)
public class TaskControllerTest {
    @Autowired
    private TaskController taskController;

    Task task =new Task();
    @Before
    public void setUp() throws Exception
    {
        task.setType("代办");
        task.setReleaser(14);
        task.setTitle("aaa");
        task.setReleaseDate(new Date(System.currentTimeMillis()));
        task.setDueDate(new Date(System.currentTimeMillis()));
        task.setLeftHours(3);
        task.setFromLocation(2);
        task.setToLocation(3);
        task.setDescription_1("bb");
        task.setDescription_2("qq");
        task.setBonousType("积分");
        task.setBonousAmount(100);
    }



    @Test
    public void getTaskInfoByID()
    {
        Task task=taskController.getTaskInfoByID(7);
    }
}