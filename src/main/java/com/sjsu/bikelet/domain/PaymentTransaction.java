package com.sjsu.bikelet.domain;

import java.util.Date;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class PaymentTransaction {

    private Integer status;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date dateOfTransaction;

    @Size(max = 30)
    private String permissionName;

    @Size(max = 100)
    private String description;

    @ManyToOne
    private PaymentInfo paymentId;

    @ManyToOne
    private BikeLetUser userId;

    @OneToOne
    private Bill billId;
}
