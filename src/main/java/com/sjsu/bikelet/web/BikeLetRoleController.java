package com.sjsu.bikelet.web;

import com.sjsu.bikelet.domain.BikeLetRole;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/bikeletroles")
@Controller
@RooWebScaffold(path = "bikeletroles", formBackingObject = BikeLetRole.class)
public class BikeLetRoleController {
}
