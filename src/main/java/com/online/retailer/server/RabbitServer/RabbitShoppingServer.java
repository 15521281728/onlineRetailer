package com.online.retailer.server.RabbitServer;

import com.online.retailer.mapper.GoodsTransactionMapper;
import com.online.retailer.model.GoodsTransaction;
import net.sf.json.JSONObject;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitShoppingServer {
    @Autowired
    GoodsTransactionMapper goodsTransactionMapper;
    //发送短信服务
    @RabbitListener(queues = "fanout_queue_email")
    public void sendEmailToOutLook(Message message){
        byte[] oper = message.getBody();
        String messageBody = new String(oper);
        System.out.println(messageBody);
    }

    //制定任务计划程序
    @RabbitListener(queues = "fanout_queue_sms")
    public void createTask(Message message){
        byte[] content = message.getBody();
        String s = new String(content);
        System.out.println(s);
    }
    //添加交易记录
    @RabbitListener(queues = "shopping_queue_createTransaction")
    public void createTransaction(Message message){
        byte[] content = message.getBody();
        String s = new String(content);
        boolean isCreate = false;

        JSONObject jsonObject = JSONObject.fromObject(s);
        GoodsTransaction goodsTransaction = (GoodsTransaction) JSONObject.toBean(jsonObject,GoodsTransaction.class);
        isCreate = goodsTransactionMapper.addGoodsTransactionLog(goodsTransaction);
        if (isCreate){
            System.out.println("成交");
        }
        else{
            System.out.println("信息录入失败！");
        }
        System.out.println(s);
    }
    //发送邮件
    @RabbitListener(queues = "shopping_queue_mail")
    public void shoppingMail(Message message){
        byte[] content = message.getBody();
        String s = new String(content);
        System.out.println(s);
    }
    //建立聊天
    @RabbitListener(queues = "shopping_queue_chat")
    public void weChat(Message message){
        byte[] content = message.getBody();
        String s = new String(content);
        System.out.println(s);
    }
}
