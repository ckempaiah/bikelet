package com.sjsu.bikelet.domain;

import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooEntity
public class Station {

    private Integer stationId;

    @NotNull
    private String location;

    @OneToOne
    private BikeLocation bikeLocation;
}
