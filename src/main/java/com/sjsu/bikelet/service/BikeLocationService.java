package com.sjsu.bikelet.service;

import org.springframework.roo.addon.layers.service.RooService;

import com.sjsu.bikelet.domain.BikeLocation;

@RooService(domainTypes = { com.sjsu.bikelet.domain.BikeLocation.class })
public interface BikeLocationService {
	
	public abstract void updateBikeLocation(Long bikeId, String status, Long stationId);
	
	public abstract long countAvailableBikesByStation(Long stationId);
	
	public abstract BikeLocation findBikeLocationOfBike(Long bikeId);
}
