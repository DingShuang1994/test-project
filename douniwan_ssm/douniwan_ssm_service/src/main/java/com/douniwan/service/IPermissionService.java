package com.douniwan.service;

import com.douniwan.domain.Permission;

import java.util.List;

public interface IPermissionService {

    List<Permission> findAll() throws Exception;

    Permission findById(String id) throws Exception;

    void save(Permission permission) throws Exception;
}
