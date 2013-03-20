package com.sjsu.bikelet.service;

import java.util.List;

import com.sjsu.bikelet.model.RentTransactionStatusEnum;
import org.springframework.roo.addon.layers.service.RooService;

import com.sjsu.bikelet.domain.RentTransaction;

@RooService(domainTypes = { com.sjsu.bikelet.domain.RentTransaction.class })
public interface RentTransactionService {
	
	public abstract RentTransaction findRentTransactionForCheckin(Long userId, String status);
	public abstract List<RentTransaction> findRentTransactionsByUser(int firstResult, int maxResults, Long userId);
	public abstract List<RentTransaction> findAllRentTransactionsByUser(Long userId);
	public abstract long countAllRentTransactionsForUser(Long userId);
    List<RentTransaction> findTransactionsForToday(Long userId, RentTransactionStatusEnum statusEnum);
    public abstract RentTransaction findLastTransactionsByUser(Long userId);
}
