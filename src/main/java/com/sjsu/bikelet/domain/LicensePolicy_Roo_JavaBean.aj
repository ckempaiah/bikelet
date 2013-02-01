// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.sjsu.bikelet.domain;

import com.sjsu.bikelet.domain.TenantLicensePolicy;
import java.lang.Double;
import java.lang.Integer;
import java.lang.String;

privileged aspect LicensePolicy_Roo_JavaBean {
    
    public Integer LicensePolicy.getLicenseId() {
        return this.licenseId;
    }
    
    public void LicensePolicy.setLicenseId(Integer licenseId) {
        this.licenseId = licenseId;
    }
    
    public String LicensePolicy.getLicenseName() {
        return this.licenseName;
    }
    
    public void LicensePolicy.setLicenseName(String licenseName) {
        this.licenseName = licenseName;
    }
    
    public Double LicensePolicy.getLicenseCostPerUser() {
        return this.licenseCostPerUser;
    }
    
    public void LicensePolicy.setLicenseCostPerUser(Double licenseCostPerUser) {
        this.licenseCostPerUser = licenseCostPerUser;
    }
    
    public Integer LicensePolicy.getLicenseType() {
        return this.licenseType;
    }
    
    public void LicensePolicy.setLicenseType(Integer licenseType) {
        this.licenseType = licenseType;
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
    
    public TenantLicensePolicy LicensePolicy.getTenLicPolicy() {
        return this.tenLicPolicy;
    }
    
    public void LicensePolicy.setTenLicPolicy(TenantLicensePolicy tenLicPolicy) {
        this.tenLicPolicy = tenLicPolicy;
    }
    
}
