package com.sjsu.bikelet.service;

import org.springframework.roo.addon.layers.service.RooService;
import com.sjsu.bikelet.domain.Address;

@RooService(domainTypes = { com.sjsu.bikelet.domain.Address.class })
public interface AddressService {
	
	 public abstract Address findAddressByUser(Long id);    
	    
}
