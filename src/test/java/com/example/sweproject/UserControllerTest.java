package com.example.sweproject;

import com.example.sweproject.bean.User;
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

    User user=new User();
    @Before
    public void setUp() throws Exception {
        user.setUserName("rr11");
        user.setPassword("kk");
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    @Transactional
    public void addUser() {
        assertEquals(userController.addUser(user),1);
    }
}