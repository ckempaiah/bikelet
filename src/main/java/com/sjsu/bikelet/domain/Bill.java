package com.sjsu.bikelet.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class Bill {

    @Column(name = "total_charges")
    private Double totalcharges;

    @Size(max = 100)
    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date billStartDate;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date billEndDate;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    @NotNull
    private BikeLetUser userId;
    
    @Transient
    private String user;

    public String getUser() {
    	return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

    public BikeLetUser getUserId() {
        return this.userId;
    }

    public void setUserId(BikeLetUser userId) {
        this.userId = userId;
    }

	public static List<Bill> findBillEntriesByUser(int firstResult, int maxResults, Long userId) {
        return entityManager().createQuery("SELECT o FROM Bill o where o.userId.id = :userId", Bill.class).setParameter("userId", userId).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    public static List<Bill> findAllBillsByUser(Long userId) {
        return entityManager().createQuery("SELECT o FROM Bill o where o.userId.id = :userId", Bill.class).setParameter("userId", userId).getResultList();
    }
    
    public static long countBillsByUser(Long userId) {
        return entityManager().createQuery("SELECT COUNT(o) FROM Bill o where o.userId.id = :userId", Long.class).setParameter("userId", userId).getSingleResult();
    }
    
    public static List<Bill> findAllBillsByTenant(Long tenantId) {
        return entityManager().createQuery("SELECT o FROM Bill o where o.userId.id IN (SELECT bu.id FROM BikeLetUser bu where bu.tenantId.id = :tenantId)", Bill.class).setParameter("tenantId", tenantId).getResultList();
    }
    
    public static List<Bill> findBillEntriesByTenant(int firstResult, int maxResults, Long tenantId) {
        return entityManager().createQuery("SELECT o FROM Bill o where o.userId.id IN (SELECT bu.id FROM BikeLetUser bu where bu.tenantId.id = :tenantId)", Bill.class).setParameter("tenantId", tenantId).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    public static long countBillsByTenant(Long tenantId) {
        return entityManager().createQuery("SELECT COUNT(o) FROM Bill o where o.userId.id IN (SELECT bu.id FROM BikeLetUser bu where bu.tenantId.id = :tenantId)", Long.class).setParameter("tenantId", tenantId).getSingleResult();
    }

    public static Bill findLatestBill(Long userId){
    	List<Bill> bills = entityManager().createQuery("SELECT o FROM Bill o where o.userId.id = :userId ORDER BY o.id DESC limit 1", Bill.class).setParameter("userId", userId).getResultList();
    	if(bills.size() > 0){
    		return bills.get(0);
    	}
    	else{
    		return null;
    	}

    }

}
