package com.sjsu.bikelet.domain;

import javax.persistence.ManyToOne;
import java.util.List;
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
    
    public static List<Program> findAllProgramsByTenant(Long tenantId) {
        return entityManager().createQuery("SELECT o FROM Program o where o.tenantId.id = :tenantId", Program.class).setParameter("tenantId", tenantId).getResultList();
    }
    
    public static long countProgramsByTenant(Long tenantId) {
    	if (tenantId == null)
    		return countPrograms();
        return entityManager().createQuery("SELECT COUNT(o) FROM Program o where o.tenantId.id = :tenantId", Long.class).setParameter("tenantId", tenantId).getSingleResult();
    }
    
        
    public static List<Program> findProgramEntriesByTenant(Long tenantId, int firstResult, int maxResults) {
    	if (tenantId == null)
    		return findProgramEntries(firstResult, maxResults);
        return entityManager().createQuery("SELECT o FROM Program o where o.tenantId.id = :tenantId", Program.class).setParameter("tenantId", tenantId).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

}
