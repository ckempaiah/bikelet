// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.sjsu.bikelet.service;

import com.sjsu.bikelet.domain.LicensePolicy;
import com.sjsu.bikelet.service.LicensePolicyServiceImpl;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

privileged aspect LicensePolicyServiceImpl_Roo_Service {
    
    declare @type: LicensePolicyServiceImpl: @Service;
    
    declare @type: LicensePolicyServiceImpl: @Transactional;
    
    public long LicensePolicyServiceImpl.countAllLicensePolicys() {
        return LicensePolicy.countLicensePolicys();
    }
    
    public void LicensePolicyServiceImpl.deleteLicensePolicy(LicensePolicy licensePolicy) {
        licensePolicy.remove();
    }
    
    public LicensePolicy LicensePolicyServiceImpl.findLicensePolicy(Long id) {
        return LicensePolicy.findLicensePolicy(id);
    }
    
    public List<LicensePolicy> LicensePolicyServiceImpl.findAllLicensePolicys() {
        return LicensePolicy.findAllLicensePolicys();
    }
    
    public List<LicensePolicy> LicensePolicyServiceImpl.findLicensePolicyEntries(int firstResult, int maxResults) {
        return LicensePolicy.findLicensePolicyEntries(firstResult, maxResults);
    }
    
    public void LicensePolicyServiceImpl.saveLicensePolicy(LicensePolicy licensePolicy) {
        licensePolicy.persist();
    }
    
    public LicensePolicy LicensePolicyServiceImpl.updateLicensePolicy(LicensePolicy licensePolicy) {
        return licensePolicy.merge();
    }
    
}