package com.sjsu.bikelet.service;

import com.sjsu.bikelet.domain.PaymentInfo;


public class PaymentInfoServiceImpl implements PaymentInfoService {
	
	 public PaymentInfo findPaymentInfoByUser(Long userId) {
		 return PaymentInfo.findPaymentInfoByUser(userId);
	 }
}
