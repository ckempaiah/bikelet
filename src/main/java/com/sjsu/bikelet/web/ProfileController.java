package com.sjsu.bikelet.web;

import com.sjsu.bikelet.domain.Address;
import com.sjsu.bikelet.domain.Tenant;

import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sjsu.bikelet.domain.PaymentInfo;
import com.sjsu.bikelet.service.AddressService;
import com.sjsu.bikelet.service.BikeLetUserService;
import com.sjsu.bikelet.service.PaymentInfoService;
import com.sjsu.bikelet.web.PaymentInfoController;
import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

@RequestMapping("/profile")
@Controller
@RooWebScaffold(path = "profile", formBackingObject = Address.class)
public class ProfileController {
	
    @Autowired
    AddressService addressService;
    
    @Autowired
    PaymentInfoService paymentInfoService;
    
    @Autowired
    BikeLetUserService bikeLetUserService;
	
    @RequestMapping(value = "/address", method = RequestMethod.PUT, produces = "text/html")
    public String updateAddress(@Valid Address address, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
    	Long userId = Utils.getLogonUserId();
    	if (bindingResult.hasErrors()) {
        	address.setUserId(bikeLetUserService.findBikeLetUser(userId));
            populateEditAddressForm(uiModel, address);
            return "profile/address/update";
        }
        uiModel.asMap().clear();
        address.setUserId(bikeLetUserService.findBikeLetUser(userId));

        if (address.getId() == null) {
        	addressService.saveAddress(address);
        } else {
        	addressService.updateAddress(address);
        }
        
        return "redirect:/profile/address?form";
    }
    
    @RequestMapping(value = "/address", params = "form", produces = "text/html")
    public String updateAddressForm(Model uiModel) {
    	Long userId = Utils.getLogonUserId();
    	
    	Address address;
    	try {
    		address = addressService.findAddressByUser(userId);
    		
    	} catch (Exception e) {
    		address = new Address();
    		address.setUserId(bikeLetUserService.findBikeLetUser(userId));
    	}
    	
        populateEditAddressForm(uiModel, address);
        return "profile/address/update";
    }
    
    void populateEditAddressForm(Model uiModel, Address address) {
    	Long userId = Utils.getLogonUserId();
    	
        uiModel.addAttribute("address", address);
    }
    
    @RequestMapping(value = "/paymentinfo", method = RequestMethod.PUT, produces = "text/html")
    public String updatePaymentInfo(@Valid PaymentInfo paymentInfo, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
    	Long userId = Utils.getLogonUserId();
    	if (bindingResult.hasErrors()) {
    		System.out.println("errro");
    		for (ObjectError err : bindingResult.getAllErrors()) {
    			System.out.println(err.getDefaultMessage() + err.getObjectName());
    		}
    		
    		paymentInfo.setUserId(bikeLetUserService.findBikeLetUser(userId));
            populateEditPaymentInfoForm(uiModel, paymentInfo);
            return "profile/paymentinfo/update";
        }
        uiModel.asMap().clear();
    	
    	paymentInfo.setUserId(bikeLetUserService.findBikeLetUser(userId));

        if (paymentInfo.getId() == null) {
        	paymentInfoService.savePaymentInfo(paymentInfo);
        } else {
          	paymentInfoService.updatePaymentInfo(paymentInfo);
        }
        return "redirect:/profile/paymentinfo?form";
    }

	@RequestMapping(value = "/paymentinfo", params = "form", produces = "text/html")
    public String updatePaymentInfoForm(Model uiModel) {
    	Long userId = Utils.getLogonUserId();
    	PaymentInfo paymentInfo;
    	try {
    		paymentInfo = paymentInfoService.findPaymentInfoByUser(userId);
    		
    	} catch (Exception e) {
    		paymentInfo = new PaymentInfo();
    		paymentInfo.setUserId(bikeLetUserService.findBikeLetUser(userId));
    	}
 
    	populateEditPaymentInfoForm(uiModel, paymentInfo);
        return "profile/paymentinfo/update";
    }

    void populateEditPaymentInfoForm(Model uiModel, PaymentInfo paymentInfo) {
        uiModel.addAttribute("paymentInfo", paymentInfo);
    }
    
}
