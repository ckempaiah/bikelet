package com.sjsu.bikelet.service;

import org.springframework.roo.addon.layers.service.RooService;
import com.sjsu.bikelet.domain.Program;
import com.sjsu.bikelet.domain.BikeLetUser;
import com.sjsu.bikelet.service.ProgramService;
import java.util.List;

@RooService(domainTypes = { com.sjsu.bikelet.domain.Program.class })
public interface ProgramService {

	public abstract long countAllPrograms();

	public abstract void deleteProgram(Program program);

	public abstract Program findProgram(Long id);

	public abstract List<Program> findAllPrograms();

	public abstract List<Program> findProgramEntries(int firstResult,
			int maxResults);

	public abstract void saveProgram(Program program);

	public abstract Program updateProgram(Program program);

	public abstract List<Program> findAllProgramsByTenant(Long tenantId);
}
