package com.sjsu.bikelet.domain;

import javax.persistence.ManyToOne;

import javax.validation.constraints.NotNull;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

import com.sjsu.bikelet.domain.Station;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class Station {

    @NotNull
    private String location;

    @ManyToOne
    private Tenant tenantId;

    @ManyToOne
    private Program programId;

    private Integer capacity;

    public static long countStationsByTenant(Long tenantId) {
    	if (tenantId == null)
    		return countStations();
        return entityManager().createQuery("SELECT COUNT(o) FROM Station o where o.tenantId.id = :tenantId", Long.class).setParameter("tenantId", tenantId).getSingleResult();
    }
    
    public static List<Station> findAllStationsByTenant(Long tenantId) {
    	if (tenantId == null)
    		return findAllStations();
        return entityManager().createQuery("SELECT o FROM Station o where o.tenantId.id = :tenantId", Station.class).setParameter("tenantId", tenantId).getResultList();
    }
    
    public static List<Station> findStationEntriesByTenant(Long tenantId, int firstResult, int maxResults) {
    	if (tenantId == null)
    		return findStationEntries(firstResult, maxResults);
        return entityManager().createQuery("SELECT o FROM Station o where o.tenantId.id = :tenantId", Station.class).setParameter("tenantId", tenantId).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    public static List<Station> findAllStationsByProgram(Long programId) {
    	if (programId == null)
    		return findAllStations();
        return entityManager().createQuery("SELECT o FROM Station o where o.programId.id = :programId", Station.class)
        					  .setParameter("programId", programId)
        					  .getResultList();
    }
}
