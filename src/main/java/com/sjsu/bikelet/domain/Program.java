package com.sjsu.bikelet.domain;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class Program {

    @Size(max = 60)
    private String programName;

    @Size(max = 255)
    private String description;

    @ManyToOne
    private Tenant tenantId;

    @NotNull
    @Size(max = 30)
    private String orgName;

    private Long contactId;

    private Integer min_threshold;

    private Integer max_threshold;
}
