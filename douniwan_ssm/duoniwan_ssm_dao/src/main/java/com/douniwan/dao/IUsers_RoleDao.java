package com.douniwan.dao;


import com.douniwan.domain.Users_role;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IUsers_RoleDao {

    @Insert("insert into users_role(userId , roleId) values(#{userId},#{roleId})")
//    void save(Users_role users_role) throws Exception;
    void save(@Param("userId")String userId ,@Param("roleId") String roleId) throws Exception;
}
