package com.sjsu.bikelet.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

import com.sjsu.bikelet.model.BikeAvailabilityStatusEnum;


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

    @Size(max = 30)
    private String bikeStatus;

    @ManyToOne
    private Tenant tenantId;
    
    @Transient
    private Station station;

    public Station getStation() {
		return station;
	}

	public void setStation(Station station) {
		this.station = station;
	}
	
    @Transient
    private String locationStatus;


	public String getLocationStatus() {
		return locationStatus;
	}

	public void setLocationStatus(String locationStatus) {
		this.locationStatus = locationStatus;
	}

	public static long countBikesByStation(Long stationId) {
        return entityManager().createQuery("SELECT COUNT(o) FROM BikeLocation o where o.stationId.id = :stationId ", Long.class).setParameter("stationId", stationId).getSingleResult();
    }
    
    public static List<Bike> findAllBikesByStation(Long stationId) {
        return entityManager().createQuery("SELECT o.bikeId FROM BikeLocation o where o.stationId.id = :stationId ", Bike.class).setParameter("stationId", stationId).getResultList();
    }
    
    public static List<Bike> findBikeEntriesByStation(Long stationId, int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o.bikeId FROM BikeLocation o where o.stationId.id = :stationId ", Bike.class).setParameter("stationId", stationId).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

    public static long countBikesByTenant(Long tenantId) {
        return entityManager().createQuery("SELECT COUNT(o) FROM Bike o where o.tenantId.id = :tenantId", Long.class).setParameter("tenantId", tenantId).getSingleResult();
    }
    
    public static List<Bike> findAllBikesByTenant(Long tenantId) {
        return entityManager().createQuery("SELECT o FROM Bike o where o.tenantId.id = :tenantId", Bike.class).setParameter("tenantId", tenantId).getResultList();
    }
    
    public static List<Bike> findBikeEntriesByTenant(Long tenantId, int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Bike o where o.tenantId.id = :tenantId", Bike.class).setParameter("tenantId", tenantId).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    public static List<Bike> findAvailableBikesByStation(Long stationId)
    {
    	String status = BikeAvailabilityStatusEnum.Available.toString();
    	if(stationId==null)
    		return findAllBikes();
    	return entityManager().createQuery("SELECT o FROM Bike o WHERE o.id IN (SELECT bl.bikeId.id FROM BikeLocation bl WHERE bl.stationId.id = :stId and bl.bikeStatus = :status)", Bike.class)
    						  .setParameter("stId", stationId)
    						  .setParameter("status", status)
    						  .getResultList();
    }

}
