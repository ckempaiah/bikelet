package com.sjsu.bikelet.domain;

import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.Size;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class BikeLocation {

    @Size(max = 10)
    private String bikeStatus;

    @ManyToOne
    private Bike bike;

    @ManyToOne
    private Station stationId;

    @OneToOne
    private Bike bikeId;
}
