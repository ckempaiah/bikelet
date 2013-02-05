package com.sjsu.bikelet.web;

import com.sjsu.bikelet.domain.SubscriptionRate;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/subscriptionrates")
@Controller
@RooWebScaffold(path = "subscriptionrates", formBackingObject = SubscriptionRate.class)
public class SubscriptionRateController {
}
