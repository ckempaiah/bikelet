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
public class Organization {

    private Integer orgId;

    @NotNull
    @Size(max = 30)
    private String orgName;

    private Integer contactId;

    private Integer orgColumn;

    @ManyToOne
    private Program programs;
}
