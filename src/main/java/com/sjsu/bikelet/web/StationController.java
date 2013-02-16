package com.sjsu.bikelet.web;

import com.sjsu.bikelet.domain.BikeLetRole;
import com.sjsu.bikelet.domain.BikeLetUser;
import com.sjsu.bikelet.domain.Station;
import com.sjsu.bikelet.domain.Program;
import com.sjsu.bikelet.domain.Bike;
import com.sjsu.bikelet.domain.BikeLocation;
import com.sjsu.bikelet.domain.UserRole;
import com.sjsu.bikelet.model.BikeStatusEnum;
import com.sjsu.bikelet.domain.Tenant;
import com.sjsu.bikelet.web.Utils;
import java.util.ArrayList;
import java.util.List;
import com.sjsu.bikelet.web.Utils;
import com.sjsu.bikelet.service.ProgramService;
import com.sjsu.bikelet.service.StationService;
import com.sjsu.bikelet.service.TenantService;
import com.sjsu.bikelet.service.BikeService;
import com.sjsu.bikelet.service.BikeLocationService;
import com.sjsu.bikelet.web.StationController;
import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.joda.time.format.DateTimeFormat;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/stations")
@Controller
@RooWebScaffold(path = "stations", formBackingObject = Station.class)
public class StationController {
	
	
	@RequestMapping(method = RequestMethod.POST, produces = "text/html")
	public String create(@Valid Station station, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
	    if (bindingResult.hasErrors()) {
	        populateEditForm(uiModel, station);
	        return "stations/create";
	    }
	    uiModel.asMap().clear();
	    station.setTenantId(tenantService.findTenant(station.getTenantId().getId()));
	    stationService.saveStation(station);
	    return "redirect:/stations/" + encodeUrlPathSegment(station.getId().toString(), httpServletRequest);
	}
	
    @RequestMapping(produces = "text/html")
    public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
    	Long tenantId = Utils.getLogonTenantId();
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("stations", stationService.findStationEntriesByTenant(tenantId, firstResult, sizeNo));
            float nrOfPages = (float) stationService.countAllStationsByTenant(tenantId) / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("stations", stationService.findAllStationsByTenant(tenantId));
        }
        return "stations/list";
    }
    
    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String update(@Valid Station station, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, station);
            return "stations/update";
        }
        uiModel.asMap().clear();
        station.setTenantId(tenantService.findTenant(station.getTenantId().getId()));
        stationService.updateStation(station);
        return "redirect:/stations/" + encodeUrlPathSegment(station.getId().toString(), httpServletRequest);
    }

    void populateEditForm(Model uiModel, Station station) {
    	Long tenantId = Utils.getLogonTenantId();
        uiModel.addAttribute("station", station);
        uiModel.addAttribute("programs", programService.findAllProgramsByTenant(tenantId));
    }

    
    @RequestMapping(value = "/{id}/bikes",method = RequestMethod.POST, produces = "text/html")
    public String createBike(@PathVariable("id") Long id, @Valid Bike bike, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
        	uiModel.addAttribute("stationId", id);
            populateEditBikeForm(uiModel, bike);
            return "stations/bikes/create";
        }
        uiModel.asMap().clear();
        bike.setTenantId(tenantService.findTenant(bike.getTenantId().getId()));	
        bikeService.saveBike(bike);
        // Create BikeLocation
        Station station = stationService.findStation(id);
        BikeLocation bikeLocation = new BikeLocation();
        bikeLocation.setBikeId(bike);
        bikeLocation.setStationId(station);
        bikeLocation.setBikeStatus(BikeStatusEnum.Available.toString());
        bikeLocationService.saveBikeLocation(bikeLocation);

        return "redirect:/stations/" + id + "/bikes/" + encodeUrlPathSegment(bike.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(value = "/{id}/bikes", params = "form", produces = "text/html")
    public String createBikeForm(@PathVariable("id") Long id,  Model uiModel) {
    	Long tenantId = Utils.getLogonTenantId();
    	Tenant tenant = tenantService.findTenant(tenantId);
    	Bike bike = new Bike();
    	bike.setTenantId(tenant);
    	uiModel.addAttribute("stationId", id);
        populateEditBikeForm(uiModel, bike);
        return "stations/bikes/create";
    }
    
    @RequestMapping(value = "/{id}/bikes/{bikeId}", produces = "text/html")
    public String showBike(@PathVariable("id") Long id, @PathVariable("bikeId") Long bikeId, Model uiModel) {
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("bike", bikeService.findBike(bikeId));
        uiModel.addAttribute("itemId", bikeId);
        uiModel.addAttribute("stationId", id);
        return "stations/bikes/show";
    }
    
    @RequestMapping(value = "/{stationId}/bikes", produces = "text/html")
    public String listBike(@PathVariable("stationId") Long stationId, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("bikes", bikeService.findBikeEntriesByStation(stationId, firstResult, sizeNo));
            float nrOfPages = (float) bikeService.countAllBikesByStation(stationId) / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("bikes", bikeService.findAllBikesByStation(stationId));
        }
        uiModel.addAttribute("stationId", stationId);
        addDateTimeFormatPatterns(uiModel);
        return "stations/bikes/list";
    }
    
    @RequestMapping(value = "/{stationId}/bikes", method = RequestMethod.PUT, produces = "text/html")
    public String updateBike(@Valid Bike bike, @PathVariable("stationId") Long stationId, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
        	uiModel.addAttribute("stationId", stationId);
            populateEditBikeForm(uiModel, bike);
            return "stations/bikes/update";
        }
        uiModel.asMap().clear();
        bike.setTenantId(tenantService.findTenant(bike.getTenantId().getId()));
        bikeService.updateBike(bike);
        return "redirect:/stations/" + stationId + "/bikes/" + encodeUrlPathSegment(bike.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(value = "/{id}/bikes/{bikeId}", params = "form", produces = "text/html")
    public String updateBikeForm(@PathVariable("id") Long id, @PathVariable("bikeId") Long bikeId, Model uiModel) {
    	uiModel.addAttribute("stationId", id);
        populateEditBikeForm(uiModel, bikeService.findBike(bikeId));
        return "stations/bikes/update";
    }
    
    @RequestMapping(value = "/{id}/bikes/{bikeId}", method = RequestMethod.DELETE, produces = "text/html")
    public String deleteBike(@PathVariable("id") Long id, @PathVariable("bikeId") Long bikeId, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        Bike bike = bikeService.findBike(bikeId);
        bikeService.deleteBike(bike);
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/stations/" + id + "/bikes";
    }
    
    void addDateTimeFormatPatterns(Model uiModel) {
        uiModel.addAttribute("bike_lastservicedate_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
    }
    
    void populateEditBikeForm(Model uiModel, Bike bike) {
        uiModel.addAttribute("bike", bike);
        addDateTimeFormatPatterns(uiModel);
    }
}
