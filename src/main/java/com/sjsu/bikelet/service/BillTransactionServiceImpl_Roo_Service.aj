// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.sjsu.bikelet.service;

import com.sjsu.bikelet.domain.BillTransaction;
import com.sjsu.bikelet.service.BillTransactionServiceImpl;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

privileged aspect BillTransactionServiceImpl_Roo_Service {
    
    declare @type: BillTransactionServiceImpl: @Service;
    
    declare @type: BillTransactionServiceImpl: @Transactional;
    
    public long BillTransactionServiceImpl.countAllBillTransactions() {
        return BillTransaction.countBillTransactions();
    }
    
    public void BillTransactionServiceImpl.deleteBillTransaction(BillTransaction billTransaction) {
        billTransaction.remove();
    }
    
    public BillTransaction BillTransactionServiceImpl.findBillTransaction(Long id) {
        return BillTransaction.findBillTransaction(id);
    }
    
    public List<BillTransaction> BillTransactionServiceImpl.findAllBillTransactions() {
        return BillTransaction.findAllBillTransactions();
    }
    

    public void BillTransactionServiceImpl.saveBillTransaction(BillTransaction billTransaction) {
        billTransaction.persist();
    }
    
    public BillTransaction BillTransactionServiceImpl.updateBillTransaction(BillTransaction billTransaction) {
        return billTransaction.merge();
    }
    
}
