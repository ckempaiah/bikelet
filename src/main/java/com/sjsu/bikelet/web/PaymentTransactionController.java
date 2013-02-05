package com.sjsu.bikelet.web;

import com.sjsu.bikelet.domain.PaymentTransaction;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/paymenttransactions")
@Controller
@RooWebScaffold(path = "paymenttransactions", formBackingObject = PaymentTransaction.class)
public class PaymentTransactionController {
}
