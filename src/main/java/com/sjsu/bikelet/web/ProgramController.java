package com.sjsu.bikelet.web;

import com.sjsu.bikelet.domain.Program;
import com.sjsu.bikelet.domain.BikeLetUser;
import com.sjsu.bikelet.domain.UserRole;
import com.sjsu.bikelet.domain.BikeLetRole;
import com.sjsu.bikelet.domain.Tenant;
import com.sjsu.bikelet.service.ProgramService;
import com.sjsu.bikelet.service.BikeLetUserService;
import com.sjsu.bikelet.service.TenantService;
import com.sjsu.bikelet.service.UserRoleService;
import com.sjsu.bikelet.service.BikeLetRoleService;
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

@RequestMapping("/programs")
@Controller
@RooWebScaffold(path = "programs", formBackingObject = Program.class)
public class ProgramController {

	@RequestMapping(value = "/{id}/bikeletusers", method = RequestMethod.POST, produces = "text/html")
	public String createUser(@Valid BikeLetUser bikeLetUser,
			BindingResult bindingResult, Model uiModel,
			HttpServletRequest httpServletRequest) {
		if (bindingResult.hasErrors()) {
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
		return "redirect:/programs/" + bikeLetUser.getProgramId().getId() +  "/bikeletusers";
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
	
	@RequestMapping(value = "/{id}/bikeletusers/{userId}", produces = "text/html")
	public String showUser(@PathVariable("id") Long id, @PathVariable("userId") Long userId, Model uiModel) {
	    uiModel.addAttribute("bikeletuser", bikeLetUserService.findBikeLetUser(userId));
	    uiModel.addAttribute("itemId", userId);
	    uiModel.addAttribute("programId", id);
	    return "programs/bikeletusers/show";
	}
	    
    @RequestMapping(value = "/{programId}/bikeletusers", method = RequestMethod.PUT, produces = "text/html")
    public String updateUser(@PathVariable("programId") Long programId, @Valid BikeLetUser bikeLetUser, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditUserForm(uiModel, bikeLetUser);
            return "programs/bikeletusers/update";
        }
        uiModel.asMap().clear();
		bikeLetUser.setProgramId(programService.findProgram(bikeLetUser.getProgramId().getId()));
		bikeLetUser.setTenantId(tenantService.findTenant(bikeLetUser.getTenantId().getId()));	

        bikeLetUserService.updateBikeLetUser(bikeLetUser);
        return "redirect:/programs/" + programId + "/bikeletusers/" + encodeUrlPathSegment(bikeLetUser.getId().toString(), httpServletRequest);
    }
	    
	@RequestMapping(value = "/{id}/bikeletusers/{userId}", params = "form", produces = "text/html")
	public String updateUserForm(@PathVariable("id") Long id, @PathVariable("userId") Long userId, Model uiModel) {
	    populateEditUserForm(uiModel, bikeLetUserService.findBikeLetUser(userId));
	    return "programs/bikeletusers/update";
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

	@RequestMapping(produces = "text/html")
	public String list(
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "size", required = false) Integer size,
			Model uiModel) {
		if (page != null || size != null) {
			int sizeNo = size == null ? 10 : size.intValue();
			final int firstResult = page == null ? 0 : (page.intValue() - 1)
					* sizeNo;
			uiModel.addAttribute("programs",
					programService.findProgramEntries(firstResult, sizeNo));
			float nrOfPages = (float) programService.countAllPrograms()
					/ sizeNo;
			uiModel.addAttribute(
					"maxPages",
					(int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1
							: nrOfPages));
		} else {
			uiModel.addAttribute("programs", programService.findAllPrograms());
		}
		return "programs/list";
	}


	void populateEditUserForm(Model uiModel, BikeLetUser bikeLetUser) {
		uiModel.addAttribute("bikeLetUser", bikeLetUser);
		uiModel.addAttribute("programId", bikeLetUser.getProgramId().getId());
		uiModel.addAttribute("programs", programService.findAllPrograms());
		uiModel.addAttribute("tenants", tenantService.findAllTenants());
	}
}
