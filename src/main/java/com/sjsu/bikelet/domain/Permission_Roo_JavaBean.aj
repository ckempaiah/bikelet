// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.sjsu.bikelet.domain;

import com.sjsu.bikelet.domain.RolePermission;
import java.lang.Integer;
import java.lang.String;

privileged aspect Permission_Roo_JavaBean {
    
    public Integer Permission.getPermissionId() {
        return this.permissionId;
    }
    
    public void Permission.setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }
    
    public String Permission.getPermissionName() {
        return this.permissionName;
    }
    
    public void Permission.setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }
    
    public String Permission.getDescription() {
        return this.description;
    }
    
    public void Permission.setDescription(String description) {
        this.description = description;
    }
    
    public RolePermission Permission.getRolePermissions() {
        return this.rolePermissions;
    }
    
    public void Permission.setRolePermissions(RolePermission rolePermissions) {
        this.rolePermissions = rolePermissions;
    }
    
}
