package com.sjsu.bikelet.service;

import org.springframework.roo.addon.layers.service.RooService;
import com.sjsu.bikelet.domain.UserSubscriptionPolicy;

@RooService(domainTypes = { com.sjsu.bikelet.domain.UserSubscriptionPolicy.class })
public interface UserSubscriptionPolicyService {

	public abstract UserSubscriptionPolicy findUserSubscriptionPolicyByUser(Long userId);      
}
