// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.sjsu.bikelet.web;

import com.sjsu.bikelet.domain.PaymentInfo;
import com.sjsu.bikelet.service.BikeLetUserService;
import com.sjsu.bikelet.service.PaymentInfoService;
import com.sjsu.bikelet.web.PaymentInfoController;
import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

privileged aspect PaymentInfoController_Roo_Controller {
    
    @Autowired
    PaymentInfoService PaymentInfoController.paymentInfoService;
    
    @Autowired
    BikeLetUserService PaymentInfoController.bikeLetUserService;
    
    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String PaymentInfoController.create(@Valid PaymentInfo paymentInfo, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, paymentInfo);
            return "paymentinfoes/create";
        }
        uiModel.asMap().clear();
        paymentInfoService.savePaymentInfo(paymentInfo);
        return "redirect:/paymentinfoes/" + encodeUrlPathSegment(paymentInfo.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(params = "form", produces = "text/html")
    public String PaymentInfoController.createForm(Model uiModel) {
        populateEditForm(uiModel, new PaymentInfo());
        return "paymentinfoes/create";
    }
    
    @RequestMapping(value = "/{id}", produces = "text/html")
    public String PaymentInfoController.show(@PathVariable("id") Long id, Model uiModel) {
        uiModel.addAttribute("paymentinfo", paymentInfoService.findPaymentInfo(id));
        uiModel.addAttribute("itemId", id);
        return "paymentinfoes/show";
    }
    
    @RequestMapping(produces = "text/html")
    public String PaymentInfoController.list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("paymentinfoes", paymentInfoService.findPaymentInfoEntries(firstResult, sizeNo));
            float nrOfPages = (float) paymentInfoService.countAllPaymentInfoes() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("paymentinfoes", paymentInfoService.findAllPaymentInfoes());
        }
        return "paymentinfoes/list";
    }
    
    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String PaymentInfoController.update(@Valid PaymentInfo paymentInfo, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, paymentInfo);
            return "paymentinfoes/update";
        }
        uiModel.asMap().clear();
        paymentInfoService.updatePaymentInfo(paymentInfo);
        return "redirect:/paymentinfoes/" + encodeUrlPathSegment(paymentInfo.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String PaymentInfoController.updateForm(@PathVariable("id") Long id, Model uiModel) {
        populateEditForm(uiModel, paymentInfoService.findPaymentInfo(id));
        return "paymentinfoes/update";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String PaymentInfoController.delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        PaymentInfo paymentInfo = paymentInfoService.findPaymentInfo(id);
        paymentInfoService.deletePaymentInfo(paymentInfo);
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/paymentinfoes";
    }
    
    void PaymentInfoController.populateEditForm(Model uiModel, PaymentInfo paymentInfo) {
        uiModel.addAttribute("paymentInfo", paymentInfo);
        uiModel.addAttribute("bikeletusers", bikeLetUserService.findAllBikeLetUsers());
    }
    
    String PaymentInfoController.encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
        String enc = httpServletRequest.getCharacterEncoding();
        if (enc == null) {
            enc = WebUtils.DEFAULT_CHARACTER_ENCODING;
        }
        try {
            pathSegment = UriUtils.encodePathSegment(pathSegment, enc);
        } catch (UnsupportedEncodingException uee) {}
        return pathSegment;
    }
    
}
