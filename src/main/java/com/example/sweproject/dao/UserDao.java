package com.example.sweproject.dao;

import com.example.sweproject.bean.Address;
import com.example.sweproject.bean.AddressList;
import com.example.sweproject.bean.User;
import com.example.sweproject.bean.UserInfo;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.Map;

public interface UserDao
{
    int insert(User user);//注册
    int insertUserInfo(User user);//注册时一同生成
    int insertUserDormitory(User user);//注册时一同生成
    User getUserByUserName(String userName);

    int saveUserInfo(@Param("userID") int userID, @Param("userInfo") UserInfo userInfo);//保存基本信息
    int saveUserDormitory(@Param("userID") int userID,@Param("address") Address address);//保存宿舍楼信息
    int insertUserAddress(@Param("userID") int userID,@Param("address") Address address);//插入用户常用地址

    UserInfo getUserInfo(@Param("userID")int userID);//获取基本信息
    AddressList getUserAddresses(@Param("userID")int userID);//获取常用地址
    Address getUserDormitory(@Param("userID")int userID);//获取宿舍楼信息

}
