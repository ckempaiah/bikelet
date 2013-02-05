package com.sjsu.bikelet.web;

import com.sjsu.bikelet.domain.SubscriptionPolicy;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/subscriptionpolicys")
@Controller
@RooWebScaffold(path = "subscriptionpolicys", formBackingObject = SubscriptionPolicy.class)
public class SubscriptionPolicyController {
}
