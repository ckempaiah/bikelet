// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.sjsu.bikelet.domain;

import java.lang.Integer;
import java.lang.Long;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

privileged aspect Bike_Roo_Jpa_Entity {
    
    declare @type: Bike: @Entity;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long Bike.id;
    
    @Version
    @Column(name = "version")
    private Integer Bike.version;
    
    public Long Bike.getId() {
        return this.id;
    }
    
    public void Bike.setId(Long id) {
        this.id = id;
    }
    
    public Integer Bike.getVersion() {
        return this.version;
    }
    
    public void Bike.setVersion(Integer version) {
        this.version = version;
    }
    
}
