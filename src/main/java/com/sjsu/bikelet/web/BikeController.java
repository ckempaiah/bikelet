package com.sjsu.bikelet.web;

import com.sjsu.bikelet.domain.Bike;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/bikes")
@Controller
@RooWebScaffold(path = "bikes", formBackingObject = Bike.class)
public class BikeController {
}
