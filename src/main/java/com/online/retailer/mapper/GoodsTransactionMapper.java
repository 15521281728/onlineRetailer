package com.online.retailer.mapper;

import com.online.retailer.model.Customer;
import com.online.retailer.model.Goods;
import com.online.retailer.model.GoodsTransaction;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Mapper
@Component
public interface GoodsTransactionMapper {
    //增加交易记录
    @Insert("insert into transaction(goods_id,user_id,transaction_time,transaction_price,transaction_harvestAddress," +
            "transaction_num) values(#{goodsId},#{userId},#{transactionTime},#{transactionPrice}," +
            "#{transactionHarvestAddress},#{transactionNum})")
    public boolean addGoodsTransactionLog(GoodsTransaction goodsTransaction);
    //删除交易记录
    @Delete("delete from transaction where transaction_id = #{transactionId}")
    public boolean deleteGoodsTransactionLogById(int transactionId);
    //修改交易记录
    @Deprecated
    @Update("update transaction set goods_id = #{goodsId},user_id = #{userId},transaction_time = #{transactionTime}," +
            "transaction_harvestAddress = #{transactionHarvestAddress},transaction_num = #{transactionNum}")
    public boolean updateGoodsTransactionLog(GoodsTransaction goodsTransaction);
    //查询交易记录
    @Select("select transaction_id from transaction where transaction_time=#{transactionTime}," +
            "user_id = #{userId},goods_id = #{goodsId}")
    public int selectTransactionId(GoodsTransaction goodsTransaction);
//    @Select("select * from transaction where transaction_time=#{transactionTime}," +
//            "user_id = #{userId},goods_id = #{goodsId}")
//    public GoodsTransaction selectTransaction(Date transactionTime,int userId,int goodsId);
    @Select("select * from transaction where transaction_id=#{transactionId}")
    public GoodsTransaction selectTransactionById(int transactionId);
    @Select("select * from transaction where user_id = #{userId}")
    public List<GoodsTransaction> selectTransactionsByUserId(int userId);
    @Select("select * from transaction where goods_id = #{GoodsId}")
    public List<GoodsTransaction> selectTransactionsByGoodsId(int GoodsId);
}
