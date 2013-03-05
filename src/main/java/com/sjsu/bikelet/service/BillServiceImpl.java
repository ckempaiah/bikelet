package com.sjsu.bikelet.service;

import java.util.List;
import com.sjsu.bikelet.domain.Bill;


public class BillServiceImpl implements BillService {

  @Override
	public List<Bill> findBillEntriesByUser(int firstResult, int maxResults,
			Long userId) {
		// TODO Auto-generated method stub
		return Bill.findBillEntriesByUser(firstResult, maxResults, userId);
	}

	@Override
	public List<Bill> findAllBillsByUser(Long userId) {
		// TODO Auto-generated method stub
		return Bill.findAllBillsByUser(userId);
	}

	@Override
	public long countAllBillsForUser(Long userId) {
		// TODO Auto-generated method stub
		return Bill.countBillsByUser(userId);
	}

	@Override
	public List<Bill> findBillEntriesByTenant(int firstResult, int maxResults,
			Long tenantId) {
		// TODO Auto-generated method stub
		return Bill.findBillEntriesByTenant(firstResult, maxResults, tenantId);
	}

	@Override
	public List<Bill> findAllBillsByTenant(Long tenantId) {
		// TODO Auto-generated method stub
		return Bill.findAllBillsByTenant(tenantId);
	}

	@Override
	public long countAllBillsForTenant(Long tenantId) {
		// TODO Auto-generated method stub
		return Bill.countBillsByTenant(tenantId);
	}

}
