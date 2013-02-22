package com.sjsu.bikelet.service;

import com.sjsu.bikelet.domain.Address;


public class AddressServiceImpl implements AddressService {
	
	 public Address findAddressByUser(Long userId) {
		 return Address.findAddressByUser(userId);
	 }
}
