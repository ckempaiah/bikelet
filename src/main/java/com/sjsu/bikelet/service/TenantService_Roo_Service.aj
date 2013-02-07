// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.sjsu.bikelet.service;

import com.sjsu.bikelet.domain.Tenant;
import com.sjsu.bikelet.service.TenantService;
import java.util.List;

privileged aspect TenantService_Roo_Service {
    
    public abstract long TenantService.countAllTenants();    
    public abstract void TenantService.deleteTenant(Tenant tenant);    
    public abstract Tenant TenantService.findTenant(Long id);    
    public abstract List<Tenant> TenantService.findAllTenants();    
    public abstract List<Tenant> TenantService.findTenantEntries(int firstResult, int maxResults);    
    public abstract void TenantService.saveTenant(Tenant tenant);    
    public abstract Tenant TenantService.updateTenant(Tenant tenant);    
}
