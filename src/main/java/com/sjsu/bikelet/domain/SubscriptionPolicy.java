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
    public static long countSubscriptionPolicysByProgram(Long programId) {
        return entityManager().createQuery("SELECT COUNT(o) FROM SubscriptionPolicy o where o.programId.id = :programId", Long.class).setParameter("programId", programId).getSingleResult();
    }
    
    public static List<SubscriptionPolicy> findSubscriptionPolicyEntriesByProgram(Long programId, int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM SubscriptionPolicy o where o.programId.id = :programId", SubscriptionPolicy.class).setParameter("programId", programId).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    public static List<SubscriptionPolicy> findAllSubscriptionPolicysByProgram(Long programId){
    	return entityManager().createQuery("SELECT o FROM SubscriptionPolicy o where o.programId.id = :programId", SubscriptionPolicy.class).setParameter("programId", programId).getResultList();
    }
    
    public static List<SubscriptionPolicy> findActiveSubscriptionPolicysByProgram(Long programId){
    	return entityManager().createQuery("SELECT o FROM SubscriptionPolicy o where o.programId.id = :programId and EXISTS ( SELECT 1 from SubscriptionRate r where r.policyStartDate < NOW() and r.policyEndDate > NOW() and r.policyId.id = o.id)", SubscriptionPolicy.class).setParameter("programId", programId).getResultList();
    }
}
