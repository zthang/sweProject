package com.example.sweproject.dao;

import com.example.sweproject.bean.Address;
import com.example.sweproject.bean.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

@Mapper
public interface UserDao
{
    int register(@Param("userInfo") UserInfo userInfo);//注册

    UserInfo getUserInfoByPhoneNumber(@Param("phoneNumber") String phoneNumber);
    UserInfo getUserInfoByMail(@Param("mail") String mail);
    UserInfo getUserInfoByNickname(@Param("nickname") String nickname);

    int saveUserInfo(@Param("userInfo") UserInfo userInfo);//保存基本信息
    int insertUserAddress(@Param("userID") int userID,@Param("address") Address address);//插入用户常用地址

    UserInfo getUserInfo(@Param("userID")int userID);//获取基本信息
    ArrayList<Address> getUserAddresses(@Param("userID")int userID);//获取常用地址
    ArrayList<String>getDormitoryList();//获得宿舍列表

    String getNicknameByID(@Param("userID")int userID);
}
