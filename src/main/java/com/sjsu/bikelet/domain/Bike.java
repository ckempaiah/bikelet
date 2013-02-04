package com.sjsu.bikelet.domain;

import java.util.Date;
import javax.persistence.Column;
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
public class Bike {

    private Integer bikeHeight;

    @Size(max = 10)
    private String bikeColor;

    @NotNull
    @Size(min = 2, max = 20)
    private String bikeType;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date lastServiceDate;

    @Size(max = 10)
    private String wheelSize;

    @Size(max = 10)
    private String bikeStatus;

    @ManyToOne
    private Tenant tenantId;
}
