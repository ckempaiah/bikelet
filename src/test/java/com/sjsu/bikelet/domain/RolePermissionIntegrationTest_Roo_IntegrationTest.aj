// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.sjsu.bikelet.domain;

import com.sjsu.bikelet.domain.RolePermissionDataOnDemand;
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

privileged aspect RolePermissionIntegrationTest_Roo_IntegrationTest {
    
    declare @type: RolePermissionIntegrationTest: @RunWith(SpringJUnit4ClassRunner.class);
    
    declare @type: RolePermissionIntegrationTest: @ContextConfiguration(locations = "classpath:/META-INF/spring/applicationContext*.xml");
    
    declare @type: RolePermissionIntegrationTest: @Transactional;
    
    @Autowired
    private RolePermissionDataOnDemand RolePermissionIntegrationTest.dod;
    
    @Test
    public void RolePermissionIntegrationTest.testCountRolePermissions() {
        Assert.assertNotNull("Data on demand for 'RolePermission' failed to initialize correctly", dod.getRandomRolePermission());
        long count = RolePermission.countRolePermissions();
        Assert.assertTrue("Counter for 'RolePermission' incorrectly reported there were no entries", count > 0);
    }
    
    @Test
    public void RolePermissionIntegrationTest.testFindRolePermission() {
        RolePermission obj = dod.getRandomRolePermission();
        Assert.assertNotNull("Data on demand for 'RolePermission' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'RolePermission' failed to provide an identifier", id);
        obj = RolePermission.findRolePermission(id);
        Assert.assertNotNull("Find method for 'RolePermission' illegally returned null for id '" + id + "'", obj);
        Assert.assertEquals("Find method for 'RolePermission' returned the incorrect identifier", id, obj.getId());
    }
    
    @Test
    public void RolePermissionIntegrationTest.testFindAllRolePermissions() {
        Assert.assertNotNull("Data on demand for 'RolePermission' failed to initialize correctly", dod.getRandomRolePermission());
        long count = RolePermission.countRolePermissions();
        Assert.assertTrue("Too expensive to perform a find all test for 'RolePermission', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        List<RolePermission> result = RolePermission.findAllRolePermissions();
        Assert.assertNotNull("Find all method for 'RolePermission' illegally returned null", result);
        Assert.assertTrue("Find all method for 'RolePermission' failed to return any data", result.size() > 0);
    }
    
    @Test
    public void RolePermissionIntegrationTest.testFindRolePermissionEntries() {
        Assert.assertNotNull("Data on demand for 'RolePermission' failed to initialize correctly", dod.getRandomRolePermission());
        long count = RolePermission.countRolePermissions();
        if (count > 20) count = 20;
        int firstResult = 0;
        int maxResults = (int) count;
        List<RolePermission> result = RolePermission.findRolePermissionEntries(firstResult, maxResults);
        Assert.assertNotNull("Find entries method for 'RolePermission' illegally returned null", result);
        Assert.assertEquals("Find entries method for 'RolePermission' returned an incorrect number of entries", count, result.size());
    }
    
    @Test
    public void RolePermissionIntegrationTest.testFlush() {
        RolePermission obj = dod.getRandomRolePermission();
        Assert.assertNotNull("Data on demand for 'RolePermission' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'RolePermission' failed to provide an identifier", id);
        obj = RolePermission.findRolePermission(id);
        Assert.assertNotNull("Find method for 'RolePermission' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyRolePermission(obj);
        Integer currentVersion = obj.getVersion();
        obj.flush();
        Assert.assertTrue("Version for 'RolePermission' failed to increment on flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }
    
    @Test
    public void RolePermissionIntegrationTest.testMergeUpdate() {
        RolePermission obj = dod.getRandomRolePermission();
        Assert.assertNotNull("Data on demand for 'RolePermission' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'RolePermission' failed to provide an identifier", id);
        obj = RolePermission.findRolePermission(id);
        boolean modified =  dod.modifyRolePermission(obj);
        Integer currentVersion = obj.getVersion();
        RolePermission merged = obj.merge();
        obj.flush();
        Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId(), id);
        Assert.assertTrue("Version for 'RolePermission' failed to increment on merge and flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }
    
    @Test
    public void RolePermissionIntegrationTest.testPersist() {
        Assert.assertNotNull("Data on demand for 'RolePermission' failed to initialize correctly", dod.getRandomRolePermission());
        RolePermission obj = dod.getNewTransientRolePermission(Integer.MAX_VALUE);
        Assert.assertNotNull("Data on demand for 'RolePermission' failed to provide a new transient entity", obj);
        Assert.assertNull("Expected 'RolePermission' identifier to be null", obj.getId());
        obj.persist();
        obj.flush();
        Assert.assertNotNull("Expected 'RolePermission' identifier to no longer be null", obj.getId());
    }
    
    @Test
    public void RolePermissionIntegrationTest.testRemove() {
        RolePermission obj = dod.getRandomRolePermission();
        Assert.assertNotNull("Data on demand for 'RolePermission' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'RolePermission' failed to provide an identifier", id);
        obj = RolePermission.findRolePermission(id);
        obj.remove();
        obj.flush();
        Assert.assertNull("Failed to remove 'RolePermission' with identifier '" + id + "'", RolePermission.findRolePermission(id));
    }
    
}
