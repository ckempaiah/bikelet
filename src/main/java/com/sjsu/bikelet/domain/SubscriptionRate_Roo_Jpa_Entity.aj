// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.sjsu.bikelet.domain;

import com.sjsu.bikelet.domain.SubscriptionRate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

privileged aspect SubscriptionRate_Roo_Jpa_Entity {
    
    declare @type: SubscriptionRate: @Entity;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long SubscriptionRate.id;
    
    @Version
    @Column(name = "version")
    private Integer SubscriptionRate.version;
    
    public Long SubscriptionRate.getId() {
        return this.id;
    }
    
    public void SubscriptionRate.setId(Long id) {
        this.id = id;
    }
    
    public Integer SubscriptionRate.getVersion() {
        return this.version;
    }
    
    public void SubscriptionRate.setVersion(Integer version) {
        this.version = version;
    }
    
}
