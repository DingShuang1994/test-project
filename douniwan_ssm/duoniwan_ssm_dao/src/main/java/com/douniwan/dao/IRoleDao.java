package com.douniwan.dao;

import com.douniwan.domain.Permission;
import com.douniwan.domain.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IRoleDao {
    //根据用户id查询用户所有角色
    @Select("select * from role where id in (select roleId from users_role where userId=#{userId})")
    @Results({
            @Result(id=true , property = "id" ,column="id"),
            @Result(property="roleName" , column = "roleName"),
            @Result(property="roleDesc" , column = "roleDesc"),
            @Result(property="roleDesc" , column = "roleDesc"),
            @Result(property="permissions" , column = "id" , many=@Many(select = "com.douniwan.dao.IPermissionDao.findPermissionByRoleId") ,javaType = List.class)
    })
    List<Role> findRolesByUserId(String userId) throws Exception;

    //查询所有角色
    @Select("select * from role ")
    List<Role> findAll() throws Exception;

    //根据角色id值查询角色详情
    @Select("select * from role where id = #{id}")
    @Results({
            @Result(id=true , property = "id" ,column="id"),
            @Result(property="roleName" , column = "roleName"),
            @Result(property="roleDesc" , column = "roleDesc"),
            @Result(property="roleDesc" , column = "roleDesc"),
            @Result(property="permissions" , column = "id" , many=@Many(select = "com.douniwan.dao.IPermissionDao.findPermissionByRoleId") ,javaType = List.class),
            @Result(property="users" , column = "id" , many=@Many(select = "com.douniwan.dao.IUserDao.findUsersByRoleId") ,javaType = List.class)
    })
    Role findById(String id) throws Exception;

    //根据权限id查询所有的角色
    @Insert("insert into role(roleName ,roleDesc) values(#{roleName},#{roleDesc})")
    void save(Role role) throws Exception;

    //根据权限id获取角色信息 查权限详情用的
    @Select("select * from role where id in (select roleId from role_permission where permissionId = #{permissionId})")
    @Results({
            @Result(id=true , property = "id" ,column="id"),
            @Result(property="roleName" , column = "roleName"),
            @Result(property="roleDesc" , column = "roleDesc"),
            @Result(property="roleDesc" , column = "roleDesc"),
            @Result(property="users" , column = "id" , many=@Many(select = "com.douniwan.dao.IUserDao.findUsersByRoleId") ,javaType = List.class)
    })
    List<Role> findRolesByPermissionId(String permissionId) throws Exception;

    //根据用户id查询用户没有的角色
    @Select("select * from role where id not in (select roleId from users_role where userId=#{userId})")
    List<Role> findRolesByNotUserId(String userId) throws Exception;

    //根据角色id获取角色没有的权限
    @Select("select * from role where id = #{roleId}")
    @Results({
            @Result(id=true , property = "id" ,column="id"),
            @Result(property="roleName" , column = "roleName"),
            @Result(property="roleDesc" , column = "roleDesc"),
            @Result(property="roleDesc" , column = "roleDesc"),
            @Result(property="permissions" , column = "id" , many=@Many(select = "com.douniwan.dao.IPermissionDao.findPermissionByNotRoleId") ,javaType = List.class)
    })
    Role findRoleByIdAndAllPermission(String roleId) throws Exception;
}
