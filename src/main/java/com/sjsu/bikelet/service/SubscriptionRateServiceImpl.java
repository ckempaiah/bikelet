package com.sjsu.bikelet.service;

import java.util.List;

import com.sjsu.bikelet.domain.SubscriptionPolicy;
import com.sjsu.bikelet.domain.SubscriptionRate;


public class SubscriptionRateServiceImpl implements SubscriptionRateService {

	@Override
	public List<SubscriptionRate> findAllSubscriptionRatesByPolicy(Long policyId) {
		// TODO Auto-generated method stub
		return SubscriptionRate.findAllSubscriptionRatesByPolicy(policyId);
	}

	@Override
	public long countSubscriptionRatesByPolicy(Long policyId) {
		// TODO Auto-generated method stub
		return SubscriptionRate.countSubscriptionRatesByPolicy(policyId);
	}

	@Override
	public List<SubscriptionRate> findSubscriptionRateEntriesByPolicy(
			Long policyId, int firstResult, int maxResults) {
		// TODO Auto-generated method stub
		return SubscriptionRate.findSubscriptionRateEntriesByPolicy(policyId,firstResult,maxResults);
	}
	
	@Override
	public boolean isValidSubscriptionPolicy(Long policyId) {
		// TODO Auto-generated method stub
		return SubscriptionRate.isValidSubscription(policyId);
	}
}
