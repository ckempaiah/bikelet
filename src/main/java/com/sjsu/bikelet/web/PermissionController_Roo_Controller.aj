// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.sjsu.bikelet.web;

import com.sjsu.bikelet.domain.Permission;
import com.sjsu.bikelet.service.PermissionService;
import com.sjsu.bikelet.web.PermissionController;
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

privileged aspect PermissionController_Roo_Controller {
    
    @Autowired
    PermissionService PermissionController.permissionService;
    
    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String PermissionController.create(@Valid Permission permission, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, permission);
            return "permissions/create";
        }
        uiModel.asMap().clear();
        permissionService.savePermission(permission);
        return "redirect:/permissions/" + encodeUrlPathSegment(permission.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(params = "form", produces = "text/html")
    public String PermissionController.createForm(Model uiModel) {
        populateEditForm(uiModel, new Permission());
        return "permissions/create";
    }
    
    @RequestMapping(value = "/{id}", produces = "text/html")
    public String PermissionController.show(@PathVariable("id") Long id, Model uiModel) {
        uiModel.addAttribute("permission", permissionService.findPermission(id));
        uiModel.addAttribute("itemId", id);
        return "permissions/show";
    }
    
    @RequestMapping(produces = "text/html")
    public String PermissionController.list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("permissions", permissionService.findPermissionEntries(firstResult, sizeNo));
            float nrOfPages = (float) permissionService.countAllPermissions() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("permissions", permissionService.findAllPermissions());
        }
        return "permissions/list";
    }
    
    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String PermissionController.update(@Valid Permission permission, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, permission);
            return "permissions/update";
        }
        uiModel.asMap().clear();
        permissionService.updatePermission(permission);
        return "redirect:/permissions/" + encodeUrlPathSegment(permission.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String PermissionController.updateForm(@PathVariable("id") Long id, Model uiModel) {
        populateEditForm(uiModel, permissionService.findPermission(id));
        return "permissions/update";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String PermissionController.delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        Permission permission = permissionService.findPermission(id);
        permissionService.deletePermission(permission);
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/permissions";
    }
    
    void PermissionController.populateEditForm(Model uiModel, Permission permission) {
        uiModel.addAttribute("permission", permission);
    }
    
    String PermissionController.encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
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
