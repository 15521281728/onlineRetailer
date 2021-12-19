package com.online.retailer.mapper;

import com.online.retailer.model.Goods;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;
@Component
public class RedisDAO {
    //对redis数据库进行增删改查
    @Autowired
    RedisTemplate redisTemplate;

    //增
    public boolean insertRedis(String key, String val) {
        try {
            redisTemplate.setKeySerializer(StringRedisSerializer.UTF_8);
            redisTemplate.setValueSerializer(StringRedisSerializer.UTF_8);

            redisTemplate.opsForValue().set(key, val);
            return true;

        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean insertRedisPOJO(String key, Object val, Class<?> objectClass) {
        if (!objectClass.isInstance(val))return false;
        //对redisTemplate进行中文序列化
        redisTemplate.setKeySerializer(StringRedisSerializer.UTF_8);
        redisTemplate.setValueSerializer(StringRedisSerializer.UTF_8);
//        JSONObject jsonObject = JSONObject.fromObject(redisTemplate.opsForValue().get("goodsId_" + key));
//        Goods goods = (Goods) jsonObject.toBean(jsonObject, Goods.class);
        Object object = objectClass.cast(val);
//        goods.setGoodsName("111");
        redisTemplate.opsForValue().set(key, JSONObject.fromObject(object).toString(), 1, TimeUnit.DAYS);
        return true;
    }
    //删
    public void deleteRedis(String key){
        //对redisTemplate进行中文序列化
        redisTemplate.setKeySerializer(StringRedisSerializer.UTF_8);
        redisTemplate.setValueSerializer(StringRedisSerializer.UTF_8);

        redisTemplate.delete(key);
    }
    //改
    public boolean updateRedis(String key, String val) {
        try {
            redisTemplate.setKeySerializer(StringRedisSerializer.UTF_8);
            redisTemplate.setValueSerializer(StringRedisSerializer.UTF_8);

            redisTemplate.opsForValue().set(key, val);
            return true;

        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }
    //查
    public String selectRedis(String key){

        //对redisTemplate进行中文序列化
        redisTemplate.setKeySerializer(StringRedisSerializer.UTF_8);
        redisTemplate.setValueSerializer(StringRedisSerializer.UTF_8);

        String s = (String) redisTemplate.opsForValue().get(key);
        return s;
    }

    public Object selectRedisPOJO(String key,Class<?> objectClass){

        //对redisTemplate进行中文序列化
        redisTemplate.setKeySerializer(StringRedisSerializer.UTF_8);
        redisTemplate.setValueSerializer(StringRedisSerializer.UTF_8);

        JSONObject jsonObject = JSONObject.fromObject(redisTemplate.opsForValue().get(key));
        Object object = jsonObject.toBean(jsonObject, objectClass);
        return object;
    }
}
