package com.sjsu.bikelet.service;

import com.sjsu.bikelet.domain.UserRole;
import org.springframework.roo.addon.layers.service.RooService;

@RooService(domainTypes = { com.sjsu.bikelet.domain.UserRole.class })
public interface UserRoleService {
    UserRole findUserRoleByUserId(Long userId);
}
