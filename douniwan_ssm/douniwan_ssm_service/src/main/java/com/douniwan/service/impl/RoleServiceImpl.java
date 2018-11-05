package com.douniwan.service.impl;

import com.douniwan.dao.IRoleDao;
import com.douniwan.dao.IRole_PermissionDao;
import com.douniwan.domain.Role;
import com.douniwan.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service("roleService")
@Transactional
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private IRoleDao roleDao;

    @Autowired
    private IRole_PermissionDao role_permissionDao;

    @Override
    public List<Role> findAll() throws Exception {
        return roleDao.findAll();
    }

    @Override
    public Role findById(String id) throws Exception {
        return roleDao.findById(id);
    }

    @Override
    public void save(Role role) throws Exception {
        roleDao.save(role);
    }

    @Override
    public Role findRoleByIdAndAllPermissions(String roleId) throws Exception {
        return roleDao.findRoleByIdAndAllPermission(roleId);
    }

    @Override
    public void addPermissionToRole(String roleId, String permissionId) throws Exception {
        role_permissionDao.addPermissionToRole(roleId,permissionId);
    }
}
