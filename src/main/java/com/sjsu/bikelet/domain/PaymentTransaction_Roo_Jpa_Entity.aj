// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.sjsu.bikelet.domain;

import com.sjsu.bikelet.domain.PaymentTransaction;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

privileged aspect PaymentTransaction_Roo_Jpa_Entity {
    
    declare @type: PaymentTransaction: @Entity;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long PaymentTransaction.id;
    
    @Version
    @Column(name = "version")
    private Integer PaymentTransaction.version;
    
    public Long PaymentTransaction.getId() {
        return this.id;
    }
    
    public void PaymentTransaction.setId(Long id) {
        this.id = id;
    }
    
    public Integer PaymentTransaction.getVersion() {
        return this.version;
    }
    
    public void PaymentTransaction.setVersion(Integer version) {
        this.version = version;
    }
    
}
