// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.sjsu.bikelet.web;

import com.sjsu.bikelet.domain.Tenant;
import com.sjsu.bikelet.service.TenantService;
import com.sjsu.bikelet.web.TenantController;
import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

privileged aspect TenantController_Roo_Controller {
    
    @Autowired
    TenantService TenantController.tenantService;
    


    
    @RequestMapping(value = "/{id}", produces = "text/html")
    public String TenantController.show(@PathVariable("id") Long id, Model uiModel) {
        uiModel.addAttribute("tenant", tenantService.findTenant(id));
        uiModel.addAttribute("itemId", id);
        return "tenants/show";
    }
    

    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String TenantController.update(@Valid Tenant tenant, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, tenant);
            return "tenants/update";
        }
        uiModel.asMap().clear();
        tenantService.updateTenant(tenant);
        return "redirect:/tenants/" + encodeUrlPathSegment(tenant.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String TenantController.updateForm(@PathVariable("id") Long id, Model uiModel) {
        populateEditForm(uiModel, tenantService.findTenant(id));
        return "tenants/update";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String TenantController.delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        Tenant tenant = tenantService.findTenant(id);
        tenantService.deleteTenant(tenant);
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/tenants";
    }
    

    
    String TenantController.encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
        String enc = httpServletRequest.getCharacterEncoding();
        if (enc == null) {
            enc = WebUtils.DEFAULT_CHARACTER_ENCODING;
        }
        try {
            pathSegment = UriUtils.encodePathSegment(pathSegment, enc);
        } catch (UnsupportedEncodingException uee) {}
        return pathSegment;
    }
    
}
