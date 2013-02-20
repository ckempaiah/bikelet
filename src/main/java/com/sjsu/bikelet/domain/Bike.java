package com.sjsu.bikelet.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

import com.sjsu.bikelet.model.BikeStatusEnum;


@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class Bike {

    private Integer bikeHeight;

    @Size(max = 10)
    private String bikeColor;

    @NotNull
    @Size(min = 2, max = 20)
    private String bikeType;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date lastServiceDate;

    @Size(max = 10)
    private String wheelSize;

    @Size(max = 10)
    private String bikeStatus;

    @ManyToOne
    private Tenant tenantId;
    
    public static long countBikesByStation(Long stationId) {
        return entityManager().createQuery("SELECT COUNT(o) FROM BikeLocation o where o.stationId.id = :stationId and o.bikeStatus = :bikeStatus", Long.class).setParameter("stationId", stationId).setParameter("bikeStatus", BikeStatusEnum.Available.toString()).getSingleResult();
    }
    
    public static List<Bike> findAllBikesByStation(Long stationId) {
        return entityManager().createQuery("SELECT o.bikeId FROM BikeLocation o where o.stationId.id = :stationId and o.bikeStatus = :bikeStatus", Bike.class).setParameter("stationId", stationId).setParameter("bikeStatus", BikeStatusEnum.Available.toString()).getResultList();
    }
    
    public static List<Bike> findBikeEntriesByStation(Long stationId, int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o.bikeId FROM BikeLocation o where o.stationId.id = :stationId and o.bikeStatus = :bikeStatus", Bike.class).setParameter("stationId", stationId).setParameter("bikeStatus", BikeStatusEnum.Available.toString()).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

    public static List<Bike> findAvailableBikesByStation(Long stationId)
    {
    	String status = "Available";
    	if(stationId==null)
    		return findAllBikes();
    	return entityManager().createQuery("SELECT o FROM Bike o WHERE o.id IN (SELECT bl.bikeId.id FROM BikeLocation bl WHERE bl.stationId.id = :stId and bl.bikeStatus = :status)", Bike.class)
    						  .setParameter("stId", stationId)
    						  .setParameter("status", status)
    						  .getResultList();
    }

}
