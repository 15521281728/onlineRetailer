package com.online.retailer.service;

import com.online.retailer.mapper.ManagerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransactionService {
    //将商品信息从redis/mysql里面读出来
    @Autowired
    ManagerMapper managerMapper;
    public void addd(){
        System.out.println(managerMapper);
    }
}
