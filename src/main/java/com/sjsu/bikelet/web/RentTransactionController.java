package com.sjsu.bikelet.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sjsu.bikelet.domain.BikeLetUser;
import com.sjsu.bikelet.domain.BikeLocation;
import com.sjsu.bikelet.domain.RentTransaction;
import com.sjsu.bikelet.domain.Station;
import com.sjsu.bikelet.domain.UserSubscriptionPolicy;
import com.sjsu.bikelet.model.BikeStatusEnum;
import com.sjsu.bikelet.service.BikeLocationService;
import com.sjsu.bikelet.service.ProgramService;
import com.sjsu.bikelet.service.StationService;
import com.sjsu.bikelet.service.SubscriptionRateService;
import com.sjsu.bikelet.service.UserSubscriptionPolicyService;

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
        System.out.println("In create method ............ ");
        BikeLetUser user = new BikeLetUser();
		user = bikeLetUserService.findUserFromId(Utils.getLogonUser().getUserId());
        rentTransaction.setRentStartDate(new Date());
        rentTransaction.setUserId(user);
        System.out.println("Station Id is ...................  "+rentTransaction.getFromStationId());
        rentTransactionService.saveRentTransaction(rentTransaction);
        BikeLocation bikeLocation = new BikeLocation();
        bikeLocation = bikeLocationService.findBikeLocationOfBike(rentTransaction.getBikeId().getId());
        bikeLocation.setBikeStatus("Checkedout");
        bikeLocationService.updateBikeLocation(bikeLocation);
        System.out.println("Rent Transaction is .............. "+rentTransaction);
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
//        rentTransactionService.saveRentTransaction(rentTransaction);
        System.out.println("Rent Transaction is .............. "+rentTransaction);
        long stationId = rentTransaction.getFromStationId();
        return "redirect:/renttransactions/"+stationId+"/checkoutbike?form";
    }
	
	/* CHECKOUT */
	@RequestMapping(value = "/{stationId}/checkoutbike", params = "form", produces = "text/html")
    public String checkout(@PathVariable("stationId") Long stationId, Model uiModel) {
		RentTransaction rentTransaction = new RentTransaction();
		rentTransaction.setFromStationId(stationId.intValue());
		rentTransaction.setStatus("checkedout");
		System.out.println("Tenant Id is ............... "+Utils.getLogonTenantId());
        rentTransaction.setTenantId(Utils.getLogonTenantId());
//		rentTransaction.setRateId(Integer);

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
    	
    	long programId = (long) 82;
    	Long userId = (long) 0;
    	List<Station> stations = new ArrayList<Station>();
    	stations = stationService.findAllStationsByProgram(programId);
    	uiModel.addAttribute("rentTransaction", new RentTransaction());
		uiModel.addAttribute("stations", stations);
		if(value==1)
		{
			userId = Utils.getLogonUser().getUserId();
			UserSubscriptionPolicy policy = new UserSubscriptionPolicy();
			policy = userPolicyService.findUserSubscriptionPolicyByUser(userId);
			
			RentTransaction rentTransaction = new RentTransaction(); 
			System.out.println("User Id is ............ "+userId);
			rentTransaction = rentTransactionService.findRentTransactionForCheckin(userId, "checkedout");
			System.out.println("Transaction is .............. "+rentTransaction);
			if(rentTransaction!=null)
				return "renttransactions/checkoutexists";
			else if(policy!=null)
			{
				System.out.println("Policy Id is ................. "+policy.getPolicyId().getId());
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
//            populateEditForm(uiModel, station);
            return "renttransactions/update";
        }
        uiModel.asMap().clear();
        System.out.println("Location is ........... "+rentTransaction.getFromStationId());
        return "redirect:/renttransactions/"+ rentTransaction.getFromStationId() +"/checkout?form"; //redirect:/
    }
    
    
    
	/* CHECKIN */
    @RequestMapping(value = "/checkin", params = "form", produces = "text/html")
    public String updateForm(Model uiModel) {
    	
    	long userId = (long) 3;
		String status="checkedout";		
		RentTransaction rentTransaction = rentTransactionService.findRentTransactionForCheckin(userId, status);
        populateUpdateForm(uiModel, rentTransaction);
        return "renttransactions/update";
    }
    
    
    /* CHECKIN */
    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String update(@Valid RentTransaction rentTransaction, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, rentTransaction);
            return "renttransactions/update";
        }
        uiModel.asMap().clear();
        RentTransaction rt = rentTransactionService.updateRentTransaction(rentTransaction);
        bikeLocationService.updateBikeLocation(rt.getBikeId().getId(), "Available", rt.getToStationId());
        return "redirect:/renttransactions/" + encodeUrlPathSegment(rentTransaction.getId().toString(), httpServletRequest);
    }
    
    /* CHECKOUT */
    void populateCheckoutForm(Model uiModel, RentTransaction rentTransaction, Long stationId) {
        uiModel.addAttribute("rentTransaction", rentTransaction);
        addDateTimeFormatPatterns(uiModel);
        System.out.println("Station Id is ............... "+stationId);
        System.out.println("Bikes are ... "+bikeService.findAvailableBikesByStation(stationId));
        uiModel.addAttribute("bikes", bikeService.findAvailableBikesByStation(stationId));
    }
    
    
    /* CHECKIN */
    void populateUpdateForm(Model uiModel, RentTransaction rentTransaction) {
        uiModel.addAttribute("rentTransaction", rentTransaction);
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("bikes", bikeService.findAllBikes());
        uiModel.addAttribute("bikeletusers", bikeLetUserService.findAllBikeLetUsers());
        try{
        	if(stationService.findAllStations()!=null)
        	{
        		System.out.println("Stations are ... "+stationService.findAllStations());
        		uiModel.addAttribute("stations", stationService.findAllStations());
        		uiModel.addAttribute("tostations", stationService.findAllStations());
        	}
        	else
        		System.out.println("Result is null ... ");
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        }
    }
    
}
