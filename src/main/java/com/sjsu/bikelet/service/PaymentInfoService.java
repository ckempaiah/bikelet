package com.sjsu.bikelet.service;

import org.springframework.roo.addon.layers.service.RooService;

import com.sjsu.bikelet.domain.PaymentInfo;

@RooService(domainTypes = { com.sjsu.bikelet.domain.PaymentInfo.class })
public interface PaymentInfoService {
	
	 public abstract PaymentInfo findPaymentInfoByUser(Long userId);    
}
