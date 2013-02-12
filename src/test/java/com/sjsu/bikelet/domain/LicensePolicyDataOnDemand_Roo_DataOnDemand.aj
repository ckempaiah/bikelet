// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.sjsu.bikelet.domain;

import com.sjsu.bikelet.domain.LicensePolicy;
import com.sjsu.bikelet.domain.LicensePolicyDataOnDemand;
import com.sjsu.bikelet.model.LicenseTypeEnum;
import com.sjsu.bikelet.service.LicensePolicyService;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

privileged aspect LicensePolicyDataOnDemand_Roo_DataOnDemand {
    
    declare @type: LicensePolicyDataOnDemand: @Component;
    
    private Random LicensePolicyDataOnDemand.rnd = new SecureRandom();
    
    private List<LicensePolicy> LicensePolicyDataOnDemand.data;
    
    @Autowired
    LicensePolicyService LicensePolicyDataOnDemand.licensePolicyService;
    
    public LicensePolicy LicensePolicyDataOnDemand.getNewTransientLicensePolicy(int index) {
        LicensePolicy obj = new LicensePolicy();
        setFreeTrialPeriodInDays(obj, index);
        setLicenseBaseCost(obj, index);
        setLicenseCostPeruser(obj, index);
        setLicenseName(obj, index);
        setLicenseType(obj, index);
        return obj;
    }
    
    public void LicensePolicyDataOnDemand.setFreeTrialPeriodInDays(LicensePolicy obj, int index) {
        Integer freeTrialPeriodInDays = new Integer(index);
        obj.setFreeTrialPeriodInDays(freeTrialPeriodInDays);
    }
    
    public void LicensePolicyDataOnDemand.setLicenseBaseCost(LicensePolicy obj, int index) {
        Double licenseBaseCost = new Integer(index).doubleValue();
        obj.setLicenseBaseCost(licenseBaseCost);
    }
    
    public void LicensePolicyDataOnDemand.setLicenseCostPeruser(LicensePolicy obj, int index) {
        Double licenseCostPeruser = new Integer(index).doubleValue();
        obj.setLicenseCostPeruser(licenseCostPeruser);
    }
    
    public void LicensePolicyDataOnDemand.setLicenseName(LicensePolicy obj, int index) {
        String licenseName = "licenseName_" + index;
        if (licenseName.length() > 100) {
            licenseName = licenseName.substring(0, 100);
        }
        obj.setLicenseName(licenseName);
    }
    
    public void LicensePolicyDataOnDemand.setLicenseType(LicensePolicy obj, int index) {
        LicenseTypeEnum licenseType = LicenseTypeEnum.class.getEnumConstants()[0];
        obj.setLicenseType(licenseType);
    }
    
    public LicensePolicy LicensePolicyDataOnDemand.getSpecificLicensePolicy(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        LicensePolicy obj = data.get(index);
        Long id = obj.getId();
        return licensePolicyService.findLicensePolicy(id);
    }
    
    public LicensePolicy LicensePolicyDataOnDemand.getRandomLicensePolicy() {
        init();
        LicensePolicy obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return licensePolicyService.findLicensePolicy(id);
    }
    
    public boolean LicensePolicyDataOnDemand.modifyLicensePolicy(LicensePolicy obj) {
        return false;
    }
    
    public void LicensePolicyDataOnDemand.init() {
        int from = 0;
        int to = 10;
        data = licensePolicyService.findLicensePolicyEntries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'LicensePolicy' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<LicensePolicy>();
        for (int i = 0; i < 10; i++) {
            LicensePolicy obj = getNewTransientLicensePolicy(i);
            try {
                licensePolicyService.saveLicensePolicy(obj);
            } catch (ConstraintViolationException e) {
                StringBuilder msg = new StringBuilder();
                for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                    ConstraintViolation<?> cv = iter.next();
                    msg.append("[").append(cv.getConstraintDescriptor()).append(":").append(cv.getMessage()).append("=").append(cv.getInvalidValue()).append("]");
                }
                throw new RuntimeException(msg.toString(), e);
            }
            obj.flush();
            data.add(obj);
        }
    }
    
}
