package com.sjsu.bikelet.service;

import java.util.List;
import com.sjsu.bikelet.domain.Program;
import com.sjsu.bikelet.domain.Station;

public class ProgramServiceImpl implements ProgramService {
	
    public List<Program> findAllProgramsByTenant(Long tenantId) {
        return Program.findAllProgramsByTenant(tenantId);
    }

	@Override
	public long countAllProgramsByTenant(Long tenantId) {
		// TODO Auto-generated method stub
		return Program.countProgramsByTenant(tenantId);
	}

	@Override
	public List<Program> findProgramEntriesByTenant(Long tenantId,
			int firstResult, int maxResults) {
		// TODO Auto-generated method stub
		return Program.findProgramEntriesByTenant(tenantId, firstResult, maxResults);
	}
}
