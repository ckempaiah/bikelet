package com.sjsu.bikelet.web;

import com.sjsu.bikelet.domain.RolePermission;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/rolepermissions")
@Controller
@RooWebScaffold(path = "rolepermissions", formBackingObject = RolePermission.class)
public class RolePermissionController {
}
