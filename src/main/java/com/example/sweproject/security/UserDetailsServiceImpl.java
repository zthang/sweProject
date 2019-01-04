package com.example.sweproject.security;

import com.example.sweproject.bean.User;
import com.example.sweproject.bean.UserInfo;
import com.example.sweproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserInfo userInfo = userService.getUserInfoByPhoneNumber(s);
        User user=new User();
        user.setUsername(userInfo.getPhoneNumber());
        user.setPassword(userInfo.getPassword());
        user.setId(userInfo.getUserID());
        user.setRole(userInfo.getAuthority());
        user.setNickname(userInfo.getNickname());
        return new JwtUser(user);
    }
}
