package com.sjsu.bikelet.domain;

import java.util.Date;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooEntity
public class Bike {

    private Integer bikeheight;

    private Integer bikeId;

    @Size(max = 10)
    private String bikecolor;

    @NotNull
    @Size(min = 2, max = 20)
    private String bikeType;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date lastServiceDate;

    @Size(max = 10)
    private String wheelSize;

    @Size(max = 10)
    private String bikestatus;

    private Integer tenantId;

    @OneToOne
    private BikeLocation bikeLocation;

    @ManyToOne
    private RentTransaction rentTransactions;
}
