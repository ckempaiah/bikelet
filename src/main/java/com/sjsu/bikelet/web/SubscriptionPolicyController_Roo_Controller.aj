// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.sjsu.bikelet.web;

import com.sjsu.bikelet.domain.Program;
import com.sjsu.bikelet.domain.SubscriptionPolicy;
import com.sjsu.bikelet.web.SubscriptionPolicyController;
import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

privileged aspect SubscriptionPolicyController_Roo_Controller {
    
    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String SubscriptionPolicyController.create(@Valid SubscriptionPolicy subscriptionPolicy, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, subscriptionPolicy);
            return "subscriptionpolicys/create";
        }
        uiModel.asMap().clear();
        subscriptionPolicy.persist();
        return "redirect:/subscriptionpolicys/" + encodeUrlPathSegment(subscriptionPolicy.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(params = "form", produces = "text/html")
    public String SubscriptionPolicyController.createForm(Model uiModel) {
        populateEditForm(uiModel, new SubscriptionPolicy());
        return "subscriptionpolicys/create";
    }
    
    @RequestMapping(value = "/{id}", produces = "text/html")
    public String SubscriptionPolicyController.show(@PathVariable("id") Long id, Model uiModel) {
        uiModel.addAttribute("subscriptionpolicy", SubscriptionPolicy.findSubscriptionPolicy(id));
        uiModel.addAttribute("itemId", id);
        return "subscriptionpolicys/show";
    }
    
    @RequestMapping(produces = "text/html")
    public String SubscriptionPolicyController.list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("subscriptionpolicys", SubscriptionPolicy.findSubscriptionPolicyEntries(firstResult, sizeNo));
            float nrOfPages = (float) SubscriptionPolicy.countSubscriptionPolicys() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("subscriptionpolicys", SubscriptionPolicy.findAllSubscriptionPolicys());
        }
        return "subscriptionpolicys/list";
    }
    
    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String SubscriptionPolicyController.update(@Valid SubscriptionPolicy subscriptionPolicy, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, subscriptionPolicy);
            return "subscriptionpolicys/update";
        }
        uiModel.asMap().clear();
        subscriptionPolicy.merge();
        return "redirect:/subscriptionpolicys/" + encodeUrlPathSegment(subscriptionPolicy.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String SubscriptionPolicyController.updateForm(@PathVariable("id") Long id, Model uiModel) {
        populateEditForm(uiModel, SubscriptionPolicy.findSubscriptionPolicy(id));
        return "subscriptionpolicys/update";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String SubscriptionPolicyController.delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        SubscriptionPolicy subscriptionPolicy = SubscriptionPolicy.findSubscriptionPolicy(id);
        subscriptionPolicy.remove();
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/subscriptionpolicys";
    }
    
    void SubscriptionPolicyController.populateEditForm(Model uiModel, SubscriptionPolicy subscriptionPolicy) {
        uiModel.addAttribute("subscriptionPolicy", subscriptionPolicy);
        uiModel.addAttribute("programs", Program.findAllPrograms());
    }
    
    String SubscriptionPolicyController.encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
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
