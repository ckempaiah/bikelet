package com.sjsu.bikelet.web;

import com.sjsu.bikelet.domain.BikeLocation;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/bikelocations")
@Controller
@RooWebScaffold(path = "bikelocations", formBackingObject = BikeLocation.class)
public class BikeLocationController {
}
