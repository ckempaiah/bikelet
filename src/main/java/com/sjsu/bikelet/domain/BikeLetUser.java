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
public class BikeLetUser {

    private Integer userId;

    private Integer programId;

    @NotNull
    @Size(min = 2, max = 30)
    private String firstName;

    @NotNull
    @Size(min = 2, max = 30)
    private String lastName;

    @NotNull
    @Size(min = 2, max = 40)
    private String email;

    @NotNull
    @Size(min = 6, max = 15)
    private String password;

    @ManyToOne
    private Address addresses;

    @ManyToOne
    private Bill bills;

    @ManyToOne
    private PaymentInfo paymentInfo;

    @ManyToOne
    private PaymentTransaction paymentTransactions;

    @ManyToOne
    private UserSubscriptionPolicy usp;

    @ManyToOne
    private UserRole roles;

    @ManyToOne
    private RentTransaction rentTransactions;
}
