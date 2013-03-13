package com.sjsu.bikelet.domain;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;
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
    
    @NotNull
    @Min(100)
    @Max(9999)
    private Integer cvv;
    
    @NotNull
    @Min(1)
    @Max(12)
    private Integer cardExpMonth;
    
    @NotNull
    private Integer cardExpYear;
    
    public Integer getCvv() {
		return cvv;
	}

	public void setCvv(Integer cvv) {
		this.cvv = cvv;
	}

	public Integer getCardExpMonth() {
		return cardExpMonth;
	}

	public void setCardExpMonth(Integer cardExpMonth) {
		this.cardExpMonth = cardExpMonth;
	}

	public Integer getCardExpYear() {
		return cardExpYear;
	}

	public void setCardExpYear(Integer cardExpYear) {
		this.cardExpYear = cardExpYear;
	}

	public static PaymentInfo findPaymentInfoByUser(Long userId)
    {
    	return entityManager().createQuery("SELECT o FROM PaymentInfo o where o.userId.id = :userId", PaymentInfo.class)
    				   .setParameter("userId",userId)
    				   .getSingleResult();
    }
}
