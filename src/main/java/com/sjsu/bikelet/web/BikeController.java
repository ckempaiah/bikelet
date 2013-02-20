package com.sjsu.bikelet.web;

import com.sjsu.bikelet.domain.Bike;
import com.sjsu.bikelet.domain.BikeLocation;
import com.sjsu.bikelet.domain.Station;
import com.sjsu.bikelet.domain.Tenant;

import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.sjsu.bikelet.web.Utils;
import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import javax.validation.Valid;

import com.sjsu.bikelet.model.BikeStatusEnum;
import com.sjsu.bikelet.service.BikeService;
import com.sjsu.bikelet.service.TenantService;
import com.sjsu.bikelet.service.StationService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriUtils;

@RequestMapping("/bikes")
@Controller
@RooWebScaffold(path = "bikes", formBackingObject = Bike.class)
public class BikeController {
	
    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String create(@Valid Bike bike, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, bike);
            return "bikes/create";
        }
        uiModel.asMap().clear();
        bike.setTenantId(tenantService.findTenant(bike.getTenantId().getId()));	
        bikeService.saveBike(bike);
        
        // Create BikeLocation
        Station station = stationService.findStation(bike.getStation().getId());
        BikeLocation bikeLocation = new BikeLocation();
        bikeLocation.setBikeId(bike);
        bikeLocation.setStationId(station);
        bikeLocation.setBikeStatus(BikeStatusEnum.Available.toString());
        bikeLocationService.saveBikeLocation(bikeLocation);

        return "redirect:/bikes/" + encodeUrlPathSegment(bike.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(params = "form", produces = "text/html")
    public String createForm(Model uiModel) {
    	Long tenantId = Utils.getLogonTenantId();
    	Tenant tenant = tenantService.findTenant(tenantId);
    	Bike bike = new Bike();
    	bike.setTenantId(tenant);
        populateEditForm(uiModel, bike);
        return "bikes/create";
    }
    
    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String update(@Valid Bike bike, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, bike);
            return "bikes/update";
        }
        uiModel.asMap().clear();
        bike.setTenantId(tenantService.findTenant(bike.getTenantId().getId()));	
        bikeService.updateBike(bike);
        
        // Update BikeLocation
        Station station = stationService.findStation(bike.getStation().getId());
        BikeLocation bikeLocation = bikeLocationService.findBikeLocationOfBike(bike.getId());
        bikeLocation.setStationId(station);
        bikeLocationService.saveBikeLocation(bikeLocation);
        return "redirect:/bikes/" + encodeUrlPathSegment(bike.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String updateForm(@PathVariable("id") Long id, Model uiModel) {
    	Bike bike = bikeService.findBike(id);
    	BikeLocation bikeLocation = bikeLocationService.findBikeLocationOfBike(id);
    	if (bikeLocation != null)
    		bike.setStation(bikeLocation.getStationId());
        populateEditForm(uiModel, bike);
        return "bikes/update";
    }
    
    @RequestMapping(produces = "text/html")
    public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
    	Long tenantId = Utils.getLogonTenantId();
    	if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("bikes", bikeService.findBikeEntriesByTenant(tenantId, firstResult, sizeNo));
            float nrOfPages = (float) bikeService.countAllBikesByTenant(tenantId) / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("bikes", bikeService.findAllBikesByTenant(tenantId));
        }
        addDateTimeFormatPatterns(uiModel);
        return "bikes/list";
    }
    
    void populateEditForm(Model uiModel, Bike bike) {
    	Long tenantId = Utils.getLogonTenantId();
        uiModel.addAttribute("bike", bike);
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("stations", stationService.findAllStationsByTenant(tenantId));
    }
    
    @RequestMapping(value = "/{id}", produces = "text/html")
    public String show(@PathVariable("id") Long id, Model uiModel) {
        addDateTimeFormatPatterns(uiModel);
        Bike bike = bikeService.findBike(id);
        BikeLocation bikeLocation = bikeLocationService.findBikeLocationOfBike(id);
        if (bikeLocation != null) {
        	bike.setStation(bikeLocation.getStationId());
        }
        uiModel.addAttribute("bike", bikeService.findBike(id));
        uiModel.addAttribute("itemId", id);
        return "bikes/show";
    }
}
