package com.sjsu.bikelet.service;

import java.util.List;

import org.springframework.roo.addon.layers.service.RooService;

import com.sjsu.bikelet.domain.SubscriptionPolicy;
import com.sjsu.bikelet.domain.SubscriptionRate;

@RooService(domainTypes = { com.sjsu.bikelet.domain.SubscriptionRate.class })
public interface SubscriptionRateService {
	
	public abstract List<SubscriptionRate> findAllSubscriptionRatesByPolicy(Long policyId);
	public abstract long countSubscriptionRatesByPolicy(Long policyId);
	public abstract List<SubscriptionRate> findSubscriptionRateEntriesByPolicy(Long policyId, int firstResult, int maxResults);
	public abstract boolean checkOtherPolicyRates(Long policyId,SubscriptionRate subscriptionRate);
	public abstract boolean isValidSubscriptionPolicy(Long policyId);
	public abstract Integer getActiveRateIdForPolicy(Long policyId);
}
