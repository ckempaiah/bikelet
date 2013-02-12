package com.sjsu.bikelet.service;

import org.springframework.roo.addon.layers.service.RooService;
import com.sjsu.bikelet.domain.Station;
import com.sjsu.bikelet.service.StationService;
import java.util.List;

@RooService(domainTypes = { com.sjsu.bikelet.domain.Station.class })
public interface StationService {

	public abstract long countAllStations();

	public abstract long countAllStationsByTenant(Long tenantId);

	public abstract void deleteStation(Station station);

	public abstract Station findStation(Long id);

	public abstract List<Station> findAllStations();

	public abstract List<Station> findAllStationsByTenant(Long tenantId);

	public abstract List<Station> findStationEntries(int firstResult,
			int maxResults);

	public abstract List<Station> findStationEntriesByTenant(Long tenantId, int firstResult,
			int maxResults);

	public abstract void saveStation(Station station);

	public abstract Station updateStation(Station station);

}
