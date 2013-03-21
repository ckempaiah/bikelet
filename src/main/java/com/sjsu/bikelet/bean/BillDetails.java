package com.sjsu.bikelet.bean;

public class BillDetails {
	
	private Long billId;

    private String totalCost;

	public Long getBillId() {
		return billId;
	}

	public void setBillId(Long billId) {
		this.billId = billId;
	}

	public String getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(String totalCost) {
		this.totalCost = totalCost;
	}

    
}
