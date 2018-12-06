package com.example.sweproject;

import com.example.sweproject.bean.Address;
import com.example.sweproject.bean.User;
import com.example.sweproject.bean.UserInfo;
import com.example.sweproject.controller.UserController;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SweprojectApplication.class)
public class UserControllerTest {

    @Autowired
    private UserController userController;

    UserInfo userInfo=new UserInfo();
    Address address=new Address();
    @Before
    public void setUp() throws Exception {
        userInfo.setNickname("1");
        userInfo.setSex("男");
        userInfo.setPhoneNumber("188");
        userInfo.setMail("11");
        userInfo.setStudentID("1652");
        userInfo.setDepartment("111");
        address.setAddress("友园");
        address.setDetailAddress("304");
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    //@Transactional
    public void addUser() {
        userController.saveUserInfo(14,userInfo,address);
    }
}