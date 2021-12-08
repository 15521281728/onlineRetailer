package com.online.retailer.mapper;

import com.online.retailer.model.Manager;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface ManagerMapper {
    //增加管理员
    /**
     * 不建议直接插入，建议使用插入函数InsertUserService进行插入
     * 防止破坏了username/account的唯一性
     * */
    @Deprecated
    @Insert("insert into manager(manager_id,manager_username,manager_password,manager_identity,manager_name," +
            "manager_reallyName,manager_phone,manager_IDCard) value(#{managerId},#{managerUsername}," +
            "#{managerPassword},#{managerIdentity},#{managerName},#{managerReallyName},#{managerPhone},#{managerIDCard})")
    public boolean insertManager(Manager manager);
    //删除
    @Delete("delete from manager where manager_username = #{ManagerUsername}")
    public boolean deleteManagerByUsername(String ManagerUsername);
    //修改
    @Update("update manager set manager_username=#{managerUsername},manager_password=#{managerPassword}," +
            "manager_identity=#{managerIdentity},manager_name=#{managerName},manager_reallyName=#{managerReallyName}," +
            "manager_phone=#{managerPhone},manager_IDCard=#{managerIDCard}where manager_id = #{managerId}")
    public boolean updateManagerById(Manager manager);
    //查询
    @Select("select * from manager where manager_id = #{managerId}")
    public Manager selectManagerById(int ManagerId);
    @Select("select * from manager where manager_username = #{ManagerUsername}")
    public Manager selectManagerByUsername(String ManagerUsername);
    @Select("select * from manager where manager_IDCard = #{ManagerIDCard}")
    public Manager selectManagerByIDCard(int ManagerIDCard);
    @Select("select manager_identity from manager where manager_username = #{ManagerUsername}")
    public String selectManagerIdentityByUsername(String ManagerUsername);
}
