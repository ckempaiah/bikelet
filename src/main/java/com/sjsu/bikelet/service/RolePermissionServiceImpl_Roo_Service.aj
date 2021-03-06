// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.sjsu.bikelet.service;

import com.sjsu.bikelet.domain.RolePermission;
import com.sjsu.bikelet.service.RolePermissionServiceImpl;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

privileged aspect RolePermissionServiceImpl_Roo_Service {
    
    declare @type: RolePermissionServiceImpl: @Service;
    
    declare @type: RolePermissionServiceImpl: @Transactional;
    
    public long RolePermissionServiceImpl.countAllRolePermissions() {
        return RolePermission.countRolePermissions();
    }
    
    public void RolePermissionServiceImpl.deleteRolePermission(RolePermission rolePermission) {
        rolePermission.remove();
    }
    
    public RolePermission RolePermissionServiceImpl.findRolePermission(Long id) {
        return RolePermission.findRolePermission(id);
    }
    
    public List<RolePermission> RolePermissionServiceImpl.findAllRolePermissions() {
        return RolePermission.findAllRolePermissions();
    }
    
    public List<RolePermission> RolePermissionServiceImpl.findRolePermissionEntries(int firstResult, int maxResults) {
        return RolePermission.findRolePermissionEntries(firstResult, maxResults);
    }
    
    public void RolePermissionServiceImpl.saveRolePermission(RolePermission rolePermission) {
        rolePermission.persist();
    }
    
    public RolePermission RolePermissionServiceImpl.updateRolePermission(RolePermission rolePermission) {
        return rolePermission.merge();
    }
    
}
