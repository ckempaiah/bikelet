package com.sjsu.bikelet.service;

import com.sjsu.bikelet.domain.UserSubscriptionPolicy;


public class UserSubscriptionPolicyServiceImpl implements UserSubscriptionPolicyService {
	
	public UserSubscriptionPolicy findUserSubscriptionPolicyByUser(Long userId) {
		return UserSubscriptionPolicy.findUserSubscriptionPolicyByUser(userId);
	}
}
