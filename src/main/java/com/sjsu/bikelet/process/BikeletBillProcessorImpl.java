package com.sjsu.bikelet.process;

import com.sjsu.bikelet.domain.BikeLetUser;
import com.sjsu.bikelet.domain.Bill;
import com.sjsu.bikelet.domain.BillTransaction;
import com.sjsu.bikelet.model.PendingBillProjection;
import com.sjsu.bikelet.service.BillService;
import com.sjsu.bikelet.service.BillTransactionService;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import sun.util.resources.CalendarData_ar;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Bikelet bill processor
 * User: ckempaiah
 * Date: 3/6/13
 * Time: 4:31 PM
 * To change this template use File | Settings | File Templates.
 */
@Configuration
@EnableScheduling
public class BikeletBillProcessorImpl implements BikeletBillProcessor {
    private final Log log = LogFactory.getLog(BikeletBillProcessorImpl.class);
    @Autowired
    BillTransactionService billTransactionService;

    @Autowired
    BillService billService;

    @Override
    @Scheduled(fixedDelay = 300000) // runs every 5 minutes
    //@Scheduled(fixedDelay = 10000) // uncomment to run every 10 secs
    public void processBill() {
        log.debug("Called bikelet bill processor");

        Date now = GregorianCalendar.getInstance().getTime();

        //Date endOfMonth = DateUtils.ceiling(now, Calendar.MONTH);
        //endOfMonth = new Date(endOfMonth.getTime()-1);
        //Date startOfMonth = DateUtils.truncate(now, Calendar.MONTH);
        //log.debug(String.format("Start Date: %s End Date: %s", startOfMonth, endOfMonth));
        // query to find the project object
        List<PendingBillProjection> monthsToProcess = BillTransaction.findPendingMonths();
        log.debug("Months:" + monthsToProcess);

        for (PendingBillProjection billProjection : monthsToProcess) {
            log.debug(String.format("UserId %s, Month %s", billProjection.getUserId(), billProjection.getMonthId()));
            Long userId = billProjection.getUserId();
            Long month = billProjection.getMonthId();
            //get the current date and use ceil and floor functions to determine the beginning and end of month
            Calendar cal = GregorianCalendar.getInstance();

            cal.set(Calendar.MONTH, month.intValue() - 1);
            Date processDate = cal.getTime();
            log.debug("ProcessDate: " + processDate);
            Date endOfProcessDate = DateUtils.ceiling(processDate, Calendar.MONTH);
            endOfProcessDate = new Date(endOfProcessDate.getTime() - 1);
            Date startOfProcessDate = DateUtils.truncate(processDate, Calendar.MONTH);
            log.debug("endOfProcessDate:" + endOfProcessDate);
            log.debug("userId:" + userId);
            log.debug("startOfProcessDate:" + startOfProcessDate);
            createUpdateBill(userId, startOfProcessDate, endOfProcessDate, now);
        }
    }
    // CK, the transactional syntax was key to saving multiple entities, otherwise spring/hibernate throws transient object exception
    @Transactional
    public void createUpdateBill(Long userId, Date startOfProcessDate, Date endOfProcessDate, Date now) {

        List<BillTransaction> billTransactions = billTransactionService.findPendingBillTransactions(userId, startOfProcessDate, endOfProcessDate);
        log.debug("billTransactions" + billTransactions);
        if (CollectionUtils.isEmpty(billTransactions)) {
            return;
        }

        BikeLetUser bikeLetUser = BikeLetUser.findBikeLetUser(userId);
        Bill bill = billService.findBillByStartDate(userId, startOfProcessDate, endOfProcessDate);

        if (bill == null) {
            //create new bill
            bill = new Bill();
            bill.setBillStartDate(startOfProcessDate);
            bill.setBillEndDate(endOfProcessDate);
            bill.setCreatedDate(now);
            bill.setDescription("Monthly Bill");
            //bill.setUser(bikeLetUser.getUserName());
            bill.setUserId(bikeLetUser);
            bill.setTotalcharges(getTotalCharges(billTransactions));
            billService.saveBill(bill);
            updateBillToBillTransaction(billTransactions, bill);
        } else {
            // update transaction to existing bill
            bill.setTotalcharges(bill.getTotalcharges() + getTotalCharges(billTransactions));
            billService.updateBill(bill);
            updateBillToBillTransaction(billTransactions, bill);
        }
    }

    public double getTotalCharges(List<BillTransaction> billTransactions) {
        double totalCost = 0.0;
        for (BillTransaction billTransaction : billTransactions) {
            if (billTransaction.getTotalCost() != null) {
                totalCost += billTransaction.getTotalCost();
            }
        }
        return totalCost;
    }

    public void updateBillToBillTransaction(List<BillTransaction> billTransactions, Bill bill) {
        for (BillTransaction billTransaction : billTransactions) {
            BillTransaction.entityManager().refresh(billTransaction);
            billTransaction.setBill(bill);
            billTransactionService.updateBillTransaction(billTransaction);
        }
    }
}
