package com.sjsu.bikelet.web;

import com.sjsu.bikelet.domain.SubscriptionPolicy;
import com.sjsu.bikelet.service.ProgramService;
import com.sjsu.bikelet.service.SubscriptionPolicyService;
import com.sjsu.bikelet.service.TenantService;
import com.sjsu.bikelet.domain.Program;
import com.sjsu.bikelet.domain.BikeLetUser;
import com.sjsu.bikelet.domain.Tenant;
import com.sjsu.bikelet.service.BikeLetUserService;
import com.sjsu.bikelet.web.ProgramController;
import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import java.util.ArrayList;
import java.lang.Long;

@RequestMapping("/subscriptionpolicys")
@Controller
@RooWebScaffold(path = "subscriptionpolicys", formBackingObject = SubscriptionPolicy.class)
public class SubscriptionPolicyController {
	
//	@Autowired
//	ProgramService programService;
	
	@Autowired
	TenantService tenantService;
	
//	@Autowired
//	SubscriptionPolicyService subscriptionPolicyService;
	
	 @RequestMapping(method = RequestMethod.POST, produces = "text/html")
	    public String create(@Valid SubscriptionPolicy subscriptionPolicy, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
	        if (bindingResult.hasErrors()) {
	            populateEditForm(uiModel, subscriptionPolicy);
	            return "subscriptionpolicys/create";
	        }
	        uiModel.asMap().clear();
	        subscriptionPolicyService.saveSubscriptionPolicy(subscriptionPolicy);
	        return "redirect:/subscriptionrates?form";
	    }
	    
	
	 @RequestMapping(value = "/{id}/subscriptionpolicys", method = RequestMethod.POST, produces = "text/html")
		public String createPolicy(@Valid SubscriptionPolicy policy,
				BindingResult bindingResult, Model uiModel,
				HttpServletRequest httpServletRequest) {
			if (bindingResult.hasErrors()) {
				populateEditUserForm(uiModel, policy);
				return "programs/" + policy.getProgramId().getId() +  "/subscriptionpolicys/create";
			}
			uiModel.asMap().clear();
			policy.setProgramId(programService.findProgram(policy.getProgramId().getId()));
//			policy.setTenantId(tenantService.findTenant(policy.getTenantId().getId()));	
			subscriptionPolicyService.saveSubscriptionPolicy(policy);
			return "redirect:/programs/" + policy.getProgramId().getId() +  "/subscriptionpolicys";
		}
	 
	 void populateEditUserForm(Model uiModel, SubscriptionPolicy policy) {
			uiModel.addAttribute("policy", policy);
			uiModel.addAttribute("programId", policy.getProgramId().getId());
			//uiModel.addAttribute("programs", policy.findAllPrograms());
			//uiModel.addAttribute("tenants", tenantService.findAllTenants());
		}
}
