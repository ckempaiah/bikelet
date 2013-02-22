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
public class PaymentInfo {

    @NotNull
    @Size(min = 12, max = 19)
    private String cardNumber;

    @NotNull
    @Size(min = 2)
    private String cardUserName;

    @ManyToOne
    private BikeLetUser userId;
    
    public static PaymentInfo findPaymentInfoByUser(Long userId)
    {
    	return entityManager().createQuery("SELECT o FROM PaymentInfo o where o.userId.id = :userId", PaymentInfo.class)
    				   .setParameter("userId",userId)
    				   .getSingleResult();
    }
}
