// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.sjsu.bikelet.domain;

import com.sjsu.bikelet.domain.LicensePolicy;
import com.sjsu.bikelet.domain.LicensePolicyDataOnDemand;
import com.sjsu.bikelet.domain.Tenant;
import com.sjsu.bikelet.domain.TenantDataOnDemand;
import com.sjsu.bikelet.domain.TenantLicensePolicy;
import com.sjsu.bikelet.domain.TenantLicensePolicyDataOnDemand;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

privileged aspect TenantLicensePolicyDataOnDemand_Roo_DataOnDemand {
    
    declare @type: TenantLicensePolicyDataOnDemand: @Component;
    
    private Random TenantLicensePolicyDataOnDemand.rnd = new SecureRandom();
    
    private List<TenantLicensePolicy> TenantLicensePolicyDataOnDemand.data;
    
    @Autowired
    private LicensePolicyDataOnDemand TenantLicensePolicyDataOnDemand.licensePolicyDataOnDemand;
    
    @Autowired
    private TenantDataOnDemand TenantLicensePolicyDataOnDemand.tenantDataOnDemand;
    
    public TenantLicensePolicy TenantLicensePolicyDataOnDemand.getNewTransientTenantLicensePolicy(int index) {
        TenantLicensePolicy obj = new TenantLicensePolicy();
        setIsTrial(obj, index);
        setLicenseEndDate(obj, index);
        setLicenseId(obj, index);
        setLicenseStartDate(obj, index);
        setTenantId(obj, index);
        return obj;
    }
    
    public void TenantLicensePolicyDataOnDemand.setIsTrial(TenantLicensePolicy obj, int index) {
        Boolean isTrial = Boolean.TRUE;
        obj.setIsTrial(isTrial);
    }
    
    public void TenantLicensePolicyDataOnDemand.setLicenseEndDate(TenantLicensePolicy obj, int index) {
        Date licenseEndDate = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND) + new Double(Math.random() * 1000).intValue()).getTime();
        obj.setLicenseEndDate(licenseEndDate);
    }
    
    public void TenantLicensePolicyDataOnDemand.setLicenseId(TenantLicensePolicy obj, int index) {
        LicensePolicy licenseId = licensePolicyDataOnDemand.getRandomLicensePolicy();
        obj.setLicenseId(licenseId);
    }
    
    public void TenantLicensePolicyDataOnDemand.setLicenseStartDate(TenantLicensePolicy obj, int index) {
        Date licenseStartDate = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND) + new Double(Math.random() * 1000).intValue()).getTime();
        obj.setLicenseStartDate(licenseStartDate);
    }
    
    public void TenantLicensePolicyDataOnDemand.setTenantId(TenantLicensePolicy obj, int index) {
        Tenant tenantId = tenantDataOnDemand.getSpecificTenant(index);
        obj.setTenantId(tenantId);
    }
    
    public TenantLicensePolicy TenantLicensePolicyDataOnDemand.getSpecificTenantLicensePolicy(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        TenantLicensePolicy obj = data.get(index);
        Long id = obj.getId();
        return TenantLicensePolicy.findTenantLicensePolicy(id);
    }
    
    public TenantLicensePolicy TenantLicensePolicyDataOnDemand.getRandomTenantLicensePolicy() {
        init();
        TenantLicensePolicy obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return TenantLicensePolicy.findTenantLicensePolicy(id);
    }
    
    public boolean TenantLicensePolicyDataOnDemand.modifyTenantLicensePolicy(TenantLicensePolicy obj) {
        return false;
    }
    
    public void TenantLicensePolicyDataOnDemand.init() {
        int from = 0;
        int to = 10;
        data = TenantLicensePolicy.findTenantLicensePolicyEntries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'TenantLicensePolicy' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<TenantLicensePolicy>();
        for (int i = 0; i < 10; i++) {
            TenantLicensePolicy obj = getNewTransientTenantLicensePolicy(i);
            try {
                obj.persist();
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
