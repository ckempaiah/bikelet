package com.sjsu.bikelet.domain;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

import com.sjsu.bikelet.domain.BikeLetUser;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author chenglin
 *
 */
@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class BikeLetUser {

    @NotNull
    @Size(min = 2, max = 30)
    private String firstName;

    @NotNull
    @Size(min = 2, max = 30)
    private String lastName;

    @NotNull
    @Size(min = 2, max = 40)
    private String email;

    @NotNull
    @Size(min = 6, max = 15)
    private String password;

    @ManyToOne
    @NotNull
    private Tenant tenantId;

    @ManyToOne
    private Program programId;

    @NotNull
    @Column(name = "user_name")
    private String userName;
    
    public static long countBikeLetUsersByProgram(Long programId) {
        return entityManager().createQuery("SELECT COUNT(o) FROM BikeLetUser o where o.programId.id = :programId", Long.class).setParameter("programId", programId).getSingleResult();
    }
    
    public static List<BikeLetUser> findAllBikeLetUsersByProgram(Long programId) {
        return entityManager().createQuery("SELECT o FROM BikeLetUser o where o.programId.id = :programId", BikeLetUser.class).setParameter("programId", programId).getResultList();
    }

    public static List<BikeLetUser> findBikeLetUserEntriesByProgram(Long programId, int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM BikeLetUser o where o.programId.id = :programId", BikeLetUser.class).setParameter("programId", programId).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transient
    private SubscriptionPolicy subscriptionPolicy;
    
    public SubscriptionPolicy getSubscriptionPolicy() {
        return this.subscriptionPolicy;
    }
    
    public void setSubscriptionPolicy(SubscriptionPolicy subscriptionPolicy) {
        this.subscriptionPolicy = subscriptionPolicy;
    }

    public static BikeLetUser getUserFromId(Long userId){
    	return entityManager().createQuery("SELECT o FROM BikeLetUser o where o.id = :userId", BikeLetUser.class).setParameter("userId", userId).getSingleResult();
    }
}
