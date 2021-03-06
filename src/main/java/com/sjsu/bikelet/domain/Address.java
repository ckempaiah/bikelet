package com.sjsu.bikelet.domain;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class Address {

    @NotNull
    @Size(max = 100)
    private String addressLine1;

    @NotNull
    @Size(max = 100)
    private String addressLine2;

    @NotNull
    @Size(min = 2)
    private String city;

    @NotNull
    @Size(min = 2)
    private String addressState;

    @NotNull
    @Size(min = 2)
    private String country;

    @NotNull
    @Size(min = 5, max = 5)
    private String zipCode;

    @NotNull
    @Size(min = 8, max = 15)
    private String cellphone;

    @NotNull
    @Size(max = 15)
    private String addressType;

    @NotNull
    @Size(min = 8, max = 15)
    private String workphone;

    @ManyToOne
    private BikeLetUser userId;
    
    public static Address findAddressByUser(Long userId)
    {
    	return entityManager().createQuery("SELECT o FROM Address o where o.userId.id = :userId", Address.class)
    				   .setParameter("userId",userId)
    				   .getSingleResult();
    }
}
