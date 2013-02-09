// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.sjsu.bikelet.domain;

import com.sjsu.bikelet.domain.UserRoleDataOnDemand;
import com.sjsu.bikelet.domain.UserRoleIntegrationTest;
import com.sjsu.bikelet.service.UserRoleService;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

privileged aspect UserRoleIntegrationTest_Roo_IntegrationTest {
    
    declare @type: UserRoleIntegrationTest: @RunWith(SpringJUnit4ClassRunner.class);
    
    declare @type: UserRoleIntegrationTest: @ContextConfiguration(locations = "classpath:/META-INF/spring/applicationContext*.xml");
    
    declare @type: UserRoleIntegrationTest: @Transactional;
    
    @Autowired
    UserRoleDataOnDemand UserRoleIntegrationTest.dod;
    
    @Autowired
    UserRoleService UserRoleIntegrationTest.userRoleService;
    
    @Test
    public void UserRoleIntegrationTest.testCountAllUserRoles() {
        Assert.assertNotNull("Data on demand for 'UserRole' failed to initialize correctly", dod.getRandomUserRole());
        long count = userRoleService.countAllUserRoles();
        Assert.assertTrue("Counter for 'UserRole' incorrectly reported there were no entries", count > 0);
    }
    
    @Test
    public void UserRoleIntegrationTest.testFindUserRole() {
        UserRole obj = dod.getRandomUserRole();
        Assert.assertNotNull("Data on demand for 'UserRole' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'UserRole' failed to provide an identifier", id);
        obj = userRoleService.findUserRole(id);
        Assert.assertNotNull("Find method for 'UserRole' illegally returned null for id '" + id + "'", obj);
        Assert.assertEquals("Find method for 'UserRole' returned the incorrect identifier", id, obj.getId());
    }
    
    @Test
    public void UserRoleIntegrationTest.testFindAllUserRoles() {
        Assert.assertNotNull("Data on demand for 'UserRole' failed to initialize correctly", dod.getRandomUserRole());
        long count = userRoleService.countAllUserRoles();
        Assert.assertTrue("Too expensive to perform a find all test for 'UserRole', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        List<UserRole> result = userRoleService.findAllUserRoles();
        Assert.assertNotNull("Find all method for 'UserRole' illegally returned null", result);
        Assert.assertTrue("Find all method for 'UserRole' failed to return any data", result.size() > 0);
    }
    
    @Test
    public void UserRoleIntegrationTest.testFindUserRoleEntries() {
        Assert.assertNotNull("Data on demand for 'UserRole' failed to initialize correctly", dod.getRandomUserRole());
        long count = userRoleService.countAllUserRoles();
        if (count > 20) count = 20;
        int firstResult = 0;
        int maxResults = (int) count;
        List<UserRole> result = userRoleService.findUserRoleEntries(firstResult, maxResults);
        Assert.assertNotNull("Find entries method for 'UserRole' illegally returned null", result);
        Assert.assertEquals("Find entries method for 'UserRole' returned an incorrect number of entries", count, result.size());
    }
    
    @Test
    public void UserRoleIntegrationTest.testFlush() {
        UserRole obj = dod.getRandomUserRole();
        Assert.assertNotNull("Data on demand for 'UserRole' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'UserRole' failed to provide an identifier", id);
        obj = userRoleService.findUserRole(id);
        Assert.assertNotNull("Find method for 'UserRole' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyUserRole(obj);
        Integer currentVersion = obj.getVersion();
        obj.flush();
        Assert.assertTrue("Version for 'UserRole' failed to increment on flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }
    
    @Test
    public void UserRoleIntegrationTest.testUpdateUserRoleUpdate() {
        UserRole obj = dod.getRandomUserRole();
        Assert.assertNotNull("Data on demand for 'UserRole' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'UserRole' failed to provide an identifier", id);
        obj = userRoleService.findUserRole(id);
        boolean modified =  dod.modifyUserRole(obj);
        Integer currentVersion = obj.getVersion();
        UserRole merged = userRoleService.updateUserRole(obj);
        obj.flush();
        Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId(), id);
        Assert.assertTrue("Version for 'UserRole' failed to increment on merge and flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }
    
    @Test
    public void UserRoleIntegrationTest.testSaveUserRole() {
        Assert.assertNotNull("Data on demand for 'UserRole' failed to initialize correctly", dod.getRandomUserRole());
        UserRole obj = dod.getNewTransientUserRole(Integer.MAX_VALUE);
        Assert.assertNotNull("Data on demand for 'UserRole' failed to provide a new transient entity", obj);
        Assert.assertNull("Expected 'UserRole' identifier to be null", obj.getId());
        userRoleService.saveUserRole(obj);
        obj.flush();
        Assert.assertNotNull("Expected 'UserRole' identifier to no longer be null", obj.getId());
    }
    
    @Test
    public void UserRoleIntegrationTest.testDeleteUserRole() {
        UserRole obj = dod.getRandomUserRole();
        Assert.assertNotNull("Data on demand for 'UserRole' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'UserRole' failed to provide an identifier", id);
        obj = userRoleService.findUserRole(id);
        userRoleService.deleteUserRole(obj);
        obj.flush();
        Assert.assertNull("Failed to remove 'UserRole' with identifier '" + id + "'", userRoleService.findUserRole(id));
    }
    
}
