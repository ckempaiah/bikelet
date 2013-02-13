package com.sjsu.bikelet.service;

import java.util.List;

import com.sjsu.bikelet.domain.Program;
import com.sjsu.bikelet.domain.SubscriptionPolicy;


public class SubscriptionPolicyServiceImpl implements SubscriptionPolicyService {
	
//	public List<SubscriptionPolicy> SubscriptionPolicyServiceImpl.findAllPoliciesByProgram(Long programId) {
//        return SubscriptionPolicy.findAllPoliciesByProgram(programId);
//    }
	@Override
	public List<SubscriptionPolicy> findAllPoliciesByProgram(Long programId) {
		// TODO Auto-generated method stub
		return SubscriptionPolicy.findAllPoliciesByProgram(programId);
	}
    
}
