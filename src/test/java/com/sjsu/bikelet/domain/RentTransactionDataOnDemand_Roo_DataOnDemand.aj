// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.sjsu.bikelet.domain;

import com.sjsu.bikelet.domain.BikeDataOnDemand;
import com.sjsu.bikelet.domain.BikeLetUserDataOnDemand;
import com.sjsu.bikelet.domain.RentTransaction;
import com.sjsu.bikelet.domain.RentTransactionDataOnDemand;
import com.sjsu.bikelet.service.RentTransactionService;
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

privileged aspect RentTransactionDataOnDemand_Roo_DataOnDemand {
    
    declare @type: RentTransactionDataOnDemand: @Component;
    
    private Random RentTransactionDataOnDemand.rnd = new SecureRandom();
    
    private List<RentTransaction> RentTransactionDataOnDemand.data;
    
    @Autowired
    BikeDataOnDemand RentTransactionDataOnDemand.bikeDataOnDemand;
    
    @Autowired
    BikeLetUserDataOnDemand RentTransactionDataOnDemand.bikeLetUserDataOnDemand;
    
    @Autowired
    RentTransactionService RentTransactionDataOnDemand.rentTransactionService;
    
    public RentTransaction RentTransactionDataOnDemand.getNewTransientRentTransaction(int index) {
        RentTransaction obj = new RentTransaction();
        setComments(obj, index);
        setFromStationId(obj, index);
        setRateId(obj, index);
        setRentEndDate(obj, index);
        setRentStartDate(obj, index);
        setStatus(obj, index);
        setTenantId(obj, index);
        setToStationId(obj, index);
        return obj;
    }
    
    public void RentTransactionDataOnDemand.setComments(RentTransaction obj, int index) {
        String comments = "comments_" + index;
        if (comments.length() > 500) {
            comments = comments.substring(0, 500);
        }
        obj.setComments(comments);
    }
    
    public void RentTransactionDataOnDemand.setFromStationId(RentTransaction obj, int index) {
        Integer fromStationId = new Integer(index);
        obj.setFromStationId(fromStationId);
    }
    
    public void RentTransactionDataOnDemand.setRateId(RentTransaction obj, int index) {
        Integer rateId = new Integer(index);
        obj.setRateId(rateId);
    }
    
    public void RentTransactionDataOnDemand.setRentEndDate(RentTransaction obj, int index) {
        Date rentEndDate = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND) + new Double(Math.random() * 1000).intValue()).getTime();
        obj.setRentEndDate(rentEndDate);
    }
    
    public void RentTransactionDataOnDemand.setRentStartDate(RentTransaction obj, int index) {
        Date rentStartDate = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND) + new Double(Math.random() * 1000).intValue()).getTime();
        obj.setRentStartDate(rentStartDate);
    }
    
    public void RentTransactionDataOnDemand.setStatus(RentTransaction obj, int index) {
        String status = "status_" + index;
        if (status.length() > 10) {
            status = status.substring(0, 10);
        }
        obj.setStatus(status);
    }
    
    public void RentTransactionDataOnDemand.setTenantId(RentTransaction obj, int index) {
        Long tenantId = new Long(index);
        obj.setTenantId(tenantId);
    }
    
    public void RentTransactionDataOnDemand.setToStationId(RentTransaction obj, int index) {
        Long toStationId = new Long(index);
        obj.setToStationId(toStationId);
    }
    
    public RentTransaction RentTransactionDataOnDemand.getSpecificRentTransaction(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        RentTransaction obj = data.get(index);
        Long id = obj.getId();
        return rentTransactionService.findRentTransaction(id);
    }
    
    public RentTransaction RentTransactionDataOnDemand.getRandomRentTransaction() {
        init();
        RentTransaction obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return rentTransactionService.findRentTransaction(id);
    }
    
    public boolean RentTransactionDataOnDemand.modifyRentTransaction(RentTransaction obj) {
        return false;
    }
    
    public void RentTransactionDataOnDemand.init() {
        int from = 0;
        int to = 10;
        data = rentTransactionService.findRentTransactionEntries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'RentTransaction' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<RentTransaction>();
        for (int i = 0; i < 10; i++) {
            RentTransaction obj = getNewTransientRentTransaction(i);
            try {
                rentTransactionService.saveRentTransaction(obj);
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
