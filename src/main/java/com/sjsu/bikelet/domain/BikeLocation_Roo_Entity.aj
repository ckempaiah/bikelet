// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.sjsu.bikelet.domain;

import com.sjsu.bikelet.domain.BikeLocation;
import java.lang.Long;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

privileged aspect BikeLocation_Roo_Entity {
    
    @PersistenceContext
    transient EntityManager BikeLocation.entityManager;
    
    @Transactional
    public void BikeLocation.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void BikeLocation.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            BikeLocation attached = BikeLocation.findBikeLocation(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void BikeLocation.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void BikeLocation.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public BikeLocation BikeLocation.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        BikeLocation merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
    public static final EntityManager BikeLocation.entityManager() {
        EntityManager em = new BikeLocation().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long BikeLocation.countBikeLocations() {
        return entityManager().createQuery("SELECT COUNT(o) FROM BikeLocation o", Long.class).getSingleResult();
    }
    
    public static List<BikeLocation> BikeLocation.findAllBikeLocations() {
        return entityManager().createQuery("SELECT o FROM BikeLocation o", BikeLocation.class).getResultList();
    }
    
    public static BikeLocation BikeLocation.findBikeLocation(Long id) {
        if (id == null) return null;
        return entityManager().find(BikeLocation.class, id);
    }
    
    public static List<BikeLocation> BikeLocation.findBikeLocationEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM BikeLocation o", BikeLocation.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
}
