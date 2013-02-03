package com.sjsu.bikelet.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class SubscriptionPolicy {

    @NotNull
    @Size(min = 2)
    private String policyName;

    @Size(max = 250)
    private String policyDescription;
}
