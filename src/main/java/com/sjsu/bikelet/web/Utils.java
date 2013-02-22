package com.sjsu.bikelet.web;

import org.springframework.security.core.context.SecurityContextHolder;

import com.sjsu.bikelet.model.BikeletUserPrinciple;;

public class Utils {
	
	public static Long getLogonTenantId() {
		BikeletUserPrinciple user = getLogonUser();
		return user.getTenantId();
	}
	
	public static Long getLogonUserId() {
		BikeletUserPrinciple user = getLogonUser();
		return user.getUserId();
	}

    public static BikeletUserPrinciple getLogonUser() {
    	BikeletUserPrinciple principal = (BikeletUserPrinciple) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return principal;
    }
}
