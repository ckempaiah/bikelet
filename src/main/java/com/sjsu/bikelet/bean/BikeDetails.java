package com.sjsu.bikelet.bean;

import java.util.Date;

public class BikeDetails {
	private Integer bikeHeight;

	private String bikeColor;

	private String bikeType;

	private Date lastServiceDate;

	private String wheelSize;

	private String bikeStatus;

	private Long tenantId;

	private Long stationId;

	public Integer getBikeHeight() {
		return bikeHeight;
	}

	public void setBikeHeight(Integer bikeHeight) {
		this.bikeHeight = bikeHeight;
	}

	public String getBikeColor() {
		return bikeColor;
	}

	public void setBikeColor(String bikeColor) {
		this.bikeColor = bikeColor;
	}

	public String getBikeType() {
		return bikeType;
	}

	public void setBikeType(String bikeType) {
		this.bikeType = bikeType;
	}

	public Date getLastServiceDate() {
		return lastServiceDate;
	}

	public void setLastServiceDate(Date lastServiceDate) {
		this.lastServiceDate = lastServiceDate;
	}

	public String getWheelSize() {
		return wheelSize;
	}

	public void setWheelSize(String wheelSize) {
		this.wheelSize = wheelSize;
	}

	public String getBikeStatus() {
		return bikeStatus;
	}

	public void setBikeStatus(String bikeStatus) {
		this.bikeStatus = bikeStatus;
	}

	public Long getTenantId() {
		return tenantId;
	}

	public void setTenantId(Long tenantId) {
		this.tenantId = tenantId;
	}

	public Long getStationId() {
		return stationId;
	}

	public void setStationId(Long stationId) {
		this.stationId = stationId;
	}
	
	
}
