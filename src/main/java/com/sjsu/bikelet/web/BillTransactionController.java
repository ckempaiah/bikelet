package com.sjsu.bikelet.web;

import com.sjsu.bikelet.domain.BillTransaction;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/billtransactions")
@Controller
@RooWebScaffold(path = "billtransactions", formBackingObject = BillTransaction.class)
public class BillTransactionController {
}
