package com.online.retailer;

import com.online.retailer.mapper.CustomerMapper;
import com.online.retailer.mapper.GoodsTransactionMapper;
import com.online.retailer.mapper.ManagerMapper;
import com.online.retailer.model.Customer;
import com.online.retailer.model.GoodsTransaction;
import com.online.retailer.model.Manager;
import com.online.retailer.service.InsertUserService;
import com.online.retailer.service.TransactionService;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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
    @Autowired
    ManagerMapper managerMapper;
    @Autowired
    RabbitTemplate rabbitTemplate;
    @Test
    void testCustomerMapper() {
        InsertUserService insertUser = new InsertUserService(customerMapper,managerMapper);
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String pwd = "123456";
        pwd = bCryptPasswordEncoder.encode(pwd);
        java.util.Date now = new java.util.Date();
        Date date = new Date(now.getTime());
        Customer customer = new Customer();
//        customer.setUserId(1);
        customer.setCustomerAccount("155212817289");
        customer.setCustomerName("何顺龙2");
        customer.setCustomerPassword("1234567");
        customer.setCustomerAddress("增城");
        customer.setCustomerBirthday(date);
        customer.setCustomerIdentity("superVip");
        customer.setCustomerWallet(100);
        customer.setCustomerBank("农业银行");
        customer.setCustomerBankCard("123456789987654");
        customer.setCustomerGender("2");
        System.out.println(insertUser.insertCustomerPlusByUsername(customer));

//        customer = customerMapper.selectUserById(0);
        System.out.println(customer);
    }
    @Test
    void testManagerMapper() {
//        System.out.println(managerMapper);
//        System.out.println(applicationContext.getBean(managerMapper.getClass()));
//        TransactionService transactionService = new TransactionService();
//        transactionService.addd();
        InsertUserService insertUser = new InsertUserService(customerMapper,managerMapper);
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String pwd = "123456";
        pwd = bCryptPasswordEncoder.encode(pwd);
        java.util.Date now = new java.util.Date();
        Date date = new Date(now.getTime());
        Manager manager = new Manager();
//        customer.setUserId(1);
        manager.setManagerUsername("15521281728");
        manager.setManagerName("RC_D");
        manager.setManagerPassword(pwd);
        manager.setManagerIdentity("manager");
        manager.setManagerReallyName("何顺龙");
        manager.setManagerIDCard("123456789");
        manager.setManagerPhone("15521281728");

        System.out.println(insertUser.insertManagerByUsername(manager));

//        customer = customerMapper.selectUserById(0);
        System.out.println(manager);
    }
    @Test
    void AAA(){
        System.out.println("aa");
    }

    @Test
    void amqpAPI(){
        Customer customer = new Customer();
        customer.setCustomerId(1);
        customer.setCustomerName("aaa");
        rabbitTemplate.convertAndSend("fanout_exchange","",customer);
    }
}
