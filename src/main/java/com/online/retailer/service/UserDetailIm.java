package com.online.retailer.service;

import com.online.retailer.Util.UserInfoUtil;
import com.online.retailer.mapper.IdentityMapper;
import com.online.retailer.model.Identity;
import com.online.retailer.model.MyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserDetailIm implements UserDetailsService {
    @Autowired
    UserInfoUtil userInfoUtil;
    @Autowired
    private IdentityMapper identityMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        MyUser myUser = userInfoUtil.getUserInfoByUsername(username);
        List<Identity> identityList = myUser.getIdentityList();
        if (identityList == null){
            identityList = identityMapper.findManagerIdentityByAccount(username);
        }
//        identityList.add("customer");
        List<SimpleGrantedAuthority> collect = identityList.stream()
                .map(identityRole -> new SimpleGrantedAuthority(identityRole.getIdentityRole()))
                .collect(Collectors.toList());

        User user = new User(myUser.getUsername(),myUser.getPassword(),collect);
        return (UserDetails) user;
    }

}
