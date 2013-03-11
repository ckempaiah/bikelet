package com.sjsu.bikelet.service;

import java.util.Date;
import java.util.List;

import org.springframework.roo.addon.layers.service.RooService;

import com.sjsu.bikelet.domain.Bill;

@RooService(domainTypes = { com.sjsu.bikelet.domain.Bill.class })
public interface BillService {
  
  public abstract List<Bill> findBillEntriesByUser(int firstResult, int sizeNo, Long userId);
  
	public abstract List<Bill> findAllBillsByUser(Long userId);
	
	public abstract long countAllBillsForUser(Long userId);
	
	public abstract List<Bill> findBillEntriesByTenant(int firstResult, int sizeNo, Long tenantId);
	
	public abstract List<Bill> findAllBillsByTenant(Long tenantId);
	
	public abstract long countAllBillsForTenant(Long tenantId);

    public abstract Bill findLatestBill(Long userId);
    
    Bill findBillByStartDate(Long userId, Date startDate, Date endDate);
}
