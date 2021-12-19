package com.online.retailer.mapper;

import com.online.retailer.model.Goods;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface GoodsMapper {
    //增加商品
    @Insert("insert into goods(goods_name,goods_discount,goods_identifier,goods_imgUri,goods_expire,goods_produceDate,goods_producer," +
            "goods_num,goods_price) values(#{goodsName},#{goodsDiscount},#{goodsIdentifier},#{goodsImgUri},#{goodsExpire}," +
            "#{goodsProduceDate},#{goodsProducer},#{goodsNum},#{goodsPrice})")
    public boolean insertGoods(Goods goods);
    //删除商品
    @Delete("delete from goods where goods_id = #{goodsId}")
    public boolean deleteGoodsById(int goodsId);
    //修改商品
    @Update("update goods set goods_name=#{goodsName},goods_discount=#{goodsDiscount},goods_identifier=#{goodsIdentifier}," +
            "goods_imgUri=#{goodsImgUri},goods_expire=#{goodsExpire},goods_produceDate=#{goodsProduceDate}," +
            "goods_producer=#{goodsProducer},goods_num=#{goodsNum},goods_price=#{goodsPrice}")
    public boolean updateGoodsById(Goods goods);
    //查找商品
    @Select("select * from goods where goods_id = #{goodsId}")
    public Goods selectGoodsById(int goodsId);
    @Select("select * from goods where goods_name = #{goodsName}")
    public List<Goods> selectGoodsesByName(String goodsName);
}
