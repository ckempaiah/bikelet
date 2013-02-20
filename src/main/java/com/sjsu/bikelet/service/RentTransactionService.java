package com.sjsu.bikelet.service;

import org.springframework.roo.addon.layers.service.RooService;

import com.sjsu.bikelet.domain.RentTransaction;

@RooService(domainTypes = { com.sjsu.bikelet.domain.RentTransaction.class })
public interface RentTransactionService {
	
	public abstract RentTransaction findRentTransactionForCheckin(Long userId, String status);
}
