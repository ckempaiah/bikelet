package com.sjsu.bikelet.process;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: ckempaiah
 * Date: 2/28/13
 * Time: 9:10 AM
 * To change this template use File | Settings | File Templates.
 */
public interface BikeletBillProcessor {

    public void generateMonthlyBill();

    public void generateOneTimeBill(Long memberId,Date fromDate, Date toDate);

}
