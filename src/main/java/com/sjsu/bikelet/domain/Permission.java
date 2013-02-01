package com.sjsu.bikelet.domain;

import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;
import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooEntity
public class Permission {

    private Integer permissionId;

    @Size(max = 30)
    private String permissionName;

    @Size(max = 100)
    private String description;

    @ManyToOne
    private RolePermission rolePermissions;
}
