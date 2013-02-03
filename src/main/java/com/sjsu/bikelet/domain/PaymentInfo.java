package com.sjsu.bikelet.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class PaymentInfo {

    private Integer paymentId;

    @NotNull
    @Size(min = 12, max = 19)
    private String cardNumber;

    @NotNull
    @Size(min = 2)
    private String cardUserName;
}
