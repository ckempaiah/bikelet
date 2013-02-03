package com.sjsu.bikelet.domain;

import javax.persistence.Column;
import javax.validation.constraints.Size;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class LicensePolicy {

    @Size(max = 100)
    private String licenseName;

    private Double licenseCostPeruser;

    private Integer licenseType;

    private Double licenseBaseCost;

    private Integer freeTrialPeriodInDays;
}
