// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.sjsu.bikelet.domain;

import com.sjsu.bikelet.domain.Station;
import com.sjsu.bikelet.domain.SubscriptionPolicy;
import java.lang.Integer;
import java.lang.String;

privileged aspect Program_Roo_JavaBean {
    
    public Integer Program.getEntityId() {
        return this.entityId;
    }
    
    public void Program.setEntityId(Integer entityId) {
        this.entityId = entityId;
    }
    
    public String Program.getProgramName() {
        return this.programName;
    }
    
    public void Program.setProgramName(String programName) {
        this.programName = programName;
    }
    
    public String Program.getDescription() {
        return this.description;
    }
    
    public void Program.setDescription(String description) {
        this.description = description;
    }
    
    public SubscriptionPolicy Program.getSubscpPolicy() {
        return this.subscpPolicy;
    }
    
    public void Program.setSubscpPolicy(SubscriptionPolicy subscpPolicy) {
        this.subscpPolicy = subscpPolicy;
    }
    
    public Station Program.getStations() {
        return this.stations;
    }
    
    public void Program.setStations(Station stations) {
        this.stations = stations;
    }
    
}
