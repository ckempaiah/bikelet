package com.sjsu.bikelet.service;

import java.util.Date;
import java.util.List;

import com.sjsu.bikelet.domain.Bill;
import org.springframework.dao.EmptyResultDataAccessException;

import javax.persistence.TypedQuery;


public class BillServiceImpl implements BillService {

  @Override
	public List<Bill> findBillEntriesByUser(int firstResult, int maxResults,
			Long userId) {
		return Bill.findBillEntriesByUser(firstResult, maxResults, userId);
	}

	@Override
	public List<Bill> findAllBillsByUser(Long userId) {

		return Bill.findAllBillsByUser(userId);
	}

	@Override
	public long countAllBillsForUser(Long userId) {

		return Bill.countBillsByUser(userId);
	}

	@Override
	public List<Bill> findBillEntriesByTenant(int firstResult, int maxResults,
			Long tenantId) {

		return Bill.findBillEntriesByTenant(firstResult, maxResults, tenantId);
	}

	@Override
	public List<Bill> findAllBillsByTenant(Long tenantId) {

		return Bill.findAllBillsByTenant(tenantId);
	}

	@Override
	public long countAllBillsForTenant(Long tenantId) {
		return Bill.countBillsByTenant(tenantId);
	}

	@Override
	public Bill findLatestBill(Long userId) {
		// TODO Auto-generated method stub
		return Bill.findLatestBill(userId);
	}

    @Override
    public Bill findBillByStartDate(Long userId, Date startDate, Date endDate){
        try {
            TypedQuery<Bill> billQuery = Bill.entityManager().createQuery("select b from Bill b where (b.billStartDate between :startDate and :endDate) and b.userId.id=:userId", Bill.class)
                    .setParameter("startDate", startDate)
                    .setParameter("endDate", endDate)
                    .setParameter("userId", userId);
            return billQuery.getSingleResult();
        } catch(EmptyResultDataAccessException erdae){
            //if empty result return null
            return null;
        }
    }

}
