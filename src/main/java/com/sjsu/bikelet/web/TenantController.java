package com.sjsu.bikelet.web;

import com.sjsu.bikelet.domain.Tenant;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/tenants")
@Controller
@RooWebScaffold(path = "tenants", formBackingObject = Tenant.class)
public class TenantController {
}
