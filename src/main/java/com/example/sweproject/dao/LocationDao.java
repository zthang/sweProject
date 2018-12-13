package com.example.sweproject.dao;

import com.example.sweproject.bean.Address;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LocationDao
{
    int addLocation(@Param("address") Address address);
    Address getLocationByName(@Param("name") String name);
    Address getUserLocationByName(@Param("userID")int userID,@Param("address")String address,@Param("detailAddress")String detailAddress);
    Address getLocationByID(@Param("locationID")int locationID);
    String getLocationNameByID(@Param("locationID")int locationID);
}
