// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.sjsu.bikelet.service;

import com.sjsu.bikelet.domain.Program;
import com.sjsu.bikelet.domain.SubscriptionPolicy;
import com.sjsu.bikelet.service.SubscriptionPolicyServiceImpl;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

privileged aspect SubscriptionPolicyServiceImpl_Roo_Service {
    
    declare @type: SubscriptionPolicyServiceImpl: @Service;
    
    declare @type: SubscriptionPolicyServiceImpl: @Transactional;
    
    public long SubscriptionPolicyServiceImpl.countAllSubscriptionPolicys() {
        return SubscriptionPolicy.countSubscriptionPolicys();
    }
    
    public void SubscriptionPolicyServiceImpl.deleteSubscriptionPolicy(SubscriptionPolicy subscriptionPolicy) {
        subscriptionPolicy.remove();
    }
    
    public SubscriptionPolicy SubscriptionPolicyServiceImpl.findSubscriptionPolicy(Long id) {
        return SubscriptionPolicy.findSubscriptionPolicy(id);
    }
    
    public List<SubscriptionPolicy> SubscriptionPolicyServiceImpl.findAllSubscriptionPolicys() {
        return SubscriptionPolicy.findAllSubscriptionPolicys();
    }
    
    public List<SubscriptionPolicy> SubscriptionPolicyServiceImpl.findSubscriptionPolicyEntries(int firstResult, int maxResults) {
        return SubscriptionPolicy.findSubscriptionPolicyEntries(firstResult, maxResults);
    }
    
    public void SubscriptionPolicyServiceImpl.saveSubscriptionPolicy(SubscriptionPolicy subscriptionPolicy) {
        subscriptionPolicy.persist();
    }
    
    public SubscriptionPolicy SubscriptionPolicyServiceImpl.updateSubscriptionPolicy(SubscriptionPolicy subscriptionPolicy) {
        return subscriptionPolicy.merge();
    }
}
