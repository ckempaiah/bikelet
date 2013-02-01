// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.sjsu.bikelet.domain;

import com.sjsu.bikelet.domain.SubscriptionRateDataOnDemand;
import java.lang.Integer;
import java.lang.Long;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

privileged aspect SubscriptionRateIntegrationTest_Roo_IntegrationTest {
    
    declare @type: SubscriptionRateIntegrationTest: @RunWith(SpringJUnit4ClassRunner.class);
    
    declare @type: SubscriptionRateIntegrationTest: @ContextConfiguration(locations = "classpath:/META-INF/spring/applicationContext*.xml");
    
    declare @type: SubscriptionRateIntegrationTest: @Transactional;
    
    @Autowired
    private SubscriptionRateDataOnDemand SubscriptionRateIntegrationTest.dod;
    
    @Test
    public void SubscriptionRateIntegrationTest.testCountSubscriptionRates() {
        Assert.assertNotNull("Data on demand for 'SubscriptionRate' failed to initialize correctly", dod.getRandomSubscriptionRate());
        long count = SubscriptionRate.countSubscriptionRates();
        Assert.assertTrue("Counter for 'SubscriptionRate' incorrectly reported there were no entries", count > 0);
    }
    
    @Test
    public void SubscriptionRateIntegrationTest.testFindSubscriptionRate() {
        SubscriptionRate obj = dod.getRandomSubscriptionRate();
        Assert.assertNotNull("Data on demand for 'SubscriptionRate' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'SubscriptionRate' failed to provide an identifier", id);
        obj = SubscriptionRate.findSubscriptionRate(id);
        Assert.assertNotNull("Find method for 'SubscriptionRate' illegally returned null for id '" + id + "'", obj);
        Assert.assertEquals("Find method for 'SubscriptionRate' returned the incorrect identifier", id, obj.getId());
    }
    
    @Test
    public void SubscriptionRateIntegrationTest.testFindAllSubscriptionRates() {
        Assert.assertNotNull("Data on demand for 'SubscriptionRate' failed to initialize correctly", dod.getRandomSubscriptionRate());
        long count = SubscriptionRate.countSubscriptionRates();
        Assert.assertTrue("Too expensive to perform a find all test for 'SubscriptionRate', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        List<SubscriptionRate> result = SubscriptionRate.findAllSubscriptionRates();
        Assert.assertNotNull("Find all method for 'SubscriptionRate' illegally returned null", result);
        Assert.assertTrue("Find all method for 'SubscriptionRate' failed to return any data", result.size() > 0);
    }
    
    @Test
    public void SubscriptionRateIntegrationTest.testFindSubscriptionRateEntries() {
        Assert.assertNotNull("Data on demand for 'SubscriptionRate' failed to initialize correctly", dod.getRandomSubscriptionRate());
        long count = SubscriptionRate.countSubscriptionRates();
        if (count > 20) count = 20;
        int firstResult = 0;
        int maxResults = (int) count;
        List<SubscriptionRate> result = SubscriptionRate.findSubscriptionRateEntries(firstResult, maxResults);
        Assert.assertNotNull("Find entries method for 'SubscriptionRate' illegally returned null", result);
        Assert.assertEquals("Find entries method for 'SubscriptionRate' returned an incorrect number of entries", count, result.size());
    }
    
    @Test
    public void SubscriptionRateIntegrationTest.testFlush() {
        SubscriptionRate obj = dod.getRandomSubscriptionRate();
        Assert.assertNotNull("Data on demand for 'SubscriptionRate' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'SubscriptionRate' failed to provide an identifier", id);
        obj = SubscriptionRate.findSubscriptionRate(id);
        Assert.assertNotNull("Find method for 'SubscriptionRate' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifySubscriptionRate(obj);
        Integer currentVersion = obj.getVersion();
        obj.flush();
        Assert.assertTrue("Version for 'SubscriptionRate' failed to increment on flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }
    
    @Test
    public void SubscriptionRateIntegrationTest.testMergeUpdate() {
        SubscriptionRate obj = dod.getRandomSubscriptionRate();
        Assert.assertNotNull("Data on demand for 'SubscriptionRate' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'SubscriptionRate' failed to provide an identifier", id);
        obj = SubscriptionRate.findSubscriptionRate(id);
        boolean modified =  dod.modifySubscriptionRate(obj);
        Integer currentVersion = obj.getVersion();
        SubscriptionRate merged = obj.merge();
        obj.flush();
        Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId(), id);
        Assert.assertTrue("Version for 'SubscriptionRate' failed to increment on merge and flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }
    
    @Test
    public void SubscriptionRateIntegrationTest.testPersist() {
        Assert.assertNotNull("Data on demand for 'SubscriptionRate' failed to initialize correctly", dod.getRandomSubscriptionRate());
        SubscriptionRate obj = dod.getNewTransientSubscriptionRate(Integer.MAX_VALUE);
        Assert.assertNotNull("Data on demand for 'SubscriptionRate' failed to provide a new transient entity", obj);
        Assert.assertNull("Expected 'SubscriptionRate' identifier to be null", obj.getId());
        obj.persist();
        obj.flush();
        Assert.assertNotNull("Expected 'SubscriptionRate' identifier to no longer be null", obj.getId());
    }
    
    @Test
    public void SubscriptionRateIntegrationTest.testRemove() {
        SubscriptionRate obj = dod.getRandomSubscriptionRate();
        Assert.assertNotNull("Data on demand for 'SubscriptionRate' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'SubscriptionRate' failed to provide an identifier", id);
        obj = SubscriptionRate.findSubscriptionRate(id);
        obj.remove();
        obj.flush();
        Assert.assertNull("Failed to remove 'SubscriptionRate' with identifier '" + id + "'", SubscriptionRate.findSubscriptionRate(id));
    }
    
}
