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

privileged aspect Permission_Roo_Jpa_Entity {
    
    declare @type: Permission: @Entity;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long Permission.id;
    
    @Version
    @Column(name = "version")
    private Integer Permission.version;
    
    public Long Permission.getId() {
        return this.id;
    }
    
    public void Permission.setId(Long id) {
        this.id = id;
    }
    
    public Integer Permission.getVersion() {
        return this.version;
    }
    
    public void Permission.setVersion(Integer version) {
        this.version = version;
    }
    
}
