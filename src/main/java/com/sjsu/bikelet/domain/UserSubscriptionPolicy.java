package com.sjsu.bikelet.domain;

import java.util.List;

import javax.persistence.OneToOne;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class UserSubscriptionPolicy {

    @OneToOne
    private SubscriptionPolicy policyId;

    @OneToOne
    private BikeLetUser userId;
    
    public static UserSubscriptionPolicy findUserSubscriptionPolicyByUser(Long userId) {
        if (userId == null) return null;
        List<UserSubscriptionPolicy>  policy = entityManager().createQuery("SELECT o FROM UserSubscriptionPolicy o where o.userId.id = :userId", UserSubscriptionPolicy.class).setParameter("userId", userId).getResultList();
        if (policy != null && policy.size()>0)
        	return policy.get(0);
        return null;
        
    }
}
