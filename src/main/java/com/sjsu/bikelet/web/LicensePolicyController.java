package com.sjsu.bikelet.web;

import com.sjsu.bikelet.domain.LicensePolicy;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/licensepolicys")
@Controller
@RooWebScaffold(path = "licensepolicys", formBackingObject = LicensePolicy.class)
public class LicensePolicyController {
}
