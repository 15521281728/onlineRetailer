package com.online.retailer.Util;

import com.online.retailer.mapper.CustomerMapper;
import com.online.retailer.mapper.IdentityMapper;
import com.online.retailer.mapper.ManagerMapper;
import com.online.retailer.model.Customer;
import com.online.retailer.model.Identity;
import com.online.retailer.model.Manager;
import com.online.retailer.model.MyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserInfoUtil {

    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private ManagerMapper managerMapper;
    @Autowired
    private IdentityMapper identityMapper;
    /*请一定要保证username的唯一性！同时新增用户时请增加这里*/
    public MyUser getUserInfoByUsername(String username){
        MyUser user = new MyUser();

        Customer customer = customerMapper.selectCustomerByAccount(username);
        List<Identity> identityList;
        if (customer != null){
            user.setUsername(customer.getCustomerAccount());
            user.setPassword(customer.getCustomerPassword());
            user.setName(customer.getCustomerName());
            user.setId(customer.getCustomerId());
            identityList = identityMapper.findCustomerIdentityByAccount(username);
            user.setIdentityList(identityList);
        }
        Manager manager = managerMapper.selectManagerByUsername(username);
        if (manager != null){
            user.setUsername(manager.getManagerUsername());
            user.setPassword(manager.getManagerPassword());
            user.setName(manager.getManagerName());
            user.setId(manager.getManagerId());
            identityList = identityMapper.findManagerIdentityByAccount(username);
            user.setIdentityList(identityList);
        }
        return user;
    }
}
