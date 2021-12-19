package com.online.retailer.service;

import com.fasterxml.jackson.databind.ser.std.StringSerializer;
import com.online.retailer.mapper.CustomerMapper;
import com.online.retailer.mapper.GoodsMapper;
import com.online.retailer.model.Customer;
import com.online.retailer.model.Goods;
//import com.sun.tools.javac.util.Convert;
import com.online.retailer.model.GoodsTransaction;
import net.sf.json.JSONObject;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.TimeUnit;
//import net.sf.JSONObject;

@Service
public class GoodsService {
    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    GoodsMapper goodsMapper;

    @Autowired
    CustomerMapper customerMapper;

    @Autowired
    RabbitTemplate rabbitTemplate;

    public Goods searchGoods(int GoodsId) {
        //对redisTemplate进行中文序列化
        redisTemplate.setKeySerializer(StringRedisSerializer.UTF_8);
        redisTemplate.setValueSerializer(StringRedisSerializer.UTF_8);
        JSONObject jsonObject = JSONObject.fromObject(redisTemplate.opsForValue().get("goodsId_" + GoodsId));
        Goods goods = (Goods) jsonObject.toBean(jsonObject, Goods.class);
        if (goods != null) {
            return goods;
        } else {
            goods = goodsMapper.selectGoodsById(GoodsId);
            //以json格式写入redis
            if (goods != null) {
                redisTemplate.opsForValue().set("goodsId_" + GoodsId, JSONObject.fromObject(goods).toString(), 1, TimeUnit.DAYS);
            }
            return goods;
        }
    }

    public boolean buyGoods(int GoodsId, int CustomerId, int turnover) {
        //对redisTemplate进行中文序列化
        redisTemplate.setKeySerializer(StringRedisSerializer.UTF_8);
        redisTemplate.setValueSerializer(StringRedisSerializer.UTF_8);

        int goodsNum;
        int goodsPrice;
        Customer customer;
        int customerWallet;
        boolean isBuyJudge = false;
        JSONObject jsonObject = JSONObject.fromObject(redisTemplate.opsForValue().get("goodsId_" + GoodsId));
        Goods goods = (Goods) jsonObject.toBean(jsonObject, Goods.class);

        customer = customerMapper.selectCustomerById(CustomerId);
        customerWallet = customer.getCustomerWallet();

        if (goods != null) {
            goodsNum = goods.getGoodsNum();
            goodsNum -= turnover;
            goodsPrice = goods.getGoodsPrice();
            boolean isBuy = customerWallet > goodsPrice;
            if (!isBuy) {
                System.out.println("宝贝，你买不起唉。");
            } else if (goodsNum > 0) {
                goods.setGoodsNum(goodsNum);
                customer.setCustomerWallet(customerWallet - goodsPrice);
                //先更新mysql数据库
                goodsMapper.updateGoodsById(goods);
                customerMapper.updateCustomerById(customer);
                //后更新缓存
                redisTemplate.opsForValue().set("goodsId_" + GoodsId, JSONObject.fromObject(goods).toString(), 1, TimeUnit.DAYS);
                isBuyJudge = true;
            } else {
                System.out.println("宝贝，没货了");
            }
        } else {
            goods = goodsMapper.selectGoodsById(GoodsId);
            //以json格式写入redis
            if (goods != null) {
                goodsNum = goods.getGoodsNum();
                goodsPrice = goods.getGoodsPrice();
                boolean isBuy = customerWallet > goodsPrice;
                if (!isBuy) {
                    System.out.println("宝贝，你买不起唉。");
                } else if (goodsNum > 0) {
                    goodsNum -= turnover;
                    goods.setGoodsNum(goodsNum);
                    customer.setCustomerWallet(customerWallet - goodsPrice);

                    goodsMapper.updateGoodsById(goods);
                    customerMapper.updateCustomerById(customer);

                    redisTemplate.opsForValue().set("goodsId_" + GoodsId, JSONObject.fromObject(goods).toString(), 1, TimeUnit.DAYS);
                    System.out.println("怕不是爬虫买的东西，或者缓存被清理了？");
                    isBuyJudge = true;
                } else {
                    System.out.println("宝贝,没商品了");
                }
            } else {
                System.out.println("商品不存在");
            }
        }

        //借由rabbitmq来实现完成交易后的两个捆绑服务
        GoodsTransaction goodsTransaction = new GoodsTransaction();
        goodsTransaction.setGoodsId(goods.getGoodsId());
        goodsTransaction.setUserId(customer.getCustomerId());
        goodsTransaction.setTransactionHarvestAddress(customer.getCustomerAddress());
        goodsTransaction.setTransactionNum(turnover);
        goodsTransaction.setTransactionPrice(goods.getGoodsPrice());
        goodsTransaction.setTransactionTime(new Date());
        if (isBuyJudge) {
            //增加交易记录
            rabbitTemplate.convertAndSend("shopping_exchange", "createTransaction_router", goodsTransaction);
            //发送邮件
            rabbitTemplate.convertAndSend("shopping_exchange", "mail_router", goodsTransaction);
            return true;
        } else return false;
    }
}

