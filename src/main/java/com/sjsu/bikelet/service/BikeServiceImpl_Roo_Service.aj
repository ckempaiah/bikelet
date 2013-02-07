// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.sjsu.bikelet.service;

import com.sjsu.bikelet.domain.Bike;
import com.sjsu.bikelet.service.BikeServiceImpl;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

privileged aspect BikeServiceImpl_Roo_Service {
    
    declare @type: BikeServiceImpl: @Service;
    
    declare @type: BikeServiceImpl: @Transactional;
    
    public long BikeServiceImpl.countAllBikes() {
        return Bike.countBikes();
    }
    
    public void BikeServiceImpl.deleteBike(Bike bike) {
        bike.remove();
    }
    
    public Bike BikeServiceImpl.findBike(Long id) {
        return Bike.findBike(id);
    }
    
    public List<Bike> BikeServiceImpl.findAllBikes() {
        return Bike.findAllBikes();
    }
    
    public List<Bike> BikeServiceImpl.findBikeEntries(int firstResult, int maxResults) {
        return Bike.findBikeEntries(firstResult, maxResults);
    }
    
    public void BikeServiceImpl.saveBike(Bike bike) {
        bike.persist();
    }
    
    public Bike BikeServiceImpl.updateBike(Bike bike) {
        return bike.merge();
    }
    
}
