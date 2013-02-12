// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.sjsu.bikelet.domain;

import com.sjsu.bikelet.domain.LicensePolicy;
import com.sjsu.bikelet.model.LicenseTypeEnum;

privileged aspect LicensePolicy_Roo_JavaBean {
    
    public String LicensePolicy.getLicenseName() {
        return this.licenseName;
    }
    
    public void LicensePolicy.setLicenseName(String licenseName) {
        this.licenseName = licenseName;
    }
    
    public Double LicensePolicy.getLicenseCostPeruser() {
        return this.licenseCostPeruser;
    }
    
    public void LicensePolicy.setLicenseCostPeruser(Double licenseCostPeruser) {
        this.licenseCostPeruser = licenseCostPeruser;
    }
    
    public Double LicensePolicy.getLicenseBaseCost() {
        return this.licenseBaseCost;
    }
    
    public void LicensePolicy.setLicenseBaseCost(Double licenseBaseCost) {
        this.licenseBaseCost = licenseBaseCost;
    }
    
    public Integer LicensePolicy.getFreeTrialPeriodInDays() {
        return this.freeTrialPeriodInDays;
    }
    
    public void LicensePolicy.setFreeTrialPeriodInDays(Integer freeTrialPeriodInDays) {
        this.freeTrialPeriodInDays = freeTrialPeriodInDays;
    }
    
    public LicenseTypeEnum LicensePolicy.getLicenseType() {
        return this.licenseType;
    }
    
    public void LicensePolicy.setLicenseType(LicenseTypeEnum licenseType) {
        this.licenseType = licenseType;
    }
    
}
