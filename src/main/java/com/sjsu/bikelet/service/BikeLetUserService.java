package com.sjsu.bikelet.service;

import com.sjsu.bikelet.domain.BikeLetUser;
import com.sjsu.bikelet.service.BikeLetUserService;
import java.util.List;
import org.springframework.roo.addon.layers.service.RooService;

@RooService(domainTypes = { com.sjsu.bikelet.domain.BikeLetUser.class })
public interface BikeLetUserService {

	public abstract long countAllBikeLetUsers();

	public abstract long countAllBikeLetUsersByProgram(Long programId);

	public abstract void deleteBikeLetUser(BikeLetUser bikeLetUser);

	public abstract BikeLetUser findBikeLetUser(Long id);

	public abstract List<BikeLetUser> findAllBikeLetUsers();

	public abstract List<BikeLetUser> findAllBikeLetUsersByProgram(
			Long programId);

	public abstract List<BikeLetUser> findBikeLetUserEntries(int firstResult,
			int maxResults);

	public abstract List<BikeLetUser> findBikeLetUserEntriesByProgram(
			Long programId, int firstResult, int maxResults);

	public abstract void saveBikeLetUser(BikeLetUser bikeLetUser);

	public abstract BikeLetUser updateBikeLetUser(BikeLetUser bikeLetUser);
}
