package com.sjsu.bikelet.service;

import com.sjsu.bikelet.domain.BikeLetUser;
import com.sjsu.bikelet.domain.BillTransaction;
import com.sjsu.bikelet.domain.RentTransaction;
import org.springframework.roo.addon.layers.service.RooService;

import java.util.Date;
import java.util.List;

@RooService(domainTypes = { com.sjsu.bikelet.domain.BillTransaction.class })
public interface BillTransactionService {

    public void createBillTransactionForRentTransaction(RentTransaction rentTransaction);

    List<BillTransaction> findBillTransactionEntriesByUserId(BikeLetUser bikeLetUser, String roleName, int firstResult, int maxResults);

    long countAllBillTransactionsByUserId(BikeLetUser bikeLetUser);

    List<BillTransaction> findAllBillTransactionsByUserId(BikeLetUser bikeLetUser, String roleName);

    List<BillTransaction> findPendingBillTransactions(Long userId, Date startDate, Date endDate);
    
    public BillTransaction findLastBillTransactionByUserId(BikeLetUser userid);
}
