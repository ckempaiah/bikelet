package com.sjsu.bikelet.web;

import com.sjsu.bikelet.domain.Permission;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/permissions")
@Controller
@RooWebScaffold(path = "permissions", formBackingObject = Permission.class)
public class PermissionController {
}
