// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.sjsu.bikelet.domain;

import com.sjsu.bikelet.domain.BikeLetUser;
import com.sjsu.bikelet.domain.BikeLetUserDataOnDemand;
import com.sjsu.bikelet.domain.Bill;
import com.sjsu.bikelet.domain.BillDataOnDemand;
import com.sjsu.bikelet.domain.PaymentInfo;
import com.sjsu.bikelet.domain.PaymentInfoDataOnDemand;
import com.sjsu.bikelet.domain.PaymentTransaction;
import com.sjsu.bikelet.domain.PaymentTransactionDataOnDemand;
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

privileged aspect PaymentTransactionDataOnDemand_Roo_DataOnDemand {
    
    declare @type: PaymentTransactionDataOnDemand: @Component;
    
    private Random PaymentTransactionDataOnDemand.rnd = new SecureRandom();
    
    private List<PaymentTransaction> PaymentTransactionDataOnDemand.data;
    
    @Autowired
    private BillDataOnDemand PaymentTransactionDataOnDemand.billDataOnDemand;
    
    @Autowired
    private PaymentInfoDataOnDemand PaymentTransactionDataOnDemand.paymentInfoDataOnDemand;
    
    @Autowired
    private BikeLetUserDataOnDemand PaymentTransactionDataOnDemand.bikeLetUserDataOnDemand;
    
    public PaymentTransaction PaymentTransactionDataOnDemand.getNewTransientPaymentTransaction(int index) {
        PaymentTransaction obj = new PaymentTransaction();
        setBillId(obj, index);
        setDateOfTransaction(obj, index);
        setDescription(obj, index);
        setPaymentId(obj, index);
        setPermissionName(obj, index);
        setStatus(obj, index);
        setUserId(obj, index);
        return obj;
    }
    
    public void PaymentTransactionDataOnDemand.setBillId(PaymentTransaction obj, int index) {
        Bill billId = billDataOnDemand.getSpecificBill(index);
        obj.setBillId(billId);
    }
    
    public void PaymentTransactionDataOnDemand.setDateOfTransaction(PaymentTransaction obj, int index) {
        Date dateOfTransaction = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND) + new Double(Math.random() * 1000).intValue()).getTime();
        obj.setDateOfTransaction(dateOfTransaction);
    }
    
    public void PaymentTransactionDataOnDemand.setDescription(PaymentTransaction obj, int index) {
        String description = "description_" + index;
        if (description.length() > 100) {
            description = description.substring(0, 100);
        }
        obj.setDescription(description);
    }
    
    public void PaymentTransactionDataOnDemand.setPaymentId(PaymentTransaction obj, int index) {
        PaymentInfo paymentId = paymentInfoDataOnDemand.getRandomPaymentInfo();
        obj.setPaymentId(paymentId);
    }
    
    public void PaymentTransactionDataOnDemand.setPermissionName(PaymentTransaction obj, int index) {
        String permissionName = "permissionName_" + index;
        if (permissionName.length() > 30) {
            permissionName = permissionName.substring(0, 30);
        }
        obj.setPermissionName(permissionName);
    }
    
    public void PaymentTransactionDataOnDemand.setStatus(PaymentTransaction obj, int index) {
        Integer status = new Integer(index);
        obj.setStatus(status);
    }
    
    public void PaymentTransactionDataOnDemand.setUserId(PaymentTransaction obj, int index) {
        BikeLetUser userId = bikeLetUserDataOnDemand.getRandomBikeLetUser();
        obj.setUserId(userId);
    }
    
    public PaymentTransaction PaymentTransactionDataOnDemand.getSpecificPaymentTransaction(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        PaymentTransaction obj = data.get(index);
        Long id = obj.getId();
        return PaymentTransaction.findPaymentTransaction(id);
    }
    
    public PaymentTransaction PaymentTransactionDataOnDemand.getRandomPaymentTransaction() {
        init();
        PaymentTransaction obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return PaymentTransaction.findPaymentTransaction(id);
    }
    
    public boolean PaymentTransactionDataOnDemand.modifyPaymentTransaction(PaymentTransaction obj) {
        return false;
    }
    
    public void PaymentTransactionDataOnDemand.init() {
        int from = 0;
        int to = 10;
        data = PaymentTransaction.findPaymentTransactionEntries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'PaymentTransaction' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<PaymentTransaction>();
        for (int i = 0; i < 10; i++) {
            PaymentTransaction obj = getNewTransientPaymentTransaction(i);
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
