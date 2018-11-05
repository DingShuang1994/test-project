package com.douniwan.dao;

import com.douniwan.domain.Permission;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IPermissionDao {
    @Select("select * from permission where id in (select permissionId from role_permission where roleId = #{id} )")
    List<Permission>  findPermissionByRoleId(String id) throws Exception;

    @Select("select * from permission")
    List<Permission> findAll() throws Exception;

    @Insert("insert into permission (permissionName , url) values(#{permissionName} ,#{url})")
    void save(Permission permission) throws Exception;

    @Select("select * from permission where id = #{id}")
    @Results({
            @Result(id=true,property = "id" , column = "id"),
            @Result(property = "permissionName" , column = "permissionName"),
            @Result(property = "url" , column = "url"),
            @Result(property = "roles" , column = "id",many = @Many(select = "com.douniwan.dao.IRoleDao.findRolesByPermissionId") ,javaType = List.class),
           })
    Permission findById(String id) throws Exception;

    @Select("select * from permission where id not in (select permissionId from role_permission where roleId = #{roleId} )")
    List<Permission>  findPermissionByNotRoleId(String roleId) throws Exception;
}
