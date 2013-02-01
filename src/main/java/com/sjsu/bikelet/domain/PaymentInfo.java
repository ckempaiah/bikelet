package com.sjsu.bikelet.domain;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooEntity
public class PaymentInfo {

    private Integer paymentId;

    @NotNull
    @Size(min = 2)
    private String cardUserName;

    @NotNull
    @Size(min = 12, max = 19)
    private String cardNumber;

    @ManyToOne
    private PaymentTransaction paymentTransactions;
}
