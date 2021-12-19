# 韦校长的小卖铺

韦校长的小卖铺是一个电商系统，后端基于SpringBoot研发 ，前端使用Vue、JQuery等开发，系统代码全部开源

## 项目地址

github镜像：https://github.com/15521281728/onlineRetailer.git

## 功能列表

### web前端功能

1. 首页
   1. 搜索商品
   2. 秒杀活动
2. 商品
   1. 商品的增删改查
   2. 购物车
   3. 在线客服聊天
3. 订单
   1. 商品订单
4. 个人
   1. 个人信息
   2. 我的订单
5. 登陆/注册
   1. 登陆
   2. 注册后自动登录



### 后端技术选型

1. 框架：SpringMVC
2. 数据库：mysql，redis
3. 消息中间件：RabbitMQ
4. 日志处理：log4j



### 数据库设计

1. goods

   包含字段（goodsPrice，goodsId，goodsName，goodsDiscount，goodsIdentifier，goodsImgUri，goodsExpire，goodsProduceDate，goodsProducer，goodsNum）

2. customer

   包含字段（CustomerId，CustomerName，CustomerAccount，CustomerPassword，CustomerWallet，CustomerAddress，CustomerIdentity，CustomerBirthday，CustomerBankCard，CustomerBank，CustomerGender）

3. goodsTransaction（goodsId，userId，transactionTime，transactionPrice，transactionHarvestAddress，transactionId，transactionNum）

4. identity

   包含字段（identityId，identityRole）

5. manager

   包含字段（managerId，managerUsername，managerPassword，managerIdentity，managerName，managerReallyName，managerPhone，managerIDCard）

