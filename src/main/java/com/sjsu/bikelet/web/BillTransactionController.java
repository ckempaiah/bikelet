package com.sjsu.bikelet.web;

import com.sjsu.bikelet.domain.BikeLetUser;
import com.sjsu.bikelet.domain.BillTransaction;
import com.sjsu.bikelet.domain.Tenant;
import com.sjsu.bikelet.model.BillTransactionTypeEnum;
import org.joda.time.format.DateTimeFormat;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@RequestMapping("/billtransactions")
@Controller
@RooWebScaffold(path = "billtransactions", formBackingObject = BillTransaction.class)
public class BillTransactionController {

    void addDateTimeFormatPatterns(Model uiModel) {
        uiModel.addAttribute("billTransaction_startdate_date_format", DateTimeFormat.patternForStyle("MS", LocaleContextHolder.getLocale()));
        uiModel.addAttribute("billTransaction_enddate_date_format", DateTimeFormat.patternForStyle("MS", LocaleContextHolder.getLocale()));
    }

    void populateEditForm(Model uiModel, BillTransaction billTransaction) {
        uiModel.addAttribute("billTransaction", billTransaction);
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("tenantId", Utils.getLogonTenantId());
        uiModel.addAttribute("bills", billService.findAllBills());
        uiModel.addAttribute("billtransactiontypes", Arrays.asList(BillTransactionTypeEnum.values()));
    }

    @RequestMapping(params = "form", produces = "text/html")
    public String createForm(Model uiModel) {
        BillTransaction billTransaction = new BillTransaction();
        Long tenantId = Utils.getLogonTenantId();
        Tenant tenant = Tenant.findTenant(tenantId);
        billTransaction.setTenantId(tenant);
        List<BikeLetUser> bikeLetUserList = BikeLetUser.findBikeLetUserEntriesByTenantId(tenantId);
        uiModel.addAttribute("bikeletusers", bikeLetUserList);
        populateEditForm(uiModel, billTransaction);
        return "billtransactions/create";
    }
    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String create(@Valid BillTransaction billTransaction, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, billTransaction);
            return "billtransactions/create";
        }
        Tenant tenantObj = Tenant.findTenant(billTransaction.getTenantId().getId());
        billTransaction.setTenantId(tenantObj);
        uiModel.asMap().clear();
        billTransactionService.saveBillTransaction(billTransaction);
        return "redirect:/billtransactions/" + encodeUrlPathSegment(billTransaction.getId().toString(), httpServletRequest);
    }
    @RequestMapping(produces = "text/html")
    public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {

        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            String roleName = Utils.getLogonUser().getAuthorities().iterator().next().getAuthority();
            BikeLetUser loggedOnUser = BikeLetUser.findBikeLetUser(Utils.getLogonUserId());
            uiModel.addAttribute("billtransactions", billTransactionService.findBillTransactionEntriesByUserId(loggedOnUser, roleName, firstResult, sizeNo));
            float nrOfPages = (float) billTransactionService.countAllBillTransactionsByUserId(loggedOnUser) / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            String roleName = Utils.getLogonUser().getAuthorities().iterator().next().getAuthority();
            BikeLetUser loggedOnUser = BikeLetUser.findBikeLetUser(Utils.getLogonUserId());
            uiModel.addAttribute("billtransactions", billTransactionService.findAllBillTransactionsByUserId(loggedOnUser, roleName));
        }
        addDateTimeFormatPatterns(uiModel);
        return "billtransactions/list";
    }

    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String update(@Valid BillTransaction billTransaction, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, billTransaction);
            return "billtransactions/update";
        }
        Tenant tenantObj = Tenant.findTenant(billTransaction.getTenantId().getId());
        billTransaction.setTenantId(tenantObj);
        uiModel.asMap().clear();
        billTransactionService.updateBillTransaction(billTransaction);
        return "redirect:/billtransactions/" + encodeUrlPathSegment(billTransaction.getId().toString(), httpServletRequest);
    }
}
