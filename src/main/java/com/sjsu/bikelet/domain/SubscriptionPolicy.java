package com.sjsu.bikelet.domain;

import java.util.List;

import javax.persistence.ManyToOne;
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

    @ManyToOne
    private Program programId;
    
//    public static List<SubscriptionPolicy> SubscriptionPolicy.findAllProgramsByTenant(Long programId) {
//        return entityManager().createQuery("SELECT o FROM SubscriptionPolicy o where o.programId.id = :programId", SubscriptionPolicy.class).setParameter("programId", programId).getResultList();
//    }
}
