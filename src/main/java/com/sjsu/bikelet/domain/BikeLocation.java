package com.sjsu.bikelet.domain;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.Size;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

import com.sjsu.bikelet.model.BikeStatusEnum;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class BikeLocation {

    @Size(max = 10)
    private String bikeStatus;

    @ManyToOne
    private Station stationId;

    @OneToOne
    private Bike bikeId;
    
    public static Long countAvailableBikesByStation(Long stationId) {
        return entityManager().createQuery("SELECT COUNT(o) FROM BikeLocation o where o.stationId.id = :stationId and o.bikeStatus = :status", Long.class)
        					  .setParameter("stationId", stationId)
        					  .setParameter("status", BikeStatusEnum.Available.toString())
        					  .getSingleResult();
    }
    
    public static void updateBikeLocation(Long bikeId, String status, Long stationId)
    {
    	entityManager().createQuery("update BikeLocation o set o.bikeStatus = :status, o.stationId.id = :stationId where o.bikeId.id = :bikeId")
    				   .setParameter("status", status)
    				   .setParameter("stationId", stationId)					  
    				   .setParameter("bikeId", bikeId)
    				   .executeUpdate();
    }
    
    public static BikeLocation findBikeLocationOfBike(Long bikeId)
    {
    	return entityManager().createQuery("SELECT o FROM BikeLocation o where o.bikeId.id = :bikeId",BikeLocation.class)
    				   .setParameter("bikeId",bikeId)
    				   .getSingleResult();
    }
}
