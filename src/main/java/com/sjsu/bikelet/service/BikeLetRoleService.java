package com.sjsu.bikelet.service;

import org.springframework.roo.addon.layers.service.RooService;
import com.sjsu.bikelet.domain.BikeLetRole;

@RooService(domainTypes = { com.sjsu.bikelet.domain.BikeLetRole.class })
public interface BikeLetRoleService {
	
	public abstract BikeLetRole findBikeLetRoleByName(String roleName);    
    
}
