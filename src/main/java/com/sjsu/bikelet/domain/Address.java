package com.sjsu.bikelet.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooEntity
public class Address {

    private Integer addressId;

    @NotNull
    @Size(max = 100)
    private String addressLine1;

    @NotNull
    @Size(max = 100)
    private String addressLine2;

    @NotNull
    @Size(min = 2)
    private String city;

    @NotNull
    @Size(min = 2)
    private String cState;

    @NotNull
    @Size(min = 2)
    private String country;

    @NotNull
    @Size(min = 5, max = 5)
    private String zipCode;

    @NotNull
    @Size(min = 8, max = 15)
    private String cellphone;

    @NotNull
    @Size(max = 15)
    private String address_type;

    @NotNull
    @Size(min = 8, max = 15)
    private String workphone;

    private Integer entityId;

    @NotNull
    @Size(min = 8, max = 15)
    private String entityType;
}
