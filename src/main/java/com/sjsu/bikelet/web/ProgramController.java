package com.sjsu.bikelet.web;

import com.sjsu.bikelet.domain.Program;
import com.sjsu.bikelet.domain.BikeLetUser;
import com.sjsu.bikelet.domain.SubscriptionPolicy;
import com.sjsu.bikelet.domain.SubscriptionRate;
import com.sjsu.bikelet.domain.UserRole;
import com.sjsu.bikelet.domain.BikeLetRole;
import com.sjsu.bikelet.domain.Tenant;
import com.sjsu.bikelet.domain.UserSubscriptionPolicy;
import com.sjsu.bikelet.service.ProgramService;
import com.sjsu.bikelet.service.BikeLetUserService;
import com.sjsu.bikelet.service.SubscriptionPolicyService;
import com.sjsu.bikelet.service.SubscriptionRateService;
import com.sjsu.bikelet.service.TenantService;
import com.sjsu.bikelet.service.UserRoleService;
import com.sjsu.bikelet.service.BikeLetRoleService;
import com.sjsu.bikelet.service.UserSubscriptionPolicyService;
import com.sjsu.bikelet.web.ProgramController;
import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.validation.ObjectError;
import org.springframework.context.i18n.LocaleContextHolder;
import org.joda.time.format.DateTimeFormat;
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

@RequestMapping("/programs")
@Controller
@RooWebScaffold(path = "programs", formBackingObject = Program.class)
public class ProgramController {
	@Autowired
	SubscriptionPolicyService subscriptionPolicyService;
	
	@Autowired
	SubscriptionRateService subscriptionRateService;
	
	@Autowired
	UserSubscriptionPolicyService userSubscriptionPolicyService;
	
	@RequestMapping(method = RequestMethod.POST, produces = "text/html")
	public String create(@Valid Program program, BindingResult bindingResult,
			Model uiModel, HttpServletRequest httpServletRequest) {
		if (bindingResult.hasErrors()) {
			populateEditForm(uiModel, program);
			return "programs/create";
		}
		uiModel.asMap().clear();
		program.setTenantId(tenantService.findTenant(Utils.getLogonTenantId()));
		boolean check = program.getMin_threshold() >= program.getMax_threshold();
		if (check == true){
			bindingResult.addError(new ObjectError("program", "Min treshhold is greater than max threshhold"));
			if (bindingResult.hasErrors()) {
				populateEditForm(uiModel, program);
				return "programs/threshold";
			}
		}
		programService.saveProgram(program);
		return "redirect:/programs/"+program.getId().toString()
						+"/subscriptionpolicys";
	}
	
	@RequestMapping(value = "/{id}/bikeletusers", method = RequestMethod.POST, produces = "text/html")
	public String createUser(@PathVariable("id") Long programId, @Valid BikeLetUser bikeLetUser,
			BindingResult bindingResult, Model uiModel,
			HttpServletRequest httpServletRequest) {
		if (bindingResult.hasErrors()) {
        	Program program = programService.findProgram(programId);
    		bikeLetUser.setProgramId(program);
    		bikeLetUser.setTenantId(program.getTenantId());
    		
			populateEditUserForm(uiModel, bikeLetUser);
			return "programs/bikeletusers/create";
		}
		
        if (bikeLetUserService.isDuplicateName(bikeLetUser.getUserName(), bikeLetUser.getId())){
            bindingResult.addError(new ObjectError("bikeLetUser.userName", "User name must be Unique"));
            
            Program program = programService.findProgram(programId);
    		bikeLetUser.setProgramId(program);
    		bikeLetUser.setTenantId(program.getTenantId());

    		populateEditUserForm(uiModel, bikeLetUser);
    		return "programs/bikeletusers/create";
        }
		
		uiModel.asMap().clear();
		bikeLetUser.setProgramId(programService.findProgram(bikeLetUser.getProgramId().getId()));
		bikeLetUser.setTenantId(tenantService.findTenant(bikeLetUser.getTenantId().getId()));	
		bikeLetUserService.saveBikeLetUser(bikeLetUser);
		// Add the user role
		BikeLetUser user = bikeLetUserService.findBikeLetUser(bikeLetUser.getId());
		BikeLetRole role = bikeLetRoleService.findBikeLetRoleByName("ROLE_USER");
		UserRole userRole = new UserRole();
		userRole.setUserId(user);
		userRole.setRoleId(role);
		userRoleService.saveUserRole(userRole);
		// Add the user policy
		UserSubscriptionPolicy userSubscriptionPolicy = new UserSubscriptionPolicy();
		SubscriptionPolicy subscriptionPolicy = subscriptionPolicyService.findSubscriptionPolicy(bikeLetUser.getSubscriptionPolicy().getId());
		userSubscriptionPolicy.setPolicyId(subscriptionPolicy);
		userSubscriptionPolicy.setUserId(user);
		userSubscriptionPolicyService.saveUserSubscriptionPolicy(userSubscriptionPolicy);
		return "redirect:/programs/" + bikeLetUser.getProgramId().getId() +  "/bikeletusers";
	}
	
	@RequestMapping(value = "/{id}/subscriptionpolicys", method = RequestMethod.POST, produces = "text/html")
	public String createPolicy(@Valid SubscriptionPolicy subscriptionPolicy,
			BindingResult bindingResult, Model uiModel,
			HttpServletRequest httpServletRequest) {
		if (bindingResult.hasErrors()) {
			populateEditPolicyForm(uiModel, subscriptionPolicy);
			return "programs/subscriptionpolicys/create";
		}
		uiModel.asMap().clear();
		subscriptionPolicy.setProgramId(programService.findProgram(subscriptionPolicy.getProgramId().getId()));
		//subscriptionPolicy.setTenantId(tenantService.findTenant(bikeLetUser.getTenantId().getId()));	
		subscriptionPolicyService.saveSubscriptionPolicy(subscriptionPolicy);
		// Add the user role
		SubscriptionPolicy policy = subscriptionPolicyService.findSubscriptionPolicy(subscriptionPolicy.getId());
	    System.out.println("hello policy");
		return "redirect:/programs/" + subscriptionPolicy.getProgramId().getId().toString() +  "/subscriptionpolicys/" + subscriptionPolicy.getId().toString() + "/subscriptionrates";
	}
	
	@RequestMapping(value = "/{id}/subscriptionpolicys/{policyId}/subscriptionrates", method = RequestMethod.POST, produces = "text/html")
	public String createPolicyRate(@Valid SubscriptionRate subscriptionRate, @PathVariable("id") Long id, @PathVariable("policyId") Long policyId, BindingResult bindingResult, Model uiModel,
			HttpServletRequest httpServletRequest) {
		System.out.println("hello ");
		if (bindingResult.hasErrors()) {
			populateEditPolicyRateForm(uiModel, subscriptionRate, id);
			return "programs/subscriptionpolicys/subscriptionrates/create";
		}
		System.out.println("hello ");
		uiModel.asMap().clear();
		SubscriptionPolicy subsPolicy = subscriptionPolicyService.findSubscriptionPolicy(policyId);
		subscriptionRate.setPolicyId(subscriptionPolicyService.findSubscriptionPolicy(subscriptionRate.getPolicyId().getId()));
//		System.out.println("Program id "+id);
//		System.out.println("Start date"+subscriptionRate.getPolicyStartDate());
		boolean check = subscriptionRateService.checkOtherPolicyRates(subscriptionRate.getPolicyId().getId(), subscriptionRate);
		if (check == true){
			bindingResult.addError(new ObjectError("subscriptionRate", "Subscription Rate for the specified time period already exists"));
			if (bindingResult.hasErrors()) {
				populateEditPolicyRateForm(uiModel, subscriptionRate, id);
				return "programs/subscriptionpolicys/subscriptionrates/policydate";
			}
		}
		subscriptionRateService.saveSubscriptionRate(subscriptionRate);
		//SubscriptionRate policy = subscriptionPolicyService.findSubscriptionPolicy(subscriptionPolicy.getId());
		//return "redirect:/programs/" + id + "/subscriptionpolicys/" + policyId + "/subscriptionrates/" + subscriptionRate.getId().toString();
		return "redirect:/programs";
	}
	
	@RequestMapping(params = "form", produces = "text/html")
	public String createForm(Model uiModel) {
		Long tid = Utils.getLogonTenantId();
		Tenant tenant = tenantService.findTenant(tid);
		Program prog = new Program();
		prog.setTenantId(tenant);
		populateEditForm(uiModel, prog);
		return "programs/create";
	}

	@RequestMapping(value = "/{id}/bikeletusers", params = "form", produces = "text/html")
	public String createUserForm(@PathVariable("id") Long id, Model uiModel) {
		Program program = programService.findProgram(id);
		BikeLetUser bikeLetUser = new BikeLetUser();
		bikeLetUser.setProgramId(program);
		bikeLetUser.setTenantId(program.getTenantId());
		populateEditUserForm(uiModel, bikeLetUser);
		List<String[]> dependencies = new ArrayList<String[]>();
		if (tenantService.countAllTenants() == 0) {
			dependencies.add(new String[] { "tenant", "tenants" });
		}
		uiModel.addAttribute("dependencies", dependencies);
		return "programs/bikeletusers/create";
	}
	
	@RequestMapping(value = "/{id}/subscriptionpolicys", params = "form", produces = "text/html")
	public String createPolicyForm(@PathVariable("id") Long id, Model uiModel) {
		Program program = programService.findProgram(id);
		SubscriptionPolicy subscriptionPolicy = new SubscriptionPolicy();
		subscriptionPolicy.setProgramId(program);
		//bikeLetUser.setTenantId(program.getTenantId());
		populateEditPolicyForm(uiModel, subscriptionPolicy);
//		List<String[]> dependencies = new ArrayList<String[]>();
//		if (tenantService.countAllTenants() == 0) {
//			dependencies.add(new String[] { "tenant", "tenants" });
//		}
		//uiModel.addAttribute("dependencies", dependencies);
		return "programs/subscriptionpolicys/create";
	}
	
	@RequestMapping(value = "/{id}/subscriptionpolicys/{policyId}/subscriptionrates", params = "form", produces = "text/html")
	public String createPolicyRateForm(@PathVariable("id") Long id, @PathVariable("policyId") Long policyId, Model uiModel) {
		SubscriptionPolicy subscriptionPolicy = subscriptionPolicyService.findSubscriptionPolicy(policyId);
		//SubscriptionPolicy subscriptionPolicy = new SubscriptionPolicy();
		//subscriptionPolicy.setProgramId(program);
		SubscriptionRate subrate = new SubscriptionRate();
		subrate.setPolicyId(subscriptionPolicy);
		//bikeLetUser.setTenantId(program.getTenantId());
		populateEditPolicyRateForm(uiModel, subrate, id);
//		List<String[]> dependencies = new ArrayList<String[]>();
//		if (tenantService.countAllTenants() == 0) {
//			dependencies.add(new String[] { "tenant", "tenants" });
//		}
		//uiModel.addAttribute("dependencies", dependencies);
		return "programs/subscriptionpolicys/subscriptionrates/create";
	}
	
	@RequestMapping(value = "/{id}/bikeletusers/{userId}", produces = "text/html")
	public String showUser(@PathVariable("id") Long id, @PathVariable("userId") Long userId, Model uiModel) {
		BikeLetUser user = bikeLetUserService.findBikeLetUser(userId);
		UserSubscriptionPolicy userSubscriptionPolicy = userSubscriptionPolicyService.findUserSubscriptionPolicyByUser(userId);
		if (userSubscriptionPolicy != null)
			user.setSubscriptionPolicy(userSubscriptionPolicy.getPolicyId());
	    uiModel.addAttribute("bikeletuser", user);
	    uiModel.addAttribute("itemId", userId);
	    uiModel.addAttribute("programId", id);
	    return "programs/bikeletusers/show";
	}
	
	@RequestMapping(value = "/{id}/subscriptionpolicys/{policyId}", produces = "text/html")
	public String showPolicy(@PathVariable("id") Long id, @PathVariable("policyId") Long policyId, Model uiModel) {
	    uiModel.addAttribute("subscriptionpolicy", subscriptionPolicyService.findSubscriptionPolicy(policyId));
	    uiModel.addAttribute("itemId", policyId);
	    uiModel.addAttribute("programId", id);
	    return "programs/subscriptionpolicys/show";
	}
	
	@RequestMapping(value = "/{id}/subscriptionpolicys/{policyId}/subscriptionrates/{rateid}", produces = "text/html")
	public String showPolicyRate(@PathVariable("id") Long id, @PathVariable("policyId") Long policyId, @PathVariable("rateid") Long rateId, Model uiModel) {
	    uiModel.addAttribute("subscriptionrate", subscriptionRateService.findSubscriptionRate(rateId));
	    uiModel.addAttribute("itemId", rateId);
	   // uiModel.addAttribute("itemId", policyId);
	    uiModel.addAttribute("policyId", policyId);
	    uiModel.addAttribute("programId", id);
	    addDateTimeFormatPatterns(uiModel);
	    return "programs/subscriptionpolicys/subscriptionrates/show";
	}
	
	@RequestMapping(value = "/{id}", params = "form", produces = "text/html")
	public String updateForm(@PathVariable("id") Long id, Model uiModel) {
		Long tid = Utils.getLogonTenantId();
		Tenant tenant = tenantService.findTenant(tid);
		//Program prog = new Program();
		//prog.setTenantId(tenant);
		//populateEditForm(uiModel, prog);
		populateEditForm(uiModel, programService.findProgram(id));
		return "programs/update";
	}
	    
    @RequestMapping(value = "/{programId}/bikeletusers", method = RequestMethod.PUT, produces = "text/html")
    public String updateUser(@PathVariable("programId") Long programId, @Valid BikeLetUser bikeLetUser, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
        	Program program = programService.findProgram(programId);
    		bikeLetUser.setProgramId(program);
    		bikeLetUser.setTenantId(program.getTenantId());

            populateEditUserForm(uiModel, bikeLetUser);
            return "programs/bikeletusers/update";
        }
        uiModel.asMap().clear();
		bikeLetUser.setProgramId(programService.findProgram(bikeLetUser.getProgramId().getId()));
		bikeLetUser.setTenantId(tenantService.findTenant(bikeLetUser.getTenantId().getId()));	
		bikeLetUserService.updateBikeLetUser(bikeLetUser);
		
        // Update the policy
        UserSubscriptionPolicy userSubscriptionPolicy = userSubscriptionPolicyService.findUserSubscriptionPolicyByUser(bikeLetUser.getId());
        
        SubscriptionPolicy subscriptionPolicy = subscriptionPolicyService.findSubscriptionPolicy(bikeLetUser.getSubscriptionPolicy().getId());
		
		userSubscriptionPolicy.setPolicyId(subscriptionPolicy);
		userSubscriptionPolicyService.updateUserSubscriptionPolicy(userSubscriptionPolicy);
	
        
        
        return "redirect:/programs/" + programId + "/bikeletusers/" + encodeUrlPathSegment(bikeLetUser.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(value = "/{programId}/subscriptionpolicys", method = RequestMethod.PUT, produces = "text/html")
    public String updatePolicy(@PathVariable("programId") Long programId, @Valid SubscriptionPolicy subpol, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditPolicyForm(uiModel, subpol);
            return "programs/subscriptionpolicys/update";
        }
        uiModel.asMap().clear();
        subpol.setProgramId(programService.findProgram(subpol.getProgramId().getId()));
        //subpol.setTenantId(tenantService.findTenant(subpol.getTenantId().getId()));	

        subscriptionPolicyService.updateSubscriptionPolicy(subpol);
        return "redirect:/programs/" + programId + "/subscriptionpolicys/" + encodeUrlPathSegment(subpol.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(value = "/{programId}/subscriptionpolicys/{policyId}/subscriptionrates", method = RequestMethod.PUT, produces = "text/html")
    public String updatePolicyRate(@PathVariable("programId") Long programId, @PathVariable("policyId") Long policyId, @Valid SubscriptionRate subrate, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditPolicyRateForm(uiModel, subrate, programId);
            return "programs/subscriptionpolicys/subscriptionrates/update";
        }
        uiModel.asMap().clear();
        subrate.setPolicyId(subscriptionPolicyService.findSubscriptionPolicy(subrate.getPolicyId().getId()));
        //subpol.setTenantId(tenantService.findTenant(subpol.getTenantId().getId()));	

        subscriptionRateService.updateSubscriptionRate(subrate);
        return "redirect:/programs/" + programId + "/subscriptionpolicys/" + policyId + "/subscriptionrates/" + encodeUrlPathSegment(subrate.getId().toString(), httpServletRequest);
    }
	    
	@RequestMapping(value = "/{id}/bikeletusers/{userId}", params = "form", produces = "text/html")
	public String updateUserForm(@PathVariable("id") Long id, @PathVariable("userId") Long userId, Model uiModel) {
		BikeLetUser user = bikeLetUserService.findBikeLetUser(userId);
		UserSubscriptionPolicy userSubscriptionPolicy = userSubscriptionPolicyService.findUserSubscriptionPolicyByUser(userId);
		if (userSubscriptionPolicy != null)
		user.setSubscriptionPolicy(userSubscriptionPolicy.getPolicyId());
		
	    populateEditUserForm(uiModel, user);
	    return "programs/bikeletusers/update";
	}
	
	@RequestMapping(value = "/{id}/subscriptionpolicys/{policyId}", params = "form", produces = "text/html")
	public String updatePolicyForm(@PathVariable("id") Long id, @PathVariable("policyId") Long policyId, Model uiModel) {
	    populateEditPolicyForm(uiModel, subscriptionPolicyService.findSubscriptionPolicy(policyId));
	    return "programs/subscriptionpolicys/update";
	}
	
	@RequestMapping(value = "/{id}/subscriptionpolicys/{policyId}/subscriptionrates/{rateId}", params = "form", produces = "text/html")
	public String updatePolicyRateForm(@PathVariable("id") Long id, @PathVariable("policyId") Long policyId, @PathVariable("rateId") Long rateId, Model uiModel) {
	    populateEditPolicyRateForm(uiModel, subscriptionRateService.findSubscriptionRate(rateId), id);
	    return "programs/subscriptionpolicys/subscriptionrates/update";
	}
	    
	@RequestMapping(value = "/{id}/bikeletusers/{userId}", method = RequestMethod.DELETE, produces = "text/html")
	public String deleteUser(@PathVariable("id") Long id, @PathVariable("userId") Long userId, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
	    BikeLetUser bikeLetUser = bikeLetUserService.findBikeLetUser(userId);
	    bikeLetUserService.deleteBikeLetUser(bikeLetUser);
	    uiModel.asMap().clear();
	    uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
	    uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
	    return "redirect:/programs/" + id +"/bikeletusers";
	}
	
	@RequestMapping(value = "/{id}/subscriptionpolicys/{policyId}", method = RequestMethod.DELETE, produces = "text/html")
	public String deletePolicy(@PathVariable("id") Long id, @PathVariable("policyId") Long policyId, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
	    SubscriptionPolicy subpol = subscriptionPolicyService.findSubscriptionPolicy(policyId);
	    subscriptionPolicyService.deleteSubscriptionPolicy(subpol);
	    uiModel.asMap().clear();
	    uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
	    uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
	    return "redirect:/programs/" + id +"/subscriptionpolicys";
	}
	
	@RequestMapping(value = "/{id}/subscriptionpolicys/{policyId}/subscriptionrates/{rateId}", method = RequestMethod.DELETE, produces = "text/html")
	public String deletePolicyRate(@PathVariable("id") Long id, @PathVariable("policyId") Long policyId, @PathVariable("rateId") Long rateId, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
	    SubscriptionRate subrate = subscriptionRateService.findSubscriptionRate(rateId);
	    subscriptionRateService.deleteSubscriptionRate(subrate);
	    uiModel.asMap().clear();
	    uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
	    uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
	    return "redirect:/programs/" + id +"/subscriptionpolicys/" + policyId + "/subscriptionrates";
	}

	@RequestMapping(value = "/{id}/bikeletusers", produces = "text/html")
	public String listUsers(@PathVariable("id") Long id,
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "size", required = false) Integer size,
			Model uiModel) {
		uiModel.addAttribute("programId", id);
		if (page != null || size != null) {
			int sizeNo = size == null ? 10 : size.intValue();
			final int firstResult = page == null ? 0 : (page.intValue() - 1)
					* sizeNo;
			uiModel.addAttribute("bikeletusers", bikeLetUserService
					.findBikeLetUserEntriesByProgram(id, firstResult, sizeNo));
			float nrOfPages = (float) bikeLetUserService
					.countAllBikeLetUsersByProgram(id) / sizeNo;
			uiModel.addAttribute(
					"maxPages",
					(int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1
							: nrOfPages));
		} else {
			uiModel.addAttribute("bikeletusers",
					bikeLetUserService.findAllBikeLetUsersByProgram(id));
		}
		return "programs/bikeletusers/list";
	}
	
	 @RequestMapping(value = "/{id}/subscriptionpolicys", produces = "text/html")
	    public String listPolicys(@PathVariable("id") Long id,
				@RequestParam(value = "page", required = false) Integer page,
				@RequestParam(value = "size", required = false) Integer size,
				Model uiModel) {
		 uiModel.addAttribute("programId", id);   
		 if (page != null || size != null) {
	            int sizeNo = size == null ? 10 : size.intValue();
	            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
	            uiModel.addAttribute("subscriptionpolicys", subscriptionPolicyService.findSubscriptionPolicyEntriesByProgram(id,firstResult, sizeNo));
	            float nrOfPages = (float) subscriptionPolicyService.countSubscriptionPolicysByProgram(id) / sizeNo;
	            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
	        } else {
	            uiModel.addAttribute("subscriptionpolicys", subscriptionPolicyService.findAllSubscriptionPolicysByProgram(id));
	        }
	        return "programs/subscriptionpolicys/list";
	    }
	 
	 @RequestMapping(value = "/{id}/subscriptionpolicys/{policyId}/subscriptionrates", produces = "text/html")
	    public String listPolicyRates(@PathVariable("id") Long id,@PathVariable("policyId") Long policyId, 
				@RequestParam(value = "page", required = false) Integer page,
				@RequestParam(value = "size", required = false) Integer size,
				Model uiModel) {
		 uiModel.addAttribute("programId", id);
		 uiModel.addAttribute("policyId", policyId);
		 // TODO: Cheng: There are missing checkin from subscriptionRateService, remove this comment later
		 
		 if (page != null || size != null) {
	            int sizeNo = size == null ? 10 : size.intValue();
	            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
	            uiModel.addAttribute("subscriptionrates", subscriptionRateService.findSubscriptionRateEntriesByPolicy(policyId,firstResult, sizeNo));
	            float nrOfPages = (float) subscriptionRateService.countSubscriptionRatesByPolicy(policyId) / sizeNo;
	            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
	        } else {
	            uiModel.addAttribute("subscriptionrates", subscriptionRateService.findAllSubscriptionRatesByPolicy(policyId));
	        }
		 addDateTimeFormatPatterns(uiModel);
	        
	        return "programs/subscriptionpolicys/subscriptionrates/list";
	    }
		
	@RequestMapping(produces = "text/html")
	public String list(
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "size", required = false) Integer size,
			Model uiModel) {
		Long tenantId = Utils.getLogonTenantId();
		System.out.println("tenant id is "+tenantId);
		if (page != null || size != null) {
			int sizeNo = size == null ? 10 : size.intValue();
			final int firstResult = page == null ? 0 : (page.intValue() - 1)
					* sizeNo;
			uiModel.addAttribute("programs",
					programService.findProgramEntriesByTenant(tenantId, firstResult, sizeNo));
			float nrOfPages = (float) programService.countAllProgramsByTenant(tenantId)
					/ sizeNo;
			uiModel.addAttribute(
					"maxPages",
					(int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1
							: nrOfPages));
		} else {
			uiModel.addAttribute("programs", programService.findAllProgramsByTenant(tenantId));
		}
		return "programs/list";
	}
	
	void addDateTimeFormatPatterns(Model uiModel) {
        uiModel.addAttribute("subscriptionRate_policystartdate_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
        uiModel.addAttribute("subscriptionRate_policyenddate_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
    }


	void populateEditUserForm(Model uiModel, BikeLetUser bikeLetUser) {
		uiModel.addAttribute("bikeLetUser", bikeLetUser);
		uiModel.addAttribute("programId", bikeLetUser.getProgramId().getId());
		uiModel.addAttribute("programs", programService.findAllPrograms());
		uiModel.addAttribute("tenants", tenantService.findAllTenants());
	    uiModel.addAttribute("subscriptionPolicies", subscriptionPolicyService.findActiveSubscriptionPolicysByProgram(bikeLetUser.getProgramId().getId()));  
	}
	
	void populateEditPolicyForm(Model uiModel, SubscriptionPolicy subscriptionPolicy) {
		uiModel.addAttribute("subscriptionPolicy", subscriptionPolicy);
		uiModel.addAttribute("programId", subscriptionPolicy.getProgramId().getId());
		//uiModel.addAttribute("programs", programService.findAllPrograms());
		//uiModel.addAttribute("tenants", tenantService.findAllTenants());
	}
	
	void populateEditPolicyRateForm(Model uiModel, SubscriptionRate subscriptionRate, Long id){
		uiModel.addAttribute("subscriptionRate", subscriptionRate);
		addDateTimeFormatPatterns(uiModel);
		uiModel.addAttribute("programId", id);
		uiModel.addAttribute("policyId", subscriptionRate.getPolicyId().getId());
	}
}
