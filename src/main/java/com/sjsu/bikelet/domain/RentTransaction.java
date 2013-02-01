package com.sjsu.bikelet.domain;

import java.util.Date;
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
public class RentTransaction {

    private Integer transactionId;

    private Integer fromStationId;

    private Integer toStationId;

    private Integer tenantId;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date rentStartDate;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date rentEndDate;

    @NotNull
    @Size(max = 10)
    private String status;

    @NotNull
    @Size(max = 500)
    private String comments;

    private Integer rateId;
}
