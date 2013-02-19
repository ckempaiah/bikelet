package com.sjsu.bikelet.domain;

import javax.persistence.EntityManager;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class Tenant {

    @NotNull
    @Size(max = 30)
    private String tenantName;

    private Integer contactId;

    @Transient
    private BikeLetUser tenantAdmin;

    public BikeLetUser getTenantAdmin() {
        return tenantAdmin;
    }

    public void setTenantAdmin(BikeLetUser tenantAdmin) {
        this.tenantAdmin = tenantAdmin;
    }
}
