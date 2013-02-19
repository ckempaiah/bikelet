package com.sjsu.bikelet.service;

import java.util.List;

import org.springframework.roo.addon.layers.service.RooService;

import com.sjsu.bikelet.domain.Program;
import com.sjsu.bikelet.domain.SubscriptionPolicy;

@RooService(domainTypes = { com.sjsu.bikelet.domain.SubscriptionPolicy.class })
public interface SubscriptionPolicyService {

	public abstract List<SubscriptionPolicy> findAllSubscriptionPolicysByProgram(Long programId);
	public abstract long countSubscriptionPolicysByProgram(Long programId);
	public abstract List<SubscriptionPolicy> findSubscriptionPolicyEntriesByProgram(Long programId, int firstResult, int maxResults);

}
