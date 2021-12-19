package com.online.retailer;

import com.online.retailer.mapper.CustomerMapper;
import com.online.retailer.mapper.GoodsMapper;
import com.online.retailer.mapper.ManagerMapper;
import com.online.retailer.mapper.RedisDAO;
import com.online.retailer.model.Customer;
import com.online.retailer.model.Goods;
import com.online.retailer.model.Manager;
import com.online.retailer.service.InsertUserService;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Calendar;
import java.util.Date;

@SpringBootTest
class RetailerApplicationTests {
    @Autowired
    CustomerMapper customerMapper;
    @Autowired
    ManagerMapper managerMapper;
    @Autowired
    RabbitTemplate rabbitTemplate;
    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    RedisDAO redisDAO;
    @Autowired
    AmqpAdmin amqpAdmin;
    @Test
    void testCustomerMapper() {
        InsertUserService insertUser = new InsertUserService(customerMapper,managerMapper);
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String pwd = "123456";
        pwd = bCryptPasswordEncoder.encode(pwd);
        Date now = new Date();
//        Date date = new Date(now.getTime());
        Customer customer = new Customer();
//        customer.setUserId(1);
        customer.setCustomerAccount("15521281729");
        customer.setCustomerName("何顺龙");
        customer.setCustomerPassword(pwd);
        customer.setCustomerAddress("增城");
        customer.setCustomerBirthday(now);
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
//        Date date = new Date(now.getTime());
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

//        rabbitTemplate.convertAndSend("fanout_exchange","",customer);
        rabbitTemplate.convertAndSend("shopping_exchange","mail_router","尼玛");
    }

    @Test
    void testGoodsMapper(){
        Calendar calendar;
        Date date = new Date();
        Goods goods = new Goods();
        goods.setGoodsNum(100);
        goods.setGoodsDiscount(1);
        goods.setGoodsExpire(date);
        goods.setGoodsIdentifier("123456");
        goods.setGoodsImgUri("images/1.jpg");
        goods.setGoodsName("鼠标");
        goods.setGoodsPrice(200);
        goods.setGoodsProduceDate(date);
        goods.setGoodsProducer("中国");
        goodsMapper.insertGoods(goods);
    }

    @Test
    void add(){
        System.out.println("aaa");
        redisDAO.insertRedis("a","四四");

        Goods goods = goodsMapper.selectGoodsById(2);
        redisDAO.insertRedisPOJO("2",goods,Goods.class);
    }
    @Test
    void del(){

        redisDAO.deleteRedis("a");
    }
    @Test
    void update(){

        redisDAO.updateRedis("a","四su");
    }
    @Test
    void select(){
        System.out.println(redisDAO.selectRedis("goodsId_2"));
        Goods goods = (Goods) redisDAO.selectRedisPOJO("goodsId_2",Goods.class);
        System.out.println(goods);
    }

    @Test
    //进行交换机的创立与绑定
    void amqpConnect() {
        //定义fanout类型的交换器 交换器是啥？
        amqpAdmin.declareExchange(new FanoutExchange("shopping_exchange"));
        //定义两个持久化的队列，分别处理xx,zz的任务 队列是用来处理任务的
        amqpAdmin.declareQueue(new Queue("shopping_queue_createTransaction"));
        amqpAdmin.declareQueue(new Queue("shopping_queue_mail"));
        amqpAdmin.declareQueue(new Queue("shopping_queue_chat"));
        //绑定队列与交换器
        amqpAdmin.declareBinding(new Binding("shopping_queue_createTransaction"
                ,Binding.DestinationType.QUEUE,"shopping_exchange","",null));
        amqpAdmin.declareBinding(new Binding("shopping_queue_mail"
                ,Binding.DestinationType.QUEUE,"shopping_exchange","",null));
        amqpAdmin.declareBinding(new Binding("shopping_queue_chat"
                ,Binding.DestinationType.QUEUE,"shopping_exchange","",null));
    }
}
