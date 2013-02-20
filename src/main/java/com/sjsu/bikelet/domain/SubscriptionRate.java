package com.sjsu.bikelet.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class SubscriptionRate {

    private Double membershipPerMonth;

    private Double organizationShare;

    private Double userShare;

    private Integer freeMinsPerDay;

    private Double excessChargePerMin;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date policyStartDate;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date policyEndDate;

    @ManyToOne
    private SubscriptionPolicy policyId;
    
    public static long countSubscriptionRatesByPolicy(Long policyId) {
        return entityManager().createQuery("SELECT COUNT(o) FROM SubscriptionRate o where o.policyId.id = :policyId", Long.class).setParameter("policyId", policyId).getSingleResult();
    }
    
    public static List<SubscriptionRate> findSubscriptionRateEntriesByPolicy(Long policyId, int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM SubscriptionRate o where o.policyId.id = :policyId", SubscriptionRate.class).setParameter("policyId", policyId).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    public static List<SubscriptionRate> findAllSubscriptionRatesByPolicy(Long policyId){
    	return entityManager().createQuery("SELECT o FROM SubscriptionRate o where o.policyId.id = :policyId", SubscriptionRate.class).setParameter("policyId", policyId).getResultList();
    }

	public static boolean checkOtherPolicyRates(Long policyId, SubscriptionRate subscriptionRate) {
		// TODO Auto-generated method stub
		// count the number of rows in subscriptionrate table where policy id
		List<SubscriptionRate> abd = findAllSubscriptionRatesByPolicy(policyId);
		if(!abd.equals(null)){
			for(int i=0; i < abd.size(); i++){
				SubscriptionRate sv = new SubscriptionRate();
				sv = abd.get(i);
//				System.out.println("Policy Rate"+sv.toString());
//				boolean c = ((subscriptionRate.getPolicyStartDate().compareTo(sv.getPolicyEndDate())) <= 0);
//				System.out.println("value of "+c);
				if(((subscriptionRate.getPolicyStartDate().compareTo(sv.getPolicyEndDate())) <= 0)&&((subscriptionRate.getPolicyEndDate().compareTo(sv.getPolicyStartDate())) >= 0)){
					System.out.println("returning true");
					return true;
				}
			}
		}
		System.out.println("returing false");
		return false;
	}
    
    public static boolean isValidSubscription(Long policyId)
    {
    	Long count = entityManager().createQuery("SELECT count(o) from SubscriptionRate o where o.policyStartDate < NOW() and o.policyEndDate > NOW() and policyId.id = :policyId", Long.class).setParameter("policyId", policyId).getSingleResult();
    	if(count>0)
    		return true;
    	else
    		return false;
    }
}
