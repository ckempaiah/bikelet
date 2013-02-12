package com.sjsu.bikelet.domain;

import com.sjsu.bikelet.model.LicenseTypeEnum;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class LicensePolicy {

    @Size(max = 100)
    @NotNull
    private String licenseName;

    @NotNull
    private Double licenseCostPeruser;

    @NotNull
    private Double licenseBaseCost;

    private Integer freeTrialPeriodInDays;

    @Enumerated(EnumType.STRING)
    @Column(name = "license_type")
    private LicenseTypeEnum licenseType;
}
