package com.sjsu.bikelet.service;

import com.sjsu.bikelet.domain.Station;
import com.sjsu.bikelet.service.StationServiceImpl;
import java.util.List;

public class StationServiceImpl implements StationService {
	
    public long countAllStationsByTenant(Long tenantId) {
	    return Station.countStationsByTenant(tenantId);
	}
	     
    public List<Station> findAllStationsByTenant(Long tenantId) {
	   return Station.findAllStationsByTenant(tenantId);
	}

	public List<Station> findStationEntriesByTenant(Long tenantId, int firstResult, int maxResults) {
	    return Station.findStationEntriesByTenant(tenantId, firstResult, maxResults);
	}
   
}
