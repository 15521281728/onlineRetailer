package com.online.retailer.service;

import com.online.retailer.mapper.CustomerMapper;
import com.online.retailer.mapper.IdentityMapper;
import com.online.retailer.mapper.ManagerMapper;
import com.online.retailer.model.Customer;
import com.online.retailer.model.Identity;
import com.online.retailer.model.Manager;
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
    private CustomerMapper customerMapper;
    @Autowired
    private ManagerMapper managerMapper;
    @Autowired
    private IdentityMapper identityMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyUser myUser = getUserInfoByUsername(username);
        List<Identity> identityList = myUser.getIdentityList();
        if (identityList == null){
            identityList = identityMapper.findManagerIdentityByAccount(username);
        }
//        identityList.add("customer");
        List<SimpleGrantedAuthority> collect = identityList.stream()
                .map(identityRose -> new SimpleGrantedAuthority(identityRose.getIdentityRose()))
                .collect(Collectors.toList());

        User user = new User(myUser.getUsername(),myUser.getPassword(),collect);
        return (UserDetails) user;
    }
/*请一定要保证username的唯一性！同时新增用户时请增加这里*/
    private MyUser getUserInfoByUsername(String username){
        MyUser user = new MyUser();

        Customer customer = customerMapper.selectCustomerByAccount(username);
        List<Identity> identityList;
        if (customer != null){
            user.setUsername(customer.getCustomerAccount());
            user.setPassword(customer.getCustomerPassword());
            identityList = identityMapper.findCustomerIdentityByAccount(username);
            user.setIdentityList(identityList);
        }
        Manager manager = managerMapper.selectManagerByUsername(username);
        if (manager != null){
            user.setUsername(manager.getManagerUsername());
            user.setPassword(manager.getManagerPassword());
            identityList = identityMapper.findManagerIdentityByAccount(username);
            user.setIdentityList(identityList);
        }
        return user;
    }
}
