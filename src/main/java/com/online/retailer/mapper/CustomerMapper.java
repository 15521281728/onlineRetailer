package com.online.retailer.mapper;

import com.online.retailer.model.Customer;
import org.apache.ibatis.annotations.*;

@Mapper
public interface CustomerMapper {
    //找自身的信息
    @Select("select * from user where user_id = #{userId}")
    public Customer selectUserById(int userId);

    @Select("select * from user where user_name = #{name}")
    public Customer selectUserByName(String name);

    @Select("select * from user where user_account = #{account}")
    public Customer selectUserByAccount(String account);
    //找用户名
    @Select("select user_name from user where user_id = #{userId}")
    public String selectUserNameById(int userId);
    //找密码
    @Select("select user_password from user where user_id = #{userId}")
    public int selectUserPasswordById(int userId);
    //找收货地址
    @Select("select user_address from user where user_id = #{userId}")
    public String selectUserAddressById(int userId);
    //添加用户
    @Insert("insert into user(user_name, user_account, user_password,user_address) values(#{userName},#{userAccount},#{userPassword},'字段,uu')")
    public boolean addUser(Customer customer);
    @Insert("insert into user(user_name, user_account, " +
            "user_password,user_wallet, user_address, " +
            "user_identity,user_birthday,user_bank,user_bankCard,user_gender) " +
            "values(#{userName},#{userAccount},#{userPassword},#{userWallet},#{userAddress},#{userIdentity},#{userBirthday}" +
            ",#{userBank},#{userBankCard},#{userGender})")
    public boolean addUserPlus(Customer customer);
    //删除用户
    @Delete("Delete from user where user_id=#{userId}")
    public boolean deleteUserById(int userId);
    //修改用户
    @Update("update user set user_id = #{userId}, user_name = #{userName}, user_account = #{userAccount}, user_password = #{userPassword},user_wallet = #{userWallet} where user_id = #{userId}")
    public boolean updateUserById(Customer customer);
//    @Update("update user set user_id = #{userId}, user_name = #{userName}, user_account = #{userAccount}, " +
//            "user_password = #{userPassword},user_wallet = #{userWallet}, user_address = #{userAddress}, " +
//            "user_identity = #{userIdentity},user_birthday=#{userBirthday} where user_id = #{userId}")
//    public void updateUserById(Customer customer);


//    @Update("update user set user_address = #{userAddress} where user_id = #{userId}")
//    public void updateUserAddressById(Customer customer);
}
