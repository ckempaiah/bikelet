// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.sjsu.bikelet.domain;

import com.sjsu.bikelet.domain.BikeLetRole;
import com.sjsu.bikelet.domain.BikeLetRoleDataOnDemand;
import com.sjsu.bikelet.service.BikeLetRoleService;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

privileged aspect BikeLetRoleDataOnDemand_Roo_DataOnDemand {
    
    declare @type: BikeLetRoleDataOnDemand: @Component;
    
    private Random BikeLetRoleDataOnDemand.rnd = new SecureRandom();
    
    private List<BikeLetRole> BikeLetRoleDataOnDemand.data;
    
    @Autowired
    BikeLetRoleService BikeLetRoleDataOnDemand.bikeLetRoleService;
    
    public BikeLetRole BikeLetRoleDataOnDemand.getNewTransientBikeLetRole(int index) {
        BikeLetRole obj = new BikeLetRole();
        setRoleName(obj, index);
        return obj;
    }
    
    public void BikeLetRoleDataOnDemand.setRoleName(BikeLetRole obj, int index) {
        String roleName = "roleName_" + index;
        obj.setRoleName(roleName);
    }
    
    public BikeLetRole BikeLetRoleDataOnDemand.getSpecificBikeLetRole(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        BikeLetRole obj = data.get(index);
        Long id = obj.getId();
        return bikeLetRoleService.findBikeLetRole(id);
    }
    
    public BikeLetRole BikeLetRoleDataOnDemand.getRandomBikeLetRole() {
        init();
        BikeLetRole obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return bikeLetRoleService.findBikeLetRole(id);
    }
    
    public boolean BikeLetRoleDataOnDemand.modifyBikeLetRole(BikeLetRole obj) {
        return false;
    }
    
    public void BikeLetRoleDataOnDemand.init() {
        int from = 0;
        int to = 10;
        data = bikeLetRoleService.findBikeLetRoleEntries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'BikeLetRole' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<BikeLetRole>();
        for (int i = 0; i < 10; i++) {
            BikeLetRole obj = getNewTransientBikeLetRole(i);
            try {
                bikeLetRoleService.saveBikeLetRole(obj);
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
