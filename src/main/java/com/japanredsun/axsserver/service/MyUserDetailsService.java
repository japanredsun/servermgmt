package com.japanredsun.axsserver.service;

import com.japanredsun.axsserver.dao.UserInfoDAO;
import com.japanredsun.axsserver.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MyUserDetailsService implements UserDetailsService{

    @Autowired
    private UserInfoDAO userInfoDAO;

    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserInfo userInfo = userInfoDAO.findUserInfo(s);
        System.out.println("UserInfo= " + userInfo);

        if (userInfo == null){
            throw new UsernameNotFoundException("User " + s + "was not found in database");
        }

        //[USER,ADMIN]
        List<String> roles = userInfoDAO.getUserRoles(s);
        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();

        if(roles != null){
            for (String role : roles) {
                // ROLE_USER, ROLE_ADMIN,..
                GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_"+ role);
                grantList.add(authority);
            }
        }
        UserDetails userDetails = (UserDetails) new User(userInfo.getUserName(),userInfo.getPassword(),grantList);

        return userDetails;
    }
}
