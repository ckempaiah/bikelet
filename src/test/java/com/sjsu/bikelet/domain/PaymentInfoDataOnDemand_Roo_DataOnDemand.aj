// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.sjsu.bikelet.domain;

import com.sjsu.bikelet.domain.BikeLetUserDataOnDemand;
import com.sjsu.bikelet.domain.PaymentInfo;
import com.sjsu.bikelet.domain.PaymentInfoDataOnDemand;
import com.sjsu.bikelet.service.PaymentInfoService;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

privileged aspect PaymentInfoDataOnDemand_Roo_DataOnDemand {
    
    declare @type: PaymentInfoDataOnDemand: @Component;
    
    private Random PaymentInfoDataOnDemand.rnd = new SecureRandom();
    
    private List<PaymentInfo> PaymentInfoDataOnDemand.data;
    
    @Autowired
    BikeLetUserDataOnDemand PaymentInfoDataOnDemand.bikeLetUserDataOnDemand;
    
    @Autowired
    PaymentInfoService PaymentInfoDataOnDemand.paymentInfoService;
    
    public PaymentInfo PaymentInfoDataOnDemand.getNewTransientPaymentInfo(int index) {
        PaymentInfo obj = new PaymentInfo();
        setCardNumber(obj, index);
        setCardUserName(obj, index);

        return obj;
    }
    
    public void PaymentInfoDataOnDemand.setCardNumber(PaymentInfo obj, int index) {
        String cardNumber = "cardNumber_" + index;
        if (cardNumber.length() > 19) {
            cardNumber = cardNumber.substring(0, 19);
        }
        obj.setCardNumber(cardNumber);
    }
    
    public void PaymentInfoDataOnDemand.setCardUserName(PaymentInfo obj, int index) {
        String cardUserName = "cardUserName_" + index;
        obj.setCardUserName(cardUserName);
    }
    

    public PaymentInfo PaymentInfoDataOnDemand.getSpecificPaymentInfo(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        PaymentInfo obj = data.get(index);
        Long id = obj.getId();
        return paymentInfoService.findPaymentInfo(id);
    }
    
    public PaymentInfo PaymentInfoDataOnDemand.getRandomPaymentInfo() {
        init();
        PaymentInfo obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return paymentInfoService.findPaymentInfo(id);
    }
    
    public boolean PaymentInfoDataOnDemand.modifyPaymentInfo(PaymentInfo obj) {
        return false;
    }
    
    public void PaymentInfoDataOnDemand.init() {
        int from = 0;
        int to = 10;
        data = paymentInfoService.findPaymentInfoEntries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'PaymentInfo' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<PaymentInfo>();
        for (int i = 0; i < 10; i++) {
            PaymentInfo obj = getNewTransientPaymentInfo(i);
            try {
                paymentInfoService.savePaymentInfo(obj);
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
