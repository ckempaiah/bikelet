package com.sjsu.bikelet.service;

import java.util.List;
import com.sjsu.bikelet.domain.Program;

public class ProgramServiceImpl implements ProgramService {
	
    public List<Program> findAllProgramsByTenant(Long tenantId) {
        return Program.findAllProgramsByTenant(tenantId);
    }
}
