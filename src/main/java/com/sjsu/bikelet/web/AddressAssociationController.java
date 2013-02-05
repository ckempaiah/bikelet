package com.sjsu.bikelet.web;

import com.sjsu.bikelet.domain.AddressAssociation;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/addressassociations")
@Controller
@RooWebScaffold(path = "addressassociations", formBackingObject = AddressAssociation.class)
public class AddressAssociationController {
}
