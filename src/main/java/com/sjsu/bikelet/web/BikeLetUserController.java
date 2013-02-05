package com.sjsu.bikelet.web;

import com.sjsu.bikelet.domain.BikeLetUser;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/bikeletusers")
@Controller
@RooWebScaffold(path = "bikeletusers", formBackingObject = BikeLetUser.class)
public class BikeLetUserController {
}
