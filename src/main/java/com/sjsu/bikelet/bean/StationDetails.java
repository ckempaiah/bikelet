package com.sjsu.bikelet.bean;

public class StationDetails {
private Integer numberOfBikesAvailable;
	
	public Integer getNumberOfBikesAvailable() {
		return numberOfBikesAvailable;
	}

	public void setNumberOfBikesAvailable(Integer numberOfBikesAvailable) {
		this.numberOfBikesAvailable = numberOfBikesAvailable;
	}

	private String location;

   
    private String tenantId;

   
    private String programId;
    
	private Integer capacity;


    public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public String getProgramId() {
		return programId;
	}

	public void setProgramId(String programId) {
		this.programId = programId;
	}

	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}


}
