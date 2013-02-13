package com.sjsu.bikelet.domain;

import javax.validation.constraints.NotNull;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class BikeLetRole {

    @NotNull
    private String roleName;
    
    public static BikeLetRole findBikeLetRoleByName(String roleName) {
        if (roleName==null) return null;
        return entityManager().createQuery("SELECT o FROM BikeLetRole o where o.roleName = :roleName", BikeLetRole.class).setParameter("roleName", roleName).getSingleResult();
    }
}
