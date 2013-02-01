package com.sjsu.bikelet.domain;

import javax.validation.constraints.Size;
import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooEntity
public class BikeLocation {

    @Size(max = 10)
    private String bikeStatus;
}
