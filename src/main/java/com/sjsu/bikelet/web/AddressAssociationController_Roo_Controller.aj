// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.sjsu.bikelet.web;

import com.sjsu.bikelet.domain.AddressAssociation;
import com.sjsu.bikelet.web.AddressAssociationController;
import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

privileged aspect AddressAssociationController_Roo_Controller {
    
    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String AddressAssociationController.create(@Valid AddressAssociation addressAssociation, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, addressAssociation);
            return "addressassociations/create";
        }
        uiModel.asMap().clear();
        addressAssociation.persist();
        return "redirect:/addressassociations/" + encodeUrlPathSegment(addressAssociation.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(params = "form", produces = "text/html")
    public String AddressAssociationController.createForm(Model uiModel) {
        populateEditForm(uiModel, new AddressAssociation());
        return "addressassociations/create";
    }
    
    @RequestMapping(value = "/{id}", produces = "text/html")
    public String AddressAssociationController.show(@PathVariable("id") Long id, Model uiModel) {
        uiModel.addAttribute("addressassociation", AddressAssociation.findAddressAssociation(id));
        uiModel.addAttribute("itemId", id);
        return "addressassociations/show";
    }
    
    @RequestMapping(produces = "text/html")
    public String AddressAssociationController.list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("addressassociations", AddressAssociation.findAddressAssociationEntries(firstResult, sizeNo));
            float nrOfPages = (float) AddressAssociation.countAddressAssociations() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("addressassociations", AddressAssociation.findAllAddressAssociations());
        }
        return "addressassociations/list";
    }
    
    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String AddressAssociationController.update(@Valid AddressAssociation addressAssociation, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, addressAssociation);
            return "addressassociations/update";
        }
        uiModel.asMap().clear();
        addressAssociation.merge();
        return "redirect:/addressassociations/" + encodeUrlPathSegment(addressAssociation.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String AddressAssociationController.updateForm(@PathVariable("id") Long id, Model uiModel) {
        populateEditForm(uiModel, AddressAssociation.findAddressAssociation(id));
        return "addressassociations/update";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String AddressAssociationController.delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        AddressAssociation addressAssociation = AddressAssociation.findAddressAssociation(id);
        addressAssociation.remove();
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/addressassociations";
    }
    
    void AddressAssociationController.populateEditForm(Model uiModel, AddressAssociation addressAssociation) {
        uiModel.addAttribute("addressAssociation", addressAssociation);
    }
    
    String AddressAssociationController.encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
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
