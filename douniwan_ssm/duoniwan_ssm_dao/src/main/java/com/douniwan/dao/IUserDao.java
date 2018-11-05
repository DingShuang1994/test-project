package com.douniwan.dao;

import com.douniwan.domain.UserInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserDao {
    @Select("select * from users where username = #{username}")
    @Results({
            @Result(id=true,property = "id" ,column = "id"),
            @Result(property = "username" ,column = "username"),
            @Result(property = "email" ,column = "email"),
            @Result(property = "password" ,column = "password"),
            @Result(property = "phoneNum", column = "phoneNum"),
            @Result(property = "status", column = "status"),
            @Result(property = "roles", column = "id" ,many = @Many(select = "com.douniwan.dao.IRoleDao.findRolesByUserId"),javaType = List.class),
    })
   UserInfo findByUsername(String username) throws Exception;


    @Select("select * from users ")
    List<UserInfo> findAll() throws Exception;

    @Insert("insert into users(email,username,password,phoneNum,status) values( #{email} ,#{username} ,#{password} ,#{phoneNum},#{status})")
    void save(UserInfo userInfo) throws Exception;

    @Select("select * from users where id = #{id}")
    @Results({
            @Result(id=true,property = "id" ,column = "id"),
            @Result(property = "username" ,column = "username"),
            @Result(property = "email" ,column = "email"),
            @Result(property = "password" ,column = "password"),
            @Result(property = "phoneNum", column = "phoneNum"),
            @Result(property = "status", column = "status"),
            @Result(property = "roles", column = "id" ,many = @Many(select = "com.douniwan.dao.IRoleDao.findRolesByUserId"),javaType = List.class),
    })
    UserInfo findById(String id) throws Exception;

    @Select("select * from users where id in (select userId from users_role where roleId=#{ roleId })")
    List<UserInfo> findUsersByRoleId(String roleId) throws Exception;

    @Select("select * from users where id = #{userId}")
    @Results({
            @Result(id=true,property = "id" ,column = "id"),
            @Result(property = "username" ,column = "username"),
            @Result(property = "email" ,column = "email"),
            @Result(property = "password" ,column = "password"),
            @Result(property = "phoneNum", column = "phoneNum"),
            @Result(property = "status", column = "status"),
            @Result(property = "roles", column = "id" ,many = @Many(select = "com.douniwan.dao.IRoleDao.findRolesByNotUserId"),javaType = List.class),
    })
    UserInfo  findUserByIdAndAllRole(String userId) throws Exception;
}
