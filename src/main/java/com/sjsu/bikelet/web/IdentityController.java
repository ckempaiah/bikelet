package com.sjsu.bikelet.web;


import com.sjsu.bikelet.domain.BikeLetUser;
import com.sjsu.bikelet.service.BikeLetUserService;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.sjsu.bikelet.bean.User;

import org.springframework.beans.factory.annotation.Autowired;

@RequestMapping("/identity")
@Controller
@RooWebScaffold(path = "identity", formBackingObject = User.class)
public class IdentityController {
	
    @Autowired
    BikeLetUserService bikeLetUserService;
    
    @RequestMapping(value = "authenticate", produces = {"application/json", "text/html"})
    public String authenticateUser(@RequestParam(value = "username", required = true) String username, @RequestParam(value = "password", required = true) String password, Model uiModel) {
    	BikeLetUser bikeletuser = bikeLetUserService.findBikeLetUser(username, password);

    	User user = com.sjsu.bikelet.bean.User.convert(bikeletuser);
    	
    	uiModel.addAttribute("user",user);
    	return "bikeletusers/list";
    }

}
