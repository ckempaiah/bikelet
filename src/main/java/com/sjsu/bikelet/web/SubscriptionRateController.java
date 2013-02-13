package com.sjsu.bikelet.web;

import com.sjsu.bikelet.domain.SubscriptionRate;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

@RequestMapping("/subscriptionrates")
@Controller
@RooWebScaffold(path = "subscriptionrates", formBackingObject = SubscriptionRate.class)
public class SubscriptionRateController {
	 @RequestMapping(method = RequestMethod.POST, produces = "text/html")
	    public String create(@Valid SubscriptionRate subscriptionRate, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
	        if (bindingResult.hasErrors()) {
	            populateEditForm(uiModel, subscriptionRate);
	            return "subscriptionrates/create";
	        }
	        uiModel.asMap().clear();
	        subscriptionRateService.saveSubscriptionRate(subscriptionRate);
	        return "redirect:/programs";
	    }
}
