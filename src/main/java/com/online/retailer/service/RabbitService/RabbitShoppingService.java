package com.online.retailer.service.RabbitService;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitShoppingService {
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
}
