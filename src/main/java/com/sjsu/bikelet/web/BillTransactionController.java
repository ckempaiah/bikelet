package com.sjsu.bikelet.web;

import com.sjsu.bikelet.domain.BikeLetUser;
import com.sjsu.bikelet.domain.BillTransaction;
import org.joda.time.format.DateTimeFormat;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/billtransactions")
@Controller
@RooWebScaffold(path = "billtransactions", formBackingObject = BillTransaction.class)
public class BillTransactionController {

    void addDateTimeFormatPatterns(Model uiModel) {
        uiModel.addAttribute("billTransaction_startdate_date_format", DateTimeFormat.patternForStyle("MS", LocaleContextHolder.getLocale()));
        uiModel.addAttribute("billTransaction_enddate_date_format", DateTimeFormat.patternForStyle("MS", LocaleContextHolder.getLocale()));
    }

    @RequestMapping(produces = "text/html")
    public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            BikeLetUser loggedOnUser = BikeLetUser.findBikeLetUser(Utils.getLogonUserId());
            uiModel.addAttribute("billtransactions", billTransactionService.findBillTransactionEntriesByUserId(loggedOnUser, firstResult, sizeNo));
            float nrOfPages = (float) billTransactionService.countAllBillTransactionsByUserId(loggedOnUser) / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            BikeLetUser loggedOnUser = BikeLetUser.findBikeLetUser(Utils.getLogonUserId());
            uiModel.addAttribute("billtransactions", billTransactionService.findAllBillTransactionsByUserId(loggedOnUser));
        }
        addDateTimeFormatPatterns(uiModel);
        return "billtransactions/list";
    }
}
