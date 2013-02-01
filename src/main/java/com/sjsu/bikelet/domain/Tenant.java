package com.sjsu.bikelet.domain;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooEntity
public class Tenant {

    @NotNull
    @Size(max = 30)
    private String tenantName;

    private Integer contactId;

    private Integer tenantId;

    @ManyToOne
    private Bike bikes;

    @ManyToOne
    private Organization organizations;

    @ManyToOne
    private Program programs;

    @ManyToOne
    private BikeLetUser users;

    @ManyToOne
    private TenantLicensePolicy tenLicPolicy;

    @ManyToOne
    private Station stations;
}
