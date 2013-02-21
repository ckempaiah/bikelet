package com.sjsu.bikelet.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class RentTransaction {

    private Integer fromStationId;

    private Long toStationId;

    private Long tenantId;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date rentStartDate;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date rentEndDate;

    private Integer rateId;

    @NotNull
    @Size(max = 10)
    private String status;

    @NotNull
    @Size(max = 500)
    private String comments;

    @ManyToOne
    private BikeLetUser userId;

    @ManyToOne
    private Bike bikeId;
    
    private String accessKey;
    
    public String getAccessKey() {
		return accessKey;
	}

	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
	}
    
    public static RentTransaction findRentTransactionForCheckin(Long userId, String status)
    {
    	List<RentTransaction> transactions = new ArrayList<RentTransaction>();
    	transactions = entityManager().createQuery("SELECT o FROM RentTransaction o where o.userId.id = :userId and o.status = :status", RentTransaction.class).
    						   setParameter("userId", userId).
    						   setParameter("status", status).
    						   getResultList();
    	if(transactions.isEmpty())
    		return null;
    	else
    		return transactions.get(0);
    }
    
    public static List<RentTransaction> findRentTransactionsByUser(int firstResult, int maxResults, Long userId)
    {
        return entityManager().createQuery("SELECT o FROM RentTransaction o where o.userId.id = :userId", RentTransaction.class)
        					  .setParameter("userId", userId)
        					  .setFirstResult(firstResult)
        					  .setMaxResults(maxResults)
        					  .getResultList();    	
    }
    
    public static List<RentTransaction> findAllRentTransactionsByUser(Long userId){
    	return entityManager().createQuery("SELECT o FROM RentTransaction o where o.userId.id = :userId", RentTransaction.class)
    						  .setParameter("userId",userId)
    						  .getResultList();
    }
    
}
