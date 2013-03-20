package com.sjsu.bikelet.service;


import com.sjsu.bikelet.domain.*;
import com.sjsu.bikelet.model.BikeletRoleName;
import com.sjsu.bikelet.model.BillTransactionTypeEnum;
import com.sjsu.bikelet.model.PendingBillProjection;
import com.sjsu.bikelet.model.RentTransactionStatusEnum;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.omg.IOP.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.Date;
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
        Integer minutesToCharge = null;
        Long duration;
        Integer transactionMinutes;

        Integer minutesToChargePerDay;

        billTransaction.setReferenceEntityId(rentTransaction.getId());
        billTransaction.setEndDate(rentTransaction.getRentEndDate());
        billTransaction.setStartDate(rentTransaction.getRentStartDate());
        Long rateId = new Long(rentTransaction.getRateId());
        SubscriptionRate subscriptionRate = subscriptionRateService.findSubscriptionRate(rateId);
        subscriptionRate.getFreeMinsPerDay();

        //calculation of used minutes
        duration = (rentTransaction.getRentEndDate().getTime() - rentTransaction.getRentStartDate().getTime());
        transactionMinutes = new Integer((int) Math.ceil(duration / DateUtils.MILLIS_PER_MINUTE));
        log.debug("Minutes Used in the current transaction: " + transactionMinutes);

        //log.debug("Overall Minutes Used for current day:" + minutesUsedPerDay);
        minutesToCharge = transactionMinutes - subscriptionRate.getFreeMinsPerDay();
        log.debug("Minutes that will be charged for current day:" + minutesToCharge);
        if (minutesToCharge > 0) {
            transactionCost = minutesToCharge * subscriptionRate.getExcessChargePerMin();

        }
        log.debug("Charges for this transaction:" + transactionCost);
        String billMessage = String.format("Rent Transaction Information \n Minutes Used=%s, Minutes Charged=%s, Charge Per Minute=%s, Total Cost=%s,"
                , transactionMinutes, minutesToCharge, subscriptionRate.getExcessChargePerMin(), transactionCost);
        billMessage = StringUtils.abbreviate(billMessage, 99);
        billTransaction.setDescription(billMessage);
        billTransaction.setTransactionType(BillTransactionTypeEnum.RentalCharges.name());
        billTransaction.setTotalCost(transactionCost);
        billTransaction.setBikeLetUserId(rentTransaction.getUserId());
        Tenant tenant = Tenant.findTenant(rentTransaction.getTenantId());
        billTransaction.setTenantId(tenant);
        billTransaction.persist();
    }
    //(Ck,17FEB2012 to be used if the free minutes should be used per day basis
    private Integer getTotalTimeUsedForTheDay(Long userId) {
        List<RentTransaction> rentTransactionList = rentTransactionService.findTransactionsForToday(userId, RentTransactionStatusEnum.Complete);
        long total = 0;
        if (!CollectionUtils.isEmpty(rentTransactionList)) {
            for (RentTransaction rentTransaction : rentTransactionList) {
                total += rentTransaction.getRentEndDate().getTime() - rentTransaction.getRentStartDate().getTime();
            }
        }
        return new Integer((int) (total / DateUtils.MILLIS_PER_MINUTE));
    }

    public BillTransaction findBillTransactionByMemberId(Long memberId) {
        //TODO: provide implementation
        //BillTransaction.entityManager().createQuery("from BillTransaction bt where bt.")
        return null;
    }

    @Override
    public List<BillTransaction> findBillTransactionEntriesByUserId(BikeLetUser bikeLetUser, String roleName, int firstResult, int maxResults) {
        if (BikeletRoleName.ROLE_TENANT.name().equalsIgnoreCase(roleName)){

            return BillTransaction.findBillTransactionByTenantId(bikeLetUser.getTenantId().getId(), firstResult, maxResults);
        }else if (BikeletRoleName.ROLE_USER.name().equalsIgnoreCase(roleName)){
            return BillTransaction.findBillTransactionEntriesByUser(bikeLetUser, firstResult, maxResults);
        }
        throw new IllegalArgumentException("User role is invalid:" + roleName);
    }

    @Override
    public long countAllBillTransactionsByUserId(BikeLetUser bikeLetUser){
        return BillTransaction.countBillTransactionsByUser(bikeLetUser);
    }

    @Override
    public List<BillTransaction> findAllBillTransactionsByUserId(BikeLetUser bikeLetUser, String roleName){
        if (BikeletRoleName.ROLE_TENANT.name().equalsIgnoreCase(roleName)){
            return BillTransaction.findBillTransactionEntriesByTenantId(bikeLetUser.getTenantId().getId());
        }else if (BikeletRoleName.ROLE_USER.name().equalsIgnoreCase(roleName)){
            return BillTransaction.findBillTransactionEntriesByUser(bikeLetUser);
        }
        throw new IllegalArgumentException("User role is invalid:" + roleName);
    }

    @Override
    public List<BillTransaction> findPendingBillTransactions(Long userId, Date startDate, Date endDate){

        TypedQuery<BillTransaction> query = BillTransaction.entityManager().createQuery("select bt from BillTransaction bt " +
                "where bt.bikeLetUserId.id=:userId and bt.startDate >=:startDate and bt.endDate <=:endDate and bill.id is null", BillTransaction.class);

        query.setParameter("userId", userId);
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);

        return query.getResultList();

    }
}
