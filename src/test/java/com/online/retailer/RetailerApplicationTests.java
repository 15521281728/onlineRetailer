package com.online.retailer;

import com.online.retailer.mapper.CustomerMapper;
import com.online.retailer.model.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Array;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@SpringBootTest
class RetailerApplicationTests {
    @Autowired
    CustomerMapper customerMapper;
    @Test
    void testCustomerMapper() {
        java.util.Date now = new java.util.Date();
        Date date = new Date(now.getTime());
        Customer customer = new Customer();
//        customer.setUserId(1);
        customer.setUserAccount("155212817289");
        customer.setUserName("何顺龙2");
        customer.setUserPassword(1234567);
        customer.setUserAddress("增城");
        customer.setUserBirthday(date);
        customer.setUserIdentity("superVip");
        customer.setUserWallet(100);
        customer.setUserBank("农业银行");
        customer.setUserBankCard("123456789987654");
        customer.setUserGender("2");
        System.out.println(customerMapper.addUserPlus(customer));

//        customer = customerMapper.selectUserById(0);
        System.out.println(customer);
    }
    @Test
    void AAA(){
        System.out.println("aa");
    }
}
