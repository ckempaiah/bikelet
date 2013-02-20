package com.sjsu.bikelet.service;


import com.sjsu.bikelet.domain.Bike;
import java.lang.Long;
import java.util.List;

public class BikeServiceImpl implements BikeService {
	
    public long countAllBikesByStation(Long stationId) {
        return Bike.countBikesByStation(stationId);
    }
    
    public List<Bike> findAllBikesByStation(Long stationId) {
        return Bike.findAllBikesByStation(stationId);
    }
    
    public List<Bike> findBikeEntriesByStation(Long stationId, int firstResult, int maxResults) {
        return Bike.findBikeEntriesByStation(stationId, firstResult, maxResults);
    }
    
	public List<Bike> findAvailableBikesByStation(Long stationId) {
		return Bike.findAvailableBikesByStation(stationId);
	}

}
