package com.sjsu.bikelet.domain;

import java.util.Date;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class RentTransaction {

    private Integer fromStationId;

    private Long toStationId;

    private Long tenantId;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date rentStartDate;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date rentEndDate;

    private Integer rateId;

    @NotNull
    @Size(max = 10)
    private String status;

    @NotNull
    @Size(max = 500)
    private String comments;

    @ManyToOne
    private BikeLetUser userId;

    @ManyToOne
    private Bike bikeId;
}
