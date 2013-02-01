// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.sjsu.bikelet.domain;

import com.sjsu.bikelet.domain.Tenant;
import java.lang.Long;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

privileged aspect Tenant_Roo_Entity {
    
    @PersistenceContext
    transient EntityManager Tenant.entityManager;
    
    @Transactional
    public void Tenant.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void Tenant.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            Tenant attached = Tenant.findTenant(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void Tenant.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void Tenant.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public Tenant Tenant.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Tenant merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
    public static final EntityManager Tenant.entityManager() {
        EntityManager em = new Tenant().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long Tenant.countTenants() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Tenant o", Long.class).getSingleResult();
    }
    
    public static List<Tenant> Tenant.findAllTenants() {
        return entityManager().createQuery("SELECT o FROM Tenant o", Tenant.class).getResultList();
    }
    
    public static Tenant Tenant.findTenant(Long id) {
        if (id == null) return null;
        return entityManager().find(Tenant.class, id);
    }
    
    public static List<Tenant> Tenant.findTenantEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Tenant o", Tenant.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
}
