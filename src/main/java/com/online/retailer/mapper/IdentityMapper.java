package com.online.retailer.mapper;

import com.online.retailer.model.Identity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface IdentityMapper {

    @Select("select user_identity from user where user_account =#{account}")
    public List<Identity> findIdentityByAccount(String account);
}
