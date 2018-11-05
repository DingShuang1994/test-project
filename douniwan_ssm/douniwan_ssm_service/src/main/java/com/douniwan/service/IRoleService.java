package com.douniwan.service;

import com.douniwan.domain.Role;

import java.util.List;

public interface IRoleService {

    List<Role>  findAll() throws Exception;

    Role findById(String id) throws Exception;

    void save(Role role) throws Exception;

    Role findRoleByIdAndAllPermissions(String roleId) throws Exception;

    void addPermissionToRole(String roleId, String permissionId) throws Exception;
}
