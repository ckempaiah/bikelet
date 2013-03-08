package com.sjsu.bikelet.bean;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "TransactionDetails")
public class User {
	 private String firstName;

     private String lastName;

	 private String email;

	 private Long tenantId;

     private Long programId;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getTenantId() {
		return tenantId;
	}

	public void setTenantId(Long tenantId) {
		this.tenantId = tenantId;
	}

	public Long getProgramId() {
		return programId;
	}

	public void setProgramId(Long programId) {
		this.programId = programId;
	}

	
	public static User convert(com.sjsu.bikelet.domain.BikeLetUser bikeletuser) {
		User user = null;
		
		if (bikeletuser != null) {
			user = new User();
			user.setFirstName(bikeletuser.getFirstName());
			user.setLastName(bikeletuser.getLastName());
			user.setEmail(bikeletuser.getEmail());
			if (bikeletuser.getProgramId() != null)
			    user.setProgramId(bikeletuser.getProgramId().getId());
			if (bikeletuser.getTenantId() != null)
				  user.setTenantId(bikeletuser.getTenantId().getId());
		}
		
		return user;
	}
}
