package com.online.retailer.service;

import com.online.retailer.mapper.CustomerMapper;
import com.online.retailer.mapper.IdentityMapper;
import com.online.retailer.model.Customer;
import com.online.retailer.model.Identity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserDetailIm implements UserDetailsService {
    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private IdentityMapper identityMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = customerMapper.selectUserByAccount(username);
        List<Identity> identityList = identityMapper.findIdentityByAccount(username);
        List<SimpleGrantedAuthority> collect = identityList.stream()
                .map(indentity -> new SimpleGrantedAuthority(indentity.getIdentity()))
                .collect(Collectors.toList());

        User user = new User(customer.getUserAccount(),customer.getUserPassword(),collect);
        return (UserDetails) user;
    }
}
