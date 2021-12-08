package com.online.retailer.mapper;

import com.online.retailer.model.Customer;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface CustomerMapper {
    //找自身的信息
    @Select("select * from customer where customer_id = #{customerId}")
    public Customer selectCustomerById(int customerId);

    @Select("select * from customer where customer_name = #{name}")
    public Customer selectCustomerByName(String name);

    @Select("select * from customer where customer_account = #{account}")
    public Customer selectCustomerByAccount(String account);
    //找用户名
    @Select("select customer_name from customer where customer_id = #{customerId}")
    public String selectCustomerNameById(int customerId);
    //找密码
    @Select("select customer_password from customer where customer_id = #{customerId}")
    public int selectCustomerPasswordById(int customerId);
    //找收货地址
    @Select("select customer_address from customer where customer_id = #{customerId}")
    public String selectCustomerAddressById(int customerId);
    //添加用户
    /**
     * 不建议直接插入，建议使用插入函数InsertUserService进行插入
     * 防止破坏了username/account的唯一性
     * */
    @Deprecated
    @Insert("insert into customer(customer_name, customer_account, customer_password,customer_address) values(#{customerName},#{customerAccount},#{customerPassword},'字段,uu')")
    public boolean addCustomer(Customer customer);
    /**
     * 不建议直接插入，建议使用插入函数InsertUserService进行插入
     * 防止破坏了username/account的唯一性
     * */
    @Deprecated
    @Insert("insert into customer(customer_name, customer_account, " +
            "customer_password,customer_wallet, customer_address, " +
            "customer_identity,customer_birthday,customer_bank,customer_bankCard,customer_gender) " +
            "values(#{customerName},#{customerAccount},#{customerPassword},#{customerWallet},#{customerAddress},#{customerIdentity},#{customerBirthday}" +
            ",#{customerBank},#{customerBankCard},#{customerGender})")
    public boolean addCustomerPlus(Customer customer);
    //删除用户
    @Delete("Delete from customer where customer_id=#{customerId}")
    public boolean deleteCustomerById(int customerId);
    //修改用户
    @Update("update customer set customer_name = #{customerName}, customer_account = #{customerAccount}," +
            " customer_password = #{customerPassword},customer_wallet = #{customerWallet} where customer_id = #{customerId}")
    public boolean updateCustomerById(Customer customer);
//    @Update("update Customer set Customer_id = #{CustomerId}, Customer_name = #{CustomerName}, Customer_account = #{CustomerAccount}, " +
//            "Customer_password = #{CustomerPassword},Customer_wallet = #{CustomerWallet}, Customer_address = #{CustomerAddress}, " +
//            "Customer_identity = #{CustomerIdentity},Customer_birthday=#{CustomerBirthday} where Customer_id = #{CustomerId}")
//    public void updateCustomerById(Customer customer);


//    @Update("update Customer set Customer_address = #{CustomerAddress} where Customer_id = #{CustomerId}")
//    public void updateCustomerAddressById(Customer customer);
}
