// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.sjsu.bikelet.domain;

import com.sjsu.bikelet.domain.PaymentInfo;
import java.lang.Long;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

privileged aspect PaymentInfo_Roo_Entity {
    
    @PersistenceContext
    transient EntityManager PaymentInfo.entityManager;
    
    @Transactional
    public void PaymentInfo.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void PaymentInfo.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            PaymentInfo attached = PaymentInfo.findPaymentInfo(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void PaymentInfo.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void PaymentInfo.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public PaymentInfo PaymentInfo.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        PaymentInfo merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
    public static final EntityManager PaymentInfo.entityManager() {
        EntityManager em = new PaymentInfo().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long PaymentInfo.countPaymentInfoes() {
        return entityManager().createQuery("SELECT COUNT(o) FROM PaymentInfo o", Long.class).getSingleResult();
    }
    
    public static List<PaymentInfo> PaymentInfo.findAllPaymentInfoes() {
        return entityManager().createQuery("SELECT o FROM PaymentInfo o", PaymentInfo.class).getResultList();
    }
    
    public static PaymentInfo PaymentInfo.findPaymentInfo(Long id) {
        if (id == null) return null;
        return entityManager().find(PaymentInfo.class, id);
    }
    
    public static List<PaymentInfo> PaymentInfo.findPaymentInfoEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM PaymentInfo o", PaymentInfo.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
}
