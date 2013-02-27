package com.sjsu.bikelet.service;

import java.util.List;

import com.sjsu.bikelet.domain.Program;
import com.sjsu.bikelet.domain.SubscriptionPolicy;


public class SubscriptionPolicyServiceImpl implements SubscriptionPolicyService {

	@Override
	public List<SubscriptionPolicy> findAllSubscriptionPolicysByProgram(
			Long programId) {
		// TODO Auto-generated method stub
		return SubscriptionPolicy.findAllSubscriptionPolicysByProgram(programId);
		
	}

	@Override
	public long countSubscriptionPolicysByProgram(Long programId) {
		// TODO Auto-generated method stub
		return SubscriptionPolicy.countSubscriptionPolicysByProgram(programId);
	}

	@Override
	public List<SubscriptionPolicy> findSubscriptionPolicyEntriesByProgram(
			Long programId, int firstResult, int maxResults) {
		// TODO Auto-generated method stub
		return SubscriptionPolicy.findSubscriptionPolicyEntriesByProgram(programId,firstResult,maxResults);
	}
	
	@Override
	public List<SubscriptionPolicy> findActiveSubscriptionPolicysByProgram(
			Long programId) {
		// TODO Auto-generated method stub
		return SubscriptionPolicy.findActiveSubscriptionPolicysByProgram(programId);
		
	}
	
//	public List<SubscriptionPolicy> SubscriptionPolicyServiceImpl.findAllPoliciesByProgram(Long programId) {
//        return SubscriptionPolicy.findAllPoliciesByProgram(programId);
//    }
//	@Override
//	public List<SubscriptionPolicy> findAllPoliciesByProgram(Long programId) {
//		// TODO Auto-generated method stub
//		return SubscriptionPolicy.findAllPoliciesByProgram(programId);
	}
    

