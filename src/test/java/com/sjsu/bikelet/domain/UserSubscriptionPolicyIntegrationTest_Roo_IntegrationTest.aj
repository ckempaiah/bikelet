// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.sjsu.bikelet.domain;

import com.sjsu.bikelet.domain.UserSubscriptionPolicyDataOnDemand;
import com.sjsu.bikelet.domain.UserSubscriptionPolicyIntegrationTest;
import com.sjsu.bikelet.service.UserSubscriptionPolicyService;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

privileged aspect UserSubscriptionPolicyIntegrationTest_Roo_IntegrationTest {
    
    declare @type: UserSubscriptionPolicyIntegrationTest: @RunWith(SpringJUnit4ClassRunner.class);
    
    declare @type: UserSubscriptionPolicyIntegrationTest: @ContextConfiguration(locations = "classpath:/META-INF/spring/applicationContext*.xml");
    
    declare @type: UserSubscriptionPolicyIntegrationTest: @Transactional;
    
    @Autowired
    UserSubscriptionPolicyDataOnDemand UserSubscriptionPolicyIntegrationTest.dod;
    
    @Autowired
    UserSubscriptionPolicyService UserSubscriptionPolicyIntegrationTest.userSubscriptionPolicyService;
    
    @Test
    public void UserSubscriptionPolicyIntegrationTest.testCountAllUserSubscriptionPolicys() {
        Assert.assertNotNull("Data on demand for 'UserSubscriptionPolicy' failed to initialize correctly", dod.getRandomUserSubscriptionPolicy());
        long count = userSubscriptionPolicyService.countAllUserSubscriptionPolicys();
        Assert.assertTrue("Counter for 'UserSubscriptionPolicy' incorrectly reported there were no entries", count > 0);
    }
    
    @Test
    public void UserSubscriptionPolicyIntegrationTest.testFindUserSubscriptionPolicy() {
        UserSubscriptionPolicy obj = dod.getRandomUserSubscriptionPolicy();
        Assert.assertNotNull("Data on demand for 'UserSubscriptionPolicy' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'UserSubscriptionPolicy' failed to provide an identifier", id);
        obj = userSubscriptionPolicyService.findUserSubscriptionPolicy(id);
        Assert.assertNotNull("Find method for 'UserSubscriptionPolicy' illegally returned null for id '" + id + "'", obj);
        Assert.assertEquals("Find method for 'UserSubscriptionPolicy' returned the incorrect identifier", id, obj.getId());
    }
    
    @Test
    public void UserSubscriptionPolicyIntegrationTest.testFindAllUserSubscriptionPolicys() {
        Assert.assertNotNull("Data on demand for 'UserSubscriptionPolicy' failed to initialize correctly", dod.getRandomUserSubscriptionPolicy());
        long count = userSubscriptionPolicyService.countAllUserSubscriptionPolicys();
        Assert.assertTrue("Too expensive to perform a find all test for 'UserSubscriptionPolicy', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        List<UserSubscriptionPolicy> result = userSubscriptionPolicyService.findAllUserSubscriptionPolicys();
        Assert.assertNotNull("Find all method for 'UserSubscriptionPolicy' illegally returned null", result);
        Assert.assertTrue("Find all method for 'UserSubscriptionPolicy' failed to return any data", result.size() > 0);
    }
    
    @Test
    public void UserSubscriptionPolicyIntegrationTest.testFindUserSubscriptionPolicyEntries() {
        Assert.assertNotNull("Data on demand for 'UserSubscriptionPolicy' failed to initialize correctly", dod.getRandomUserSubscriptionPolicy());
        long count = userSubscriptionPolicyService.countAllUserSubscriptionPolicys();
        if (count > 20) count = 20;
        int firstResult = 0;
        int maxResults = (int) count;
        List<UserSubscriptionPolicy> result = userSubscriptionPolicyService.findUserSubscriptionPolicyEntries(firstResult, maxResults);
        Assert.assertNotNull("Find entries method for 'UserSubscriptionPolicy' illegally returned null", result);
        Assert.assertEquals("Find entries method for 'UserSubscriptionPolicy' returned an incorrect number of entries", count, result.size());
    }
    
    @Test
    public void UserSubscriptionPolicyIntegrationTest.testFlush() {
        UserSubscriptionPolicy obj = dod.getRandomUserSubscriptionPolicy();
        Assert.assertNotNull("Data on demand for 'UserSubscriptionPolicy' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'UserSubscriptionPolicy' failed to provide an identifier", id);
        obj = userSubscriptionPolicyService.findUserSubscriptionPolicy(id);
        Assert.assertNotNull("Find method for 'UserSubscriptionPolicy' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyUserSubscriptionPolicy(obj);
        Integer currentVersion = obj.getVersion();
        obj.flush();
        Assert.assertTrue("Version for 'UserSubscriptionPolicy' failed to increment on flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }
    
    @Test
    public void UserSubscriptionPolicyIntegrationTest.testUpdateUserSubscriptionPolicyUpdate() {
        UserSubscriptionPolicy obj = dod.getRandomUserSubscriptionPolicy();
        Assert.assertNotNull("Data on demand for 'UserSubscriptionPolicy' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'UserSubscriptionPolicy' failed to provide an identifier", id);
        obj = userSubscriptionPolicyService.findUserSubscriptionPolicy(id);
        boolean modified =  dod.modifyUserSubscriptionPolicy(obj);
        Integer currentVersion = obj.getVersion();
        UserSubscriptionPolicy merged = userSubscriptionPolicyService.updateUserSubscriptionPolicy(obj);
        obj.flush();
        Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId(), id);
        Assert.assertTrue("Version for 'UserSubscriptionPolicy' failed to increment on merge and flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }
    
    @Test
    public void UserSubscriptionPolicyIntegrationTest.testSaveUserSubscriptionPolicy() {
        Assert.assertNotNull("Data on demand for 'UserSubscriptionPolicy' failed to initialize correctly", dod.getRandomUserSubscriptionPolicy());
        UserSubscriptionPolicy obj = dod.getNewTransientUserSubscriptionPolicy(Integer.MAX_VALUE);
        Assert.assertNotNull("Data on demand for 'UserSubscriptionPolicy' failed to provide a new transient entity", obj);
        Assert.assertNull("Expected 'UserSubscriptionPolicy' identifier to be null", obj.getId());
        userSubscriptionPolicyService.saveUserSubscriptionPolicy(obj);
        obj.flush();
        Assert.assertNotNull("Expected 'UserSubscriptionPolicy' identifier to no longer be null", obj.getId());
    }
    
    @Test
    public void UserSubscriptionPolicyIntegrationTest.testDeleteUserSubscriptionPolicy() {
        UserSubscriptionPolicy obj = dod.getRandomUserSubscriptionPolicy();
        Assert.assertNotNull("Data on demand for 'UserSubscriptionPolicy' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'UserSubscriptionPolicy' failed to provide an identifier", id);
        obj = userSubscriptionPolicyService.findUserSubscriptionPolicy(id);
        userSubscriptionPolicyService.deleteUserSubscriptionPolicy(obj);
        obj.flush();
        Assert.assertNull("Failed to remove 'UserSubscriptionPolicy' with identifier '" + id + "'", userSubscriptionPolicyService.findUserSubscriptionPolicy(id));
    }
    
}
