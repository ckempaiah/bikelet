package com.sjsu.bikelet.web;

import com.sjsu.bikelet.domain.UserSubscriptionPolicy;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/usersubscriptionpolicys")
@Controller
@RooWebScaffold(path = "usersubscriptionpolicys", formBackingObject = UserSubscriptionPolicy.class)
public class UserSubscriptionPolicyController {
}
