package com.sjsu.bikelet.service;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.sjsu.bikelet.domain.BikeLetUser;
import com.sjsu.bikelet.domain.RentTransaction;
import com.sjsu.bikelet.model.RentTransactionStatusEnum;
import org.apache.commons.lang3.time.DateUtils;

import javax.persistence.TypedQuery;


public class RentTransactionServiceImpl implements RentTransactionService {

	@Override
	public RentTransaction findRentTransactionForCheckin(Long userId,
			String status) {
		System.out.println("user id "+userId + "status "+status);
		// TODO Auto-generated method stub
		return RentTransaction.findRentTransactionForCheckin(userId, status);
	}
	
	@Override
	public List<RentTransaction> findRentTransactionsByUser(int firstResult,
			int maxResults, Long userId) {
		// TODO Auto-generated method stub
		return RentTransaction.findRentTransactionsByUser(firstResult, maxResults, userId);
	}

	@Override
	public List<RentTransaction> findAllRentTransactionsByUser(Long userId) {
		// TODO Auto-generated method stub
		return RentTransaction.findAllRentTransactionsByUser(userId);
	}

    @Override
    public List<RentTransaction> findTransactionsForToday(Long userId, RentTransactionStatusEnum statusEnum){
        TypedQuery<RentTransaction> query =  RentTransaction.entityManager().createQuery("from RentTransaction rt where rt.userId.id=:userId " +
                "and rt.rentStartDate >=:rentStart and rt.rentEndDate <=:rentEnd and rt.status=:status", RentTransaction.class);
        Date date = GregorianCalendar.getInstance().getTime();
        Date startDate = DateUtils.truncate(date, Calendar.DATE);
        Date endDate = DateUtils.addMilliseconds(DateUtils.ceiling(date, Calendar.DATE),-1);
        query.setParameter("userId", userId);
        query.setParameter("rentStart", startDate);
        query.setParameter("rentEnd", endDate);
        query.setParameter("status", statusEnum.name());
        return query.getResultList();
    }
    
    	@Override
	public long countAllRentTransactionsForUser(Long userId) {
		// TODO Auto-generated method stub
		return RentTransaction.countRentTransactionsForUser(userId);
	}

		@Override
		public RentTransaction findLastTransactionsByUser(Long userId) {
			return RentTransaction.findLastTransactionsByUser(userId);
		}
}
