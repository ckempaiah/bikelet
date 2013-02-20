package com.sjsu.bikelet.service;

import org.springframework.roo.addon.layers.service.RooService;
import com.sjsu.bikelet.domain.Program;
import com.sjsu.bikelet.domain.BikeLetUser;
import com.sjsu.bikelet.domain.Station;
import com.sjsu.bikelet.service.ProgramService;
import java.util.List;

@RooService(domainTypes = { com.sjsu.bikelet.domain.Program.class })
public interface ProgramService {

	public abstract List<Program> findAllProgramsByTenant(Long tenantId);
	
	public abstract long countAllProgramsByTenant(Long tenantId);
	
	public abstract List<Program> findProgramEntriesByTenant(Long tenantId, int firstResult,
			int maxResults);
}
