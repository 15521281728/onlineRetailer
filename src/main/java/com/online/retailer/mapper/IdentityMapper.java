package com.online.retailer.mapper;

import com.online.retailer.model.Identity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface IdentityMapper {

    @Select("select b.* from customer a,identity b where a.customer_account =#{account} and " +
            "b.identity_role = a.customer_identity")
    public List<Identity> findCustomerIdentityByAccount(String account);
    @Select("select b.* from manager a,identity b where a.manager_username =#{account} and " +
            "b.identity_role = a.manager_identity")
    public List<Identity> findManagerIdentityByAccount(String account);
}
