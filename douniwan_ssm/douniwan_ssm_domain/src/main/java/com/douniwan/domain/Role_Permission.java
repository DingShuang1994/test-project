package com.douniwan.domain;

import java.io.Serializable;

public class Role_Permission implements Serializable{
    private String permissionId;
    private String roleId;

    public String getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "Role_Permission{" +
                "permissionId='" + permissionId + '\'' +
                ", roleId='" + roleId + '\'' +
                '}';
    }
}
