package com.sjsu.bikelet.service;

import java.util.List;

import org.springframework.roo.addon.layers.service.RooService;

import com.sjsu.bikelet.domain.RentTransaction;

@RooService(domainTypes = { com.sjsu.bikelet.domain.RentTransaction.class })
public interface RentTransactionService {
	
	public abstract RentTransaction findRentTransactionForCheckin(Long userId, String status);
	public abstract List<RentTransaction> findRentTransactionsByUser(int firstResult, int maxResults, Long userId);
	public abstract List<RentTransaction> findAllRentTransactionsByUser(Long userId);    
}
