package com.sjsu.bikelet.service;

import com.sjsu.bikelet.domain.RentTransaction;
import org.springframework.roo.addon.layers.service.RooService;

@RooService(domainTypes = { com.sjsu.bikelet.domain.BillTransaction.class })
public interface BillTransactionService {

    public void createBillTransactionForRentTransaction(RentTransaction rentTransaction);
}
