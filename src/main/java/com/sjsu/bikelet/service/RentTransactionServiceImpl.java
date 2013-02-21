package com.sjsu.bikelet.service;

import java.util.List;

import com.sjsu.bikelet.domain.RentTransaction;


public class RentTransactionServiceImpl implements RentTransactionService {

	@Override
	public RentTransaction findRentTransactionForCheckin(Long userId,
			String status) {
		// TODO Auto-generated method stub
		return RentTransaction.findRentTransactionForCheckin(userId, status);
	}
	
	@Override
	public List<RentTransaction> findRentTransactionsByUser(int firstResult,
			int maxResults, Long userId) {
		// TODO Auto-generated method stub
		return RentTransaction.findRentTransactionsByUser(firstResult, maxResults, userId);
	}

	@Override
	public List<RentTransaction> findAllRentTransactionsByUser(Long userId) {
		// TODO Auto-generated method stub
		return RentTransaction.findAllRentTransactionsByUser(userId);
	}
}
