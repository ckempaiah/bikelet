package com.sjsu.bikelet.web;

import java.util.List;

import com.sjsu.bikelet.domain.BikeLetUser;
import com.sjsu.bikelet.domain.Bill;
import com.sjsu.bikelet.domain.RentTransaction;
import com.sjsu.bikelet.domain.Station;
import com.sjsu.bikelet.service.BillService;
import com.sun.corba.se.impl.javax.rmi.CORBA.Util;

import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.context.i18n.LocaleContextHolder;

@RequestMapping("/bills")
@Controller
@RooWebScaffold(path = "bills", formBackingObject = Bill.class)
public class BillController {
    
    @RequestMapping(produces = "text/html")
    public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        
        if(Utils.getLogonUser().getProgramId() != null)
        {
        	Long userId = Utils.getLogonUserId();
        	if (page != null || size != null) {
                int sizeNo = size == null ? 10 : size.intValue();
                final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
                List<Bill> bills = billService.findBillEntriesByUser(firstResult, sizeNo, userId);
                loadUserNames(bills);
                uiModel.addAttribute("bills", bills);
                float nrOfPages = (float) billService.countAllBillsForUser(userId) / sizeNo;
                uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
            } else {
            	List<Bill> bills = billService.findAllBillsByUser(userId);
            	loadUserNames(bills);
                uiModel.addAttribute("bills", bills);
            }
        }
        
        else
        {
        	Long tenantId = Utils.getLogonTenantId();
        	if (page != null || size != null) {
                int sizeNo = size == null ? 10 : size.intValue();
                final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
                List<Bill> bills = billService.findBillEntriesByTenant(firstResult, sizeNo, tenantId);
                loadUserNames(bills);
                uiModel.addAttribute("bills", bills);
                float nrOfPages = (float) billService.countAllBillsForTenant(tenantId) / sizeNo;
                uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
            } else {
            	List<Bill> bills = billService.findAllBillsByTenant(tenantId);
                loadUserNames(bills);
                uiModel.addAttribute("bills", bills);
            }
        }
        addDateTimeFormatPatterns(uiModel);
        return "bills/list";
    }
    
    @RequestMapping(value = "/{id}", produces = "text/html")
    public String show(@PathVariable("id") Long id, Model uiModel) {
        addDateTimeFormatPatterns(uiModel);
        Bill bill = billService.findBill(id);
        String userName = bill.getUserId().getFirstName()+" "+bill.getUserId().getLastName();
        bill.setUser(userName);
        uiModel.addAttribute("bill", bill);
        uiModel.addAttribute("itemId", id);
        return "bills/show";
    }
    
    private void loadUserNames(List<Bill> bills) {
    	for (Bill bill: bills) {
    		String userName = bill.getUserId().getFirstName()+" "+bill.getUserId().getLastName();
    		if(userName != null)   {
    			bill.setUser(userName);
            }
    	}
    }
}
