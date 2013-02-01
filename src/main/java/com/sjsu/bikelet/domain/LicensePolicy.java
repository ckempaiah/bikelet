package com.sjsu.bikelet.domain;

import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;
import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooEntity
public class LicensePolicy {

    private Integer licenseId;

    @Size(max = 100)
    private String licenseName;

    private Double licenseCostPerUser;

    private Integer licenseType;

    private Double licenseBaseCost;

    private Integer freeTrialPeriodInDays;

    @ManyToOne
    private TenantLicensePolicy tenLicPolicy;
}
