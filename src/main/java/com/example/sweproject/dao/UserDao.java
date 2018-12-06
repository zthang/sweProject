package com.example.sweproject.dao;

import com.example.sweproject.bean.Address;
import com.example.sweproject.bean.User;
import com.example.sweproject.bean.UserInfo;
import org.apache.ibatis.annotations.Param;

public interface UserDao
{
    int insert(User user);
    int insertUserInfo(User user);
    int insertUserDormitory(User user);
    User getUserByUserName(String userName);

    int saveUserInfo(@Param("userID") int userID, @Param("userInfo") UserInfo userInfo);
    int saveUserDormitory(@Param("userID") int userID,@Param("address") Address address);

}
