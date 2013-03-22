package com.sjsu.bikelet.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.sjsu.bikelet.model.PendingBillProjection;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class BillTransaction {

    private Double totalCost;

    @Size(max = 100)
    private String transactionType;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date startDate;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date endDate;

    @Size(max = 100)
    private String description;

    @ManyToOne
    @JoinColumn(name="bill_id")
    private Bill bill;

    private Long referenceEntityId;
    @NotNull
    @ManyToOne
    @JoinColumn(name="tenant_id")
    private Tenant tenantId;

    @ManyToOne
    @JoinColumn(name="user_id")
    @NotNull
    private BikeLetUser bikeLetUserId;

    public static List<BillTransaction> findBillTransactionEntriesByUser(BikeLetUser bikeLetUser, int firstResult, int maxResults) {

        return entityManager().createQuery("SELECT o FROM BillTransaction o where o.bikeLetUserId.id=:userId", BillTransaction.class)
                .setParameter("userId", bikeLetUser.getId())
                .setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

    public static List<BillTransaction> findBillTransactionByTenantId(Long tenantId, int firstResult, int maxResults) {

        return entityManager().createQuery("SELECT o FROM BillTransaction o where o.tenantId.id=:tenantId", BillTransaction.class)
                .setParameter("tenantId", tenantId)
                .setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    public static BillTransaction findLastBillTransactionByUserId(BikeLetUser bikeLetUser) {

    	List<BillTransaction> billTransactions = entityManager().createQuery("SELECT o FROM BillTransaction o where o.bikeLetUserId.id=:userId", BillTransaction.class)
                .setParameter("userId", bikeLetUser.getId()).getResultList();
    	if(billTransactions.size()==0)
    		return null;
    	else
    	{
    		BillTransaction billTrans = billTransactions.get(0);
        	for (BillTransaction bt : billTransactions){
        		if (bt.getEndDate().compareTo(billTrans.getEndDate()) > 0){
        			billTrans = bt;
        		}
        	}
        	return billTrans;
    	}
    	
    }

    public static long countBillTransactionsByUser(BikeLetUser bikeLetUser) {
        return entityManager().createQuery("SELECT COUNT(o) FROM BillTransaction o where o.bikeLetUserId.id=:userId", Long.class)
                .setParameter("userId", bikeLetUser.getId())
                .getSingleResult();
    }

    public static List<BillTransaction> findBillTransactionEntriesByUser(BikeLetUser bikeLetUser) {
        return entityManager().createQuery("SELECT o FROM BillTransaction o where o.bikeLetUserId.id=:userId", BillTransaction.class)
                .setParameter("userId", bikeLetUser.getId())
                .getResultList();
    }

    public static List<BillTransaction> findBillTransactionEntriesByTenantId( Long tenantId) {
        return entityManager().createQuery("SELECT o FROM BillTransaction o where o.tenantId.id=:tenantId", BillTransaction.class)
                .setParameter("tenantId", tenantId)
                .getResultList();
    }

    public static List<PendingBillProjection> findPendingMonths(){
        Query query = entityManager().createNativeQuery(" select month_id, user_id from (\n" +
                "select month(end_date) as month_id, user_id from bill_transaction where bill_id is null and user_id is not null) btm\n" +
                "group by month_id, user_id", PendingBillProjection.class);

        return query.getResultList();
    }

}
