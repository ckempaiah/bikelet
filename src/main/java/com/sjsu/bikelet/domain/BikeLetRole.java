package com.sjsu.bikelet.domain;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooEntity
public class BikeLetRole {

    private Integer roleId;

    @NotNull
    private String roleName;

    @ManyToOne
    private UserRole users;

    @ManyToOne
    private RolePermission rolePermissions;
}
