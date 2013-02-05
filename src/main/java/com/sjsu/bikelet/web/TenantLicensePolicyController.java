package com.sjsu.bikelet.web;

import com.sjsu.bikelet.domain.TenantLicensePolicy;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/tenantlicensepolicys")
@Controller
@RooWebScaffold(path = "tenantlicensepolicys", formBackingObject = TenantLicensePolicy.class)
public class TenantLicensePolicyController {
}
