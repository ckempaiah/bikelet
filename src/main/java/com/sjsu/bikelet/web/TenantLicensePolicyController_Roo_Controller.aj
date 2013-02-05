// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.sjsu.bikelet.web;

import com.sjsu.bikelet.domain.LicensePolicy;
import com.sjsu.bikelet.domain.Tenant;
import com.sjsu.bikelet.domain.TenantLicensePolicy;
import com.sjsu.bikelet.web.TenantLicensePolicyController;
import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.joda.time.format.DateTimeFormat;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

privileged aspect TenantLicensePolicyController_Roo_Controller {
    
    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String TenantLicensePolicyController.create(@Valid TenantLicensePolicy tenantLicensePolicy, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, tenantLicensePolicy);
            return "tenantlicensepolicys/create";
        }
        uiModel.asMap().clear();
        tenantLicensePolicy.persist();
        return "redirect:/tenantlicensepolicys/" + encodeUrlPathSegment(tenantLicensePolicy.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(params = "form", produces = "text/html")
    public String TenantLicensePolicyController.createForm(Model uiModel) {
        populateEditForm(uiModel, new TenantLicensePolicy());
        return "tenantlicensepolicys/create";
    }
    
    @RequestMapping(value = "/{id}", produces = "text/html")
    public String TenantLicensePolicyController.show(@PathVariable("id") Long id, Model uiModel) {
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("tenantlicensepolicy", TenantLicensePolicy.findTenantLicensePolicy(id));
        uiModel.addAttribute("itemId", id);
        return "tenantlicensepolicys/show";
    }
    
    @RequestMapping(produces = "text/html")
    public String TenantLicensePolicyController.list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("tenantlicensepolicys", TenantLicensePolicy.findTenantLicensePolicyEntries(firstResult, sizeNo));
            float nrOfPages = (float) TenantLicensePolicy.countTenantLicensePolicys() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("tenantlicensepolicys", TenantLicensePolicy.findAllTenantLicensePolicys());
        }
        addDateTimeFormatPatterns(uiModel);
        return "tenantlicensepolicys/list";
    }
    
    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String TenantLicensePolicyController.update(@Valid TenantLicensePolicy tenantLicensePolicy, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, tenantLicensePolicy);
            return "tenantlicensepolicys/update";
        }
        uiModel.asMap().clear();
        tenantLicensePolicy.merge();
        return "redirect:/tenantlicensepolicys/" + encodeUrlPathSegment(tenantLicensePolicy.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String TenantLicensePolicyController.updateForm(@PathVariable("id") Long id, Model uiModel) {
        populateEditForm(uiModel, TenantLicensePolicy.findTenantLicensePolicy(id));
        return "tenantlicensepolicys/update";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String TenantLicensePolicyController.delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        TenantLicensePolicy tenantLicensePolicy = TenantLicensePolicy.findTenantLicensePolicy(id);
        tenantLicensePolicy.remove();
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/tenantlicensepolicys";
    }
    
    void TenantLicensePolicyController.addDateTimeFormatPatterns(Model uiModel) {
        uiModel.addAttribute("tenantLicensePolicy_licensestartdate_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
        uiModel.addAttribute("tenantLicensePolicy_licenseenddate_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
    }
    
    void TenantLicensePolicyController.populateEditForm(Model uiModel, TenantLicensePolicy tenantLicensePolicy) {
        uiModel.addAttribute("tenantLicensePolicy", tenantLicensePolicy);
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("licensepolicys", LicensePolicy.findAllLicensePolicys());
        uiModel.addAttribute("tenants", Tenant.findAllTenants());
    }
    
    String TenantLicensePolicyController.encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
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
