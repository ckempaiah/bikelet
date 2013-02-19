package com.sjsu.bikelet.web;

import com.sjsu.bikelet.domain.BikeLetRole;
import com.sjsu.bikelet.domain.BikeLetUser;
import com.sjsu.bikelet.domain.Tenant;
import com.sjsu.bikelet.domain.UserRole;
import com.sjsu.bikelet.service.BikeLetUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/tenants")
@Controller
@RooWebScaffold(path = "tenants", formBackingObject = Tenant.class)
public class TenantController {

    @Autowired
    private BikeLetUserService bikeLetUserService;

    @RequestMapping(params = "form", produces = "text/html")
    public String createForm(Model uiModel) {
        populateEditForm(uiModel, new Tenant());
        return "tenants/create";
    }

    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String create(@Valid Tenant tenant, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, tenant);
            return "tenants/create";
        }
        uiModel.asMap().clear();
        tenantService.saveTenant(tenant);
        return "redirect:/tenants/" + encodeUrlPathSegment(tenant.getId().toString(), httpServletRequest);
    }

    @RequestMapping(value = "/{id}/tenantadmin", method = RequestMethod.POST, produces = "text/html")
    public String createTenantAdmin(@Valid BikeLetUser bikeLetUser,
                             BindingResult bindingResult, Model uiModel,
                             HttpServletRequest httpServletRequest, @PathVariable("id") Long tenantId) {

        if (bindingResult.hasErrors() ) {
            populateTenantAdminEditForm(uiModel, bikeLetUser, tenantId);
            return "tenants/tenantadmin/create";
        }
        if (bikeLetUserService.isDuplicateName(bikeLetUser.getUserName(), bikeLetUser.getId())){
            bindingResult.addError(new ObjectError("bikeLetUser.userName", "User name must be Unique"));
            populateTenantAdminEditForm(uiModel, bikeLetUser, tenantId);
            return "tenants/tenantadmin/create";
        }
        bikeLetUser.setTenantId(tenantService.findTenant(tenantId));
        uiModel.asMap().clear();
        bikeLetUserService.createTenantAdminUser(bikeLetUser);
        return "redirect:/tenants/" +tenantId +  "/tenantadmin/" + encodeUrlPathSegment(bikeLetUser.getId().toString(), httpServletRequest);
    }
    @RequestMapping(value = "/{id}/tenantadmin", params = "form", produces = "text/html")
    public String createTenantAdminForm(@PathVariable("id") Long tenantId, Model uiModel) {
        BikeLetUser user = new BikeLetUser();
        populateTenantAdminEditForm(uiModel, user, tenantId);
        return "tenants/tenantadmin/create";
    }

    @RequestMapping(value = "/{id}/tenantadmin/{userId}", produces = "text/html")
    public String showTenantAdmin(@PathVariable("userId") Long userId, @PathVariable("id") Long tenantId, Model uiModel) {
        uiModel.addAttribute("bikeletuser", bikeLetUserService.findBikeLetUser(userId));
        uiModel.addAttribute("tenant", tenantService.findTenant(tenantId));
        uiModel.addAttribute("itemId", userId);
        return "tenants/tenantadmin/show";
    }
    void populateTenantAdminEditForm(Model uiModel, BikeLetUser bikeLetUser, Long tenantId) {
        Tenant tenant = tenantService.findTenant(tenantId);
        uiModel.addAttribute("tenant", tenantService.findTenant(tenantId));
        bikeLetUser.setTenantId(tenant);
        uiModel.addAttribute("bikeLetUser", bikeLetUser);
    }

    void populateEditForm(Model uiModel, Tenant tenant) {
        uiModel.addAttribute("tenant", tenant);
    }

    void populateUserForm(Model uiModle, BikeLetUser bikeLetUser){
        uiModle.addAttribute("bikeLetUser", bikeLetUser);
    }

    @RequestMapping(produces = "text/html")
    public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        List<Tenant> tenantList = null;
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            tenantList=tenantService.findTenantEntries(firstResult, sizeNo);
            populateTenantAdmin(tenantList);
            uiModel.addAttribute("tenants", tenantList);
            float nrOfPages = (float) tenantService.countAllTenants() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            tenantList = tenantService.findAllTenants();
            populateTenantAdmin(tenantList);
            uiModel.addAttribute("tenants", tenantList);
        }
        return "tenants/list";
    }

    private void populateTenantAdmin(List<Tenant> tenantList){

        for (Tenant tenant: tenantList){
            BikeLetUser tenantAdmin = bikeLetUserService.getTenantAdmin(tenant.getId());
            tenant.setTenantAdmin(tenantAdmin);
        }

    }

    @RequestMapping(value = "/{id}/tenantadmin/{userId}", method = RequestMethod.PUT, produces = "text/html")
    public String updateTenantAdmin(@PathVariable("id")Long tenantId, @Valid BikeLetUser bikeLetUser, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateTenantAdminEditForm(uiModel, bikeLetUser, tenantId);
            return "tenants/tenantadmin/update";
        }
        uiModel.asMap().clear();
        bikeLetUser.setTenantId(tenantService.findTenant(tenantId));
        bikeLetUserService.updateBikeLetUser(bikeLetUser);
        return "redirect:/tenants/" +tenantId +  "/tenantadmin/" + encodeUrlPathSegment(bikeLetUser.getId().toString(), httpServletRequest);
    }

    @RequestMapping(value = "/{id}/tenantadmin/{userId}", params = "form", produces = "text/html")
    public String updateTenantAdminForm(@PathVariable("id") Long tenantId, Model uiModel, @PathVariable("userId") Long userId) {
        populateTenantAdminEditForm(uiModel, bikeLetUserService.findBikeLetUser(userId), tenantId);
        return "tenants/tenantadmin/update";
    }
}

