// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.sjsu.bikelet.domain;

import com.sjsu.bikelet.domain.Address;
import com.sjsu.bikelet.domain.AddressDataOnDemand;
import com.sjsu.bikelet.domain.BikeLetUserDataOnDemand;
import com.sjsu.bikelet.service.AddressService;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

privileged aspect AddressDataOnDemand_Roo_DataOnDemand {
    
    declare @type: AddressDataOnDemand: @Component;
    
    private Random AddressDataOnDemand.rnd = new SecureRandom();
    
    private List<Address> AddressDataOnDemand.data;
    
    @Autowired
    BikeLetUserDataOnDemand AddressDataOnDemand.bikeLetUserDataOnDemand;
    
    @Autowired
    AddressService AddressDataOnDemand.addressService;
    
    public Address AddressDataOnDemand.getNewTransientAddress(int index) {
        Address obj = new Address();
        setAddressLine1(obj, index);
        setAddressLine2(obj, index);
        setAddressState(obj, index);
        setAddressType(obj, index);
        setCellphone(obj, index);
        setCity(obj, index);
        setCountry(obj, index);
        setWorkphone(obj, index);
        setZipCode(obj, index);
        return obj;
    }
    
    public void AddressDataOnDemand.setAddressLine1(Address obj, int index) {
        String addressLine1 = "addressLine1_" + index;
        if (addressLine1.length() > 100) {
            addressLine1 = addressLine1.substring(0, 100);
        }
        obj.setAddressLine1(addressLine1);
    }
    
    public void AddressDataOnDemand.setAddressLine2(Address obj, int index) {
        String addressLine2 = "addressLine2_" + index;
        if (addressLine2.length() > 100) {
            addressLine2 = addressLine2.substring(0, 100);
        }
        obj.setAddressLine2(addressLine2);
    }
    
    public void AddressDataOnDemand.setAddressState(Address obj, int index) {
        String addressState = "addressState_" + index;
        obj.setAddressState(addressState);
    }
    
    public void AddressDataOnDemand.setAddressType(Address obj, int index) {
        String addressType = "addressType_" + index;
        if (addressType.length() > 15) {
            addressType = addressType.substring(0, 15);
        }
        obj.setAddressType(addressType);
    }
    
    public void AddressDataOnDemand.setCellphone(Address obj, int index) {
        String cellphone = "cellphone_" + index;
        if (cellphone.length() > 15) {
            cellphone = cellphone.substring(0, 15);
        }
        obj.setCellphone(cellphone);
    }
    
    public void AddressDataOnDemand.setCity(Address obj, int index) {
        String city = "city_" + index;
        obj.setCity(city);
    }
    
    public void AddressDataOnDemand.setCountry(Address obj, int index) {
        String country = "country_" + index;
        obj.setCountry(country);
    }
    
    public void AddressDataOnDemand.setWorkphone(Address obj, int index) {
        String workphone = "workphone_" + index;
        if (workphone.length() > 15) {
            workphone = workphone.substring(0, 15);
        }
        obj.setWorkphone(workphone);
    }
    
    public void AddressDataOnDemand.setZipCode(Address obj, int index) {
        String zipCode = "zip_" + index;
        if (zipCode.length() > 5) {
            zipCode = zipCode.substring(0, 5);
        }
        obj.setZipCode(zipCode);
    }
    
    public Address AddressDataOnDemand.getSpecificAddress(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        Address obj = data.get(index);
        Long id = obj.getId();
        return addressService.findAddress(id);
    }
    
    public Address AddressDataOnDemand.getRandomAddress() {
        init();
        Address obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return addressService.findAddress(id);
    }
    
    public boolean AddressDataOnDemand.modifyAddress(Address obj) {
        return false;
    }
    
    public void AddressDataOnDemand.init() {
        int from = 0;
        int to = 10;
        data = addressService.findAddressEntries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'Address' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<Address>();
        for (int i = 0; i < 10; i++) {
            Address obj = getNewTransientAddress(i);
            try {
                addressService.saveAddress(obj);
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
