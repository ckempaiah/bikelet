package com.sjsu.bikelet.web;

import com.sjsu.bikelet.domain.TenantLicensePolicy;
import com.sjsu.bikelet.exception.BikeletValidationException;
import com.sjsu.bikelet.service.LicensePolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RequestMapping("/tenantlicensepolicys")
@Controller
@RooWebScaffold(path = "tenantlicensepolicys", formBackingObject = TenantLicensePolicy.class)
public class TenantLicensePolicyController {


    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String create(@Valid TenantLicensePolicy tenantLicensePolicy, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        validateDate(bindingResult, tenantLicensePolicy);

        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, tenantLicensePolicy);
            return "tenantlicensepolicys/create";
        }
        uiModel.asMap().clear();

        try {
            tenantLicensePolicyService.saveTenantLicensePolicy(tenantLicensePolicy);
        } catch (BikeletValidationException e) {
            bindingResult.addError(new ObjectError("tenantLicensePolicy", e.getMessage()));
            if (bindingResult.hasErrors()) {
                populateEditForm(uiModel, tenantLicensePolicy);
                return "tenantlicensepolicys/create";
            }
        }

        return "redirect:/tenantlicensepolicys/" + encodeUrlPathSegment(tenantLicensePolicy.getId().toString(), httpServletRequest);
    }

    private void validateDate(BindingResult bindingResult, TenantLicensePolicy tenantLicensePolicy){

        if (tenantLicensePolicy.getLicenseEndDate() == null || !tenantLicensePolicy.getLicenseEndDate().after(tenantLicensePolicy.getLicenseStartDate())) {
            bindingResult.addError(new ObjectError("tenantLicensePolicy", "License Start Date must be before license End Date"));
        } else if(! CollectionUtils.isEmpty(tenantLicensePolicyService.verifyLicensePolicyDates(tenantLicensePolicy))) {
            bindingResult.addError(new ObjectError("tenantLicensePolicy", "New license start date and end date must supersede existing ones"));
        }

    }
}
