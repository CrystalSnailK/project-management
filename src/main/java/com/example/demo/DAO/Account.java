package com.example.demo.DAO;

import com.example.demo.Entities.UserEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;


@Mapper
public interface Account {
    @Select("select * from account where isable=1")
    public List<UserEntity> getAllAccounts();
    @Select("select * from account where ID = #{ID} AND isable=1")
    public UserEntity getByUsername(int ID);

    /**
     * @param ID: 要被修改的账户ID
     * @param password: 修改之后的密码
     */
    @Update("update account set password = #{password} where ID = #{ID}")
    public void updatePassword(int ID, String password);

    /**
     *
     * @param ID
     * @param password
     * @param role: 角色描述
     */
    @Insert("insert into account(ID,password, role) values(#{ID}, #{password}, #{role})")
    public void addAccount(int ID, String password, String role);
}
