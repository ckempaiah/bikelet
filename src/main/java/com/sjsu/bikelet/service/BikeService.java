package com.sjsu.bikelet.service;

import org.springframework.roo.addon.layers.service.RooService;
import java.util.List;
import com.sjsu.bikelet.domain.Bike;

@RooService(domainTypes = { com.sjsu.bikelet.domain.Bike.class })
public interface BikeService {
	
    public abstract long countAllBikesByStation(Long stationId);    
    public abstract List<Bike> findAllBikesByStation(Long stationId);    
    public abstract List<Bike> findBikeEntriesByStation(Long stationId, int firstResult, int maxResults);  
    public abstract List<Bike> findAvailableBikesByStation(Long stationId);
    
    public abstract long countAllBikesByTenant(Long tenantId);    
    public abstract List<Bike> findAllBikesByTenant(Long tenantId);    
    public abstract List<Bike> findBikeEntriesByTenant(Long tenantId, int firstResult, int maxResults);  
}
