package com.sjsu.bikelet.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.RandomStringUtils;

import com.sjsu.bikelet.domain.BikeLetUser;
import com.sjsu.bikelet.domain.BikeLocation;
import com.sjsu.bikelet.domain.RentTransaction;
import com.sjsu.bikelet.domain.Station;
import com.sjsu.bikelet.domain.UserSubscriptionPolicy;
import com.sjsu.bikelet.model.BikeStatusEnum;
import com.sjsu.bikelet.model.RentTransactionStatusEnum;
import com.sjsu.bikelet.service.BikeLocationService;
import com.sjsu.bikelet.service.ProgramService;
import com.sjsu.bikelet.service.StationService;
import com.sjsu.bikelet.service.SubscriptionRateService;
import com.sjsu.bikelet.service.UserSubscriptionPolicyService;
import com.sun.corba.se.impl.javax.rmi.CORBA.Util;

import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


@RequestMapping("/renttransactions")
@Controller
@RooWebScaffold(path = "renttransactions", formBackingObject = RentTransaction.class)
public class RentTransactionController {
	
	@Autowired
	StationService stationService;
	
	@Autowired
	BikeLocationService bikeLocationService;
	
	@Autowired
	UserSubscriptionPolicyService userPolicyService;
	
	@Autowired
	SubscriptionRateService userRateService;
	
	
	/* CHECKOUT */
	@RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String create(@Valid RentTransaction rentTransaction, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, rentTransaction);
            return "renttransactions/create";
        }
        uiModel.asMap().clear();
        BikeLetUser user = new BikeLetUser();
		user = bikeLetUserService.findUserFromId(Utils.getLogonUser().getUserId());
		String accessKey = RandomStringUtils.randomNumeric(6);
		
		rentTransaction.setRentStartDate(new Date());
        rentTransaction.setUserId(user);
        rentTransaction.setAccessKey(accessKey);
        rentTransactionService.saveRentTransaction(rentTransaction);
        
        BikeLocation bikeLocation = new BikeLocation();
        bikeLocation = bikeLocationService.findBikeLocationOfBike(rentTransaction.getBikeId().getId());
        bikeLocation.setBikeStatus(BikeStatusEnum.CheckedOut.toString());
        bikeLocationService.updateBikeLocation(bikeLocation);
        
        return "redirect:/renttransactions/" + encodeUrlPathSegment(rentTransaction.getId().toString(), httpServletRequest);
    }
	
	/* CHECKOUT */
	@RequestMapping(value="/checkoutbike", method = RequestMethod.POST, produces = "text/html")
    public String getCheckoutForm(@Valid RentTransaction rentTransaction, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, rentTransaction);
            return "renttransactions/create";
        }
        uiModel.asMap().clear();
        long stationId = rentTransaction.getFromStationId();
        return "redirect:/renttransactions/"+stationId+"/checkoutbike?form";
    }
	
	/* CHECKOUT */
	@RequestMapping(value = "/{stationId}/checkoutbike", params = "form", produces = "text/html")
    public String checkout(@PathVariable("stationId") Long stationId, Model uiModel) {

		RentTransaction rentTransaction = new RentTransaction();
		rentTransaction.setFromStationId(stationId.intValue());
		rentTransaction.setStatus(RentTransactionStatusEnum.InProgress.toString());
        rentTransaction.setTenantId(Utils.getLogonTenantId());

        if(bikeLocationService.countAvailableBikesByStation(stationId)!=0){
        	populateCheckoutForm(uiModel, rentTransaction, stationId);
        	return "renttransactions/checkout";
        }
        else{
        	Integer i = 0;
        	return "redirect:/renttransactions/selectstation/"+i+"?form";
        }
    }
    
    /* CHECKOUT */
    @RequestMapping(value = "/selectstation/{value}", params = "form", produces = "text/html")
    public String listStations(@PathVariable("value") Integer value, Model uiModel) {
    	
    	Long userId = (long) 0;
    	List<Station> stations = new ArrayList<Station>();
    	stations = stationService.findAllStationsByProgram(Utils.getLogonUser().getProgramId());
    	uiModel.addAttribute("rentTransaction", new RentTransaction());
		uiModel.addAttribute("stations", stations);
		if(value==1)
		{
			userId = Utils.getLogonUser().getUserId();
			UserSubscriptionPolicy policy = new UserSubscriptionPolicy();
			policy = userPolicyService.findUserSubscriptionPolicyByUser(userId);
			
			RentTransaction rentTransaction = new RentTransaction(); 
			rentTransaction = rentTransactionService.findRentTransactionForCheckin(userId, RentTransactionStatusEnum.InProgress.toString());
			if(rentTransaction!=null)
				return "renttransactions/checkoutexists";
			else if(policy!=null)
			{
				if(userRateService.isValidSubscriptionPolicy(policy.getPolicyId().getId()))
					return "renttransactions/liststations";
				else
					return "renttransactions/nopolicy";
			}
			else
				return "renttransactions/nopolicy";
		}
		else
			return "renttransactions/liststationsnobikes";
    }
    
    /* CHECKOUT */
    @RequestMapping(value = "/checkoutbike", method = RequestMethod.PUT, produces = "text/html")
    public String checkoutBike(@Valid RentTransaction rentTransaction, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, rentTransaction);
            return "renttransactions/update";
        }
        uiModel.asMap().clear();
        return "redirect:/renttransactions/"+ rentTransaction.getFromStationId() +"/checkout?form"; //redirect:/
    }
    
    
    
	/* CHECKIN */
    @RequestMapping(value = "/checkin", params = "form", produces = "text/html")
    public String updateForm(Model uiModel) {
		RentTransaction rentTransaction = rentTransactionService.findRentTransactionForCheckin(Utils.getLogonUser().getUserId(), RentTransactionStatusEnum.InProgress.toString());
		if(rentTransaction!=null)
		{
			populateUpdateForm(uiModel, rentTransaction);
	        return "renttransactions/update";
		}
		else
			return "renttransactions/checkoutfirst";
    }
    
    
    /* CHECKIN */
    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String update(@Valid RentTransaction rentTransaction, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, rentTransaction);
            return "renttransactions/update";
        }
        uiModel.asMap().clear();
        BikeLetUser user = new BikeLetUser();
		user = bikeLetUserService.findUserFromId(Utils.getLogonUser().getUserId());
		RentTransaction transaction = rentTransactionService.findRentTransactionForCheckin(Utils.getLogonUser().getUserId(), RentTransactionStatusEnum.InProgress.toString());		
		transaction.setRentEndDate(new Date());
		transaction.setStatus(RentTransactionStatusEnum.Complete.toString());
		transaction.setToStationId(rentTransaction.getToStationId());
		transaction.setComments(rentTransaction.getComments());
		
        RentTransaction rt = rentTransactionService.updateRentTransaction(transaction);
        bikeLocationService.updateBikeLocation(rt.getBikeId().getId(), BikeStatusEnum.Available.toString(), rt.getToStationId());
        return "redirect:/renttransactions/" + encodeUrlPathSegment(rentTransaction.getId().toString(), httpServletRequest);
    }
    
    /* CHECKOUT */
    void populateCheckoutForm(Model uiModel, RentTransaction rentTransaction, Long stationId) {
        uiModel.addAttribute("rentTransaction", rentTransaction);
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("bikes", bikeService.findAvailableBikesByStation(stationId));
    }
    
    
    /* CHECKIN */
    void populateUpdateForm(Model uiModel, RentTransaction rentTransaction) {
        uiModel.addAttribute("rentTransaction", rentTransaction);
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("renttransaction",rentTransaction);
        uiModel.addAttribute("bikeletusers", bikeLetUserService.findAllBikeLetUsers());
		uiModel.addAttribute("tostations", stationService.findAllStationsByProgram(Utils.getLogonUser().getProgramId()));
    }
    
}
