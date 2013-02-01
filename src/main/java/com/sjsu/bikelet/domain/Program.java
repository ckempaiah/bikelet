package com.sjsu.bikelet.domain;

import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;
import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooEntity
public class Program {

    private Integer entityId;

    @Size(max = 60)
    private String programName;

    @Size(max = 255)
    private String description;

    @ManyToOne
    private SubscriptionPolicy subscpPolicy;

    @ManyToOne
    private Station stations;
}
