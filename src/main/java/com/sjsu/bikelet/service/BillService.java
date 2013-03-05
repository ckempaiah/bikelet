package com.sjsu.bikelet.service;

import org.springframework.roo.addon.layers.service.RooService;

@RooService(domainTypes = { com.sjsu.bikelet.domain.Bill.class })
public interface BillService {
  
  public abstract List<Bill> findBillEntriesByUser(int firstResult, int sizeNo, Long userId);
  
	public abstract List<Bill> findAllBillsByUser(Long userId);
	
	public abstract long countAllBillsForUser(Long userId);
	
	public abstract List<Bill> findBillEntriesByTenant(int firstResult, int sizeNo, Long tenantId);
	
	public abstract List<Bill> findAllBillsByTenant(Long tenantId);
	
	public abstract long countAllBillsForTenant(Long tenantId);
}
