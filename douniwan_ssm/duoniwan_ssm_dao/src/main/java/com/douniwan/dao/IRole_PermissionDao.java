package com.douniwan.dao;


import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IRole_PermissionDao {

    @Insert("insert into role_permission(permissionId , roleId) values(#{permissionId},#{roleId})")
//    void save(Users_role users_role) throws Exception;
    void addPermissionToRole(@Param("roleId") String roleId, @Param("permissionId") String permissionId) throws Exception;
}
