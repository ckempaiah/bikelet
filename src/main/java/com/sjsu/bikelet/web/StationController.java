package com.sjsu.bikelet.web;

import com.sjsu.bikelet.domain.Station;
import com.sjsu.bikelet.domain.Program;
import java.util.List;
import com.sjsu.bikelet.web.Utils;
import com.sjsu.bikelet.service.ProgramService;
import com.sjsu.bikelet.service.StationService;
import com.sjsu.bikelet.service.TenantService;
import com.sjsu.bikelet.web.StationController;
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
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/stations")
@Controller
@RooWebScaffold(path = "stations", formBackingObject = Station.class)
public class StationController {

    @Autowired
	StationService stationService;
	    
	@Autowired
	ProgramService programService;
	    
	@Autowired
	TenantService tenantService;
	    
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
	    
    @RequestMapping(params = "form", produces = "text/html")
    public String createForm(Model uiModel) {
    	Long tenantId = Utils.getLogonTenantId();
    	Station station = new Station();
    	station.setTenantId(tenantService.findTenant(tenantId));
        populateEditForm(uiModel, station);
        return "stations/create";
    }
	    
    @RequestMapping(value = "/{id}", produces = "text/html")
    public String show(@PathVariable("id") Long id, Model uiModel) {
        uiModel.addAttribute("station", stationService.findStation(id));
        uiModel.addAttribute("itemId", id);
        return "stations/show";
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
	    
    @RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String updateForm(@PathVariable("id") Long id, Model uiModel) {
        populateEditForm(uiModel, stationService.findStation(id));
        return "stations/update";
    }
	    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        Station station = stationService.findStation(id);
        stationService.deleteStation(station);
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/stations";
    }
	    
    void populateEditForm(Model uiModel, Station station) {
    	Long tenantId = Utils.getLogonTenantId();
        uiModel.addAttribute("station", station);
        uiModel.addAttribute("programs", programService.findAllProgramsByTenant(tenantId));
    }
	    
    String encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
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
