// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.sjsu.bikelet.domain;

import com.sjsu.bikelet.domain.Bike;
import com.sjsu.bikelet.domain.BikeLocation;
import com.sjsu.bikelet.domain.Station;

privileged aspect BikeLocation_Roo_JavaBean {
    
    public String BikeLocation.getBikeStatus() {
        return this.bikeStatus;
    }
    
    public void BikeLocation.setBikeStatus(String bikeStatus) {
        this.bikeStatus = bikeStatus;
    }
    

    public Station BikeLocation.getStationId() {
        return this.stationId;
    }
    
    public void BikeLocation.setStationId(Station stationId) {
        this.stationId = stationId;
    }
    
    public Bike BikeLocation.getBikeId() {
        return this.bikeId;
    }
    
    public void BikeLocation.setBikeId(Bike bikeId) {
        this.bikeId = bikeId;
    }
    
}
