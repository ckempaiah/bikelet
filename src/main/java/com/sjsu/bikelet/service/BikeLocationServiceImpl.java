package com.sjsu.bikelet.service;

import com.sjsu.bikelet.domain.BikeLocation;


public class BikeLocationServiceImpl implements BikeLocationService {

	@Override
	public void updateBikeLocation(Long bikeId, String status, Long stationId) {
		// TODO Auto-generated method stub
		System.out.println("In BikeLocationServiceImpl ..................");
		BikeLocation.updateBikeLocation(bikeId, status, stationId);
	}

	@Override
	public Long countAvailableBikesByStation(Long stationId) {
		// TODO Auto-generated method stub
		return BikeLocation.countAvailableBikesByStation(stationId);
	}

	@Override
	public BikeLocation findBikeLocationOfBike(Long bikeId) {
		// TODO Auto-generated method stub
		return BikeLocation.findBikeLocationOfBike(bikeId);
	}
}
