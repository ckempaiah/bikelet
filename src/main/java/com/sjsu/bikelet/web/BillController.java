package com.sjsu.bikelet.web;

import com.sjsu.bikelet.domain.Bill;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/bills")
@Controller
@RooWebScaffold(path = "bills", formBackingObject = Bill.class)
public class BillController {
}
