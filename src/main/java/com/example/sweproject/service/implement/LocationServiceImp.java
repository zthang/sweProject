package com.example.sweproject.service.implement;

import com.example.sweproject.bean.Address;
import com.example.sweproject.dao.LocationDao;
import com.example.sweproject.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "locationService")
public class LocationServiceImp implements LocationService
{
    @Autowired
    LocationDao locationDao;

    @Override
    public Address getLocationByName(String name)
    {
        return locationDao.getLocationByName(name);
    }

    @Override
    public int addLocation(Address address)
    {
        return locationDao.addLocation(address);
    }

    @Override
    public Address getUserLocationByName(int userID, String address, String detailAddress)
    {
        return locationDao.getUserLocationByName(userID,address,detailAddress);
    }

    @Override
    public Address getLocationByID(int locationID)
    {
        return locationDao.getLocationByID(locationID);
    }
}
