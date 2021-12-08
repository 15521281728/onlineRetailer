package com.online.retailer.service;

import com.online.retailer.mapper.CustomerMapper;
import com.online.retailer.mapper.ManagerMapper;
import com.online.retailer.model.Customer;
import com.online.retailer.model.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
//@Component
public class InsertUserService {
    private CustomerMapper customerMapper;
    private ManagerMapper managerMapper;

    public InsertUserService(CustomerMapper customerMapper, ManagerMapper managerMapper) {
        this.customerMapper = customerMapper;
        this.managerMapper = managerMapper;
    }

    //    @Autowired
//    public void setManagerMapper(ManagerMapper managerMapper){
//        this.managerMapper = managerMapper;
//    }
//
//    @Autowired
//    public void setCustomerMapper(CustomerMapper customerMapper){
//        this.customerMapper = customerMapper;
//    }
    public boolean insertCustomerByUsername(Customer customer) {
        boolean judge = judgeIsUser(customer.getCustomerAccount());
        boolean judgeIsInsert = false;
        if (judge) {
            judgeIsInsert = customerMapper.addCustomer(customer);
        }
        return judgeIsInsert;
    }

    public boolean insertCustomerPlusByUsername(Customer customer) {
        boolean judge = judgeIsUser(customer.getCustomerAccount());
        boolean judgeIsInsert = false;
        if (judge) {
            judgeIsInsert = customerMapper.addCustomerPlus(customer);
        }
        return judgeIsInsert;
    }

    public boolean insertManagerByUsername(Manager manager) {
        boolean judge = judgeIsUser(manager.getManagerUsername());
        boolean judgeIsInsert = false;
        if (!judge) {
            judgeIsInsert = managerMapper.insertManager(manager);
        }
        return judgeIsInsert;
    }

    /**
     * 每当新增一个角色时，便要在这里增加一行
     */
    private boolean judgeIsUser(String username) {

        System.out.println(managerMapper);
        Manager isManager = managerMapper.selectManagerByUsername(username);
        Customer isCustomer = customerMapper.selectCustomerByAccount(username);
        boolean judge = true;
        if (isCustomer == null && isManager == null) {
            judge = false;
        }
        return judge;
    }
}
