package com.sjsu.bikelet.web;

import com.sjsu.bikelet.domain.BillTransaction;
import org.joda.time.format.DateTimeFormat;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/billtransactions")
@Controller
@RooWebScaffold(path = "billtransactions", formBackingObject = BillTransaction.class)
public class BillTransactionController {

    void addDateTimeFormatPatterns(Model uiModel) {
        uiModel.addAttribute("billTransaction_startdate_date_format", DateTimeFormat.patternForStyle("MS", LocaleContextHolder.getLocale()));
        uiModel.addAttribute("billTransaction_enddate_date_format", DateTimeFormat.patternForStyle("MS", LocaleContextHolder.getLocale()));
    }
}
