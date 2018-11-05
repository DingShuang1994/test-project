package com.douniwan.service;

import com.douniwan.domain.UserInfo;
import com.douniwan.domain.Users_role;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService extends UserDetailsService {

    List<UserInfo> findAll() throws Exception;

    void save(UserInfo userInfo) throws Exception;

    UserInfo findById(String id) throws Exception;

    UserInfo  findUserByIdAndAllRole(String userId) throws Exception;

//    void addRoleToUser(Users_role users_role) throws Exception;
    void addRoleToUser(String userId ,String roleId) throws Exception;
}
