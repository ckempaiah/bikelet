package com.sjsu.bikelet.service;


import com.sjsu.bikelet.domain.BillTransaction;
import com.sjsu.bikelet.domain.RentTransaction;
import com.sjsu.bikelet.domain.SubscriptionRate;
import com.sjsu.bikelet.model.BillTransactionTypeEnum;
import com.sjsu.bikelet.model.RentTransactionStatusEnum;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.omg.IOP.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.List;

public class BillTransactionServiceImpl implements BillTransactionService {
    Log log = LogFactory.getLog(BillTransactionServiceImpl.class);
    @Autowired
    private SubscriptionPolicyService subscriptionPolicyService;
    @Autowired
    private SubscriptionRateService subscriptionRateService;

    @Autowired
    RentTransactionService rentTransactionService;

    @Override
    public void createBillTransactionForRentTransaction(RentTransaction rentTransaction) {
        double transactionCost = 0.0;
        BillTransaction billTransaction = new BillTransaction();
        Integer minutesToCharge= null;
        Long duration;
        Integer transactionMinutes;
        Integer minutesUsedPerDay;
        Integer minutesToChargePerDay;

        billTransaction.setReferenceEntityId(rentTransaction.getId());
        billTransaction.setEndDate(rentTransaction.getRentEndDate());
        billTransaction.setStartDate(rentTransaction.getRentStartDate());
        Long rateId = new Long(rentTransaction.getRateId());
        SubscriptionRate subscriptionRate = subscriptionRateService.findSubscriptionRate(rateId);
        subscriptionRate.getFreeMinsPerDay();

        //calculation of used minutes
        duration = (rentTransaction.getRentEndDate().getTime()-rentTransaction.getRentStartDate().getTime());
        transactionMinutes = new Integer((int)(duration/DateUtils.MILLIS_PER_MINUTE));
        log.debug("Minutes Used in the current transaction: " + transactionMinutes);
        minutesUsedPerDay = getTotalTimeUsedForTheDay(rentTransaction.getUserId().getId());
        log.debug("Overall Minutes Used for current day:" + minutesUsedPerDay);
        minutesToChargePerDay = minutesUsedPerDay-subscriptionRate.getFreeMinsPerDay();
        log.debug("Minutes that will be charged for current day:" + minutesToChargePerDay);
        if (minutesToChargePerDay > 0 ){
            if (minutesToChargePerDay < transactionMinutes){
                minutesToCharge = minutesToChargePerDay;
                transactionCost = minutesToChargePerDay*subscriptionRate.getExcessChargePerMin();

            } else {
                minutesToCharge = transactionMinutes;
                transactionCost = transactionMinutes*subscriptionRate.getExcessChargePerMin();
            }
        }
        log.debug("Charges for this transaction:" + transactionCost);
        String billMessage = String.format("Rent Transaction Information \n Minutes Used=%s, Minutes Charged=%s, Charge Per Minute=%s, Total Cost=%s,"
                , transactionMinutes, minutesToCharge, subscriptionRate.getExcessChargePerMin(), transactionCost);
        billMessage = StringUtils.abbreviate(billMessage,99);
        billTransaction.setDescription(billMessage);
        billTransaction.setTransactionType(BillTransactionTypeEnum.RentalCharges.name());
        billTransaction.setTotalCost(transactionCost);
        billTransaction.persist();
    }

    private Integer getTotalTimeUsedForTheDay(Long userId){
        List<RentTransaction> rentTransactionList = rentTransactionService.findTransactionsForToday(userId, RentTransactionStatusEnum.Complete);
        long total=0;
        if (!CollectionUtils.isEmpty(rentTransactionList)){
            for (RentTransaction rentTransaction: rentTransactionList){
                total+=rentTransaction.getRentEndDate().getTime()-rentTransaction.getRentStartDate().getTime();
            }
        }
        return new Integer((int)(total/DateUtils.MILLIS_PER_MINUTE));
    }

}
