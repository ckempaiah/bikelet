package com.sjsu.bikelet.service;

import org.springframework.roo.addon.layers.service.RooService;
import com.sjsu.bikelet.domain.Station;
import com.sjsu.bikelet.service.StationService;
import java.util.List;

@RooService(domainTypes = { com.sjsu.bikelet.domain.Station.class })
public interface StationService {

	public abstract long countAllStationsByTenant(Long tenantId);

	public abstract List<Station> findAllStationsByTenant(Long tenantId);

	public abstract List<Station> findStationEntriesByTenant(Long tenantId, int firstResult,
			int maxResults);

	public abstract List<Station> findAllStationsByProgram(Long programId);
}
