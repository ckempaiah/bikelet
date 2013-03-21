package com.sjsu.bikelet.bean;


import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
 
@XmlRootElement(name = "TransactionDetails")
public class TransactionDetails {

	private Long id;
	
	private String fromStation;

    private String toStation;

    private String rentStartDate;

    private String rentEndDate;

    private String status;

    private String comments;

	private String bike;
	
	private String billDetails;
	
	public String getBillDetails() {
		return billDetails;
	}

	public void setBillDetails(String billDetails) {
		this.billDetails = billDetails;
	}

	private String accessKey;
	
	private List<StationDetails> stationList;

	public List<StationDetails> getStationList() {
		return stationList;
	}

	public void setStationList(List<StationDetails> stationList) {
		this.stationList = stationList;
	}

	public String getAccessKey() {
		return accessKey;
	}

	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFromStation() {
		return fromStation;
	}

	public void setFromStation(String fromStation) {
		this.fromStation = fromStation;
	}

	public String getToStation() {
		return toStation;
	}

	public void setToStation(String toStation) {
		this.toStation = toStation;
	}

	public String getRentStartDate() {
		return rentStartDate;
	}

	public void setRentStartDate(String rentStartDate) {
		this.rentStartDate = rentStartDate;
	}

	public String getRentEndDate() {
		return rentEndDate;
	}

	public void setRentEndDate(String rentEndDate) {
		this.rentEndDate = rentEndDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getBike() {
		return bike;
	}

	public void setBike(String bike) {
		this.bike = bike;
	}
	
	
}
