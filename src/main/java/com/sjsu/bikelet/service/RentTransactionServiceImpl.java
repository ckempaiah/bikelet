package com.sjsu.bikelet.service;

import com.sjsu.bikelet.domain.RentTransaction;


public class RentTransactionServiceImpl implements RentTransactionService {

	@Override
	public RentTransaction findRentTransactionForCheckin(Long userId,
			String status) {
		// TODO Auto-generated method stub
		return RentTransaction.findRentTransactionForCheckin(userId, status);
	}
}
