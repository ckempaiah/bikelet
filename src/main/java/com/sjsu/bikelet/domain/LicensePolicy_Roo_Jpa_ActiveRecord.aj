// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.sjsu.bikelet.domain;

import com.sjsu.bikelet.domain.LicensePolicy;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

privileged aspect LicensePolicy_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager LicensePolicy.entityManager;
    
    public static final EntityManager LicensePolicy.entityManager() {
        EntityManager em = new LicensePolicy().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long LicensePolicy.countLicensePolicys() {
        return entityManager().createQuery("SELECT COUNT(o) FROM LicensePolicy o", Long.class).getSingleResult();
    }
    
    public static List<LicensePolicy> LicensePolicy.findAllLicensePolicys() {
        return entityManager().createQuery("SELECT o FROM LicensePolicy o", LicensePolicy.class).getResultList();
    }
    
    public static LicensePolicy LicensePolicy.findLicensePolicy(Long id) {
        if (id == null) return null;
        return entityManager().find(LicensePolicy.class, id);
    }
    
    public static List<LicensePolicy> LicensePolicy.findLicensePolicyEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM LicensePolicy o", LicensePolicy.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void LicensePolicy.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void LicensePolicy.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            LicensePolicy attached = LicensePolicy.findLicensePolicy(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void LicensePolicy.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void LicensePolicy.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public LicensePolicy LicensePolicy.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        LicensePolicy merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}
