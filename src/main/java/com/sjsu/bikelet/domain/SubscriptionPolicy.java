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
public class SubscriptionPolicy {

    private Integer policyId;

    @NotNull
    @Size(min = 2)
    private String policyName;

    @Size(max = 250)
    private String policyDescription;

    @ManyToOne
    private UserSubscriptionPolicy usp;

    @ManyToOne
    private SubscriptionRate subscpRates;
}
