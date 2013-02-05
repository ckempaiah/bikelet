package com.sjsu.bikelet.web;

import com.sjsu.bikelet.domain.PaymentInfo;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/paymentinfoes")
@Controller
@RooWebScaffold(path = "paymentinfoes", formBackingObject = PaymentInfo.class)
public class PaymentInfoController {
}
