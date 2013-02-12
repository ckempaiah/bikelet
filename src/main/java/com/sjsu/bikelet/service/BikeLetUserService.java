package com.sjsu.bikelet.service;

import com.sjsu.bikelet.domain.BikeLetUser;
import org.springframework.roo.addon.layers.service.RooService;

@RooService(domainTypes = { com.sjsu.bikelet.domain.BikeLetUser.class })
public interface BikeLetUserService {
    BikeLetUser findBikeLetUser(String userName, String password);
}
