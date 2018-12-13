package com.example.sweproject.service;

import com.example.sweproject.bean.Address;

public interface LocationService
{
    int addLocation(Address address);
    Address getLocationByName(String name);
    Address getUserLocationByName(int userID,String address,String detailAddress);
    Address getLocationByID(int locationID);
}
