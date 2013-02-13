package com.sjsu.bikelet.service;


import com.sjsu.bikelet.domain.BikeLetUser;
import com.sjsu.bikelet.domain.BikeLetRole;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;

public class BikeLetRoleServiceImpl implements BikeLetRoleService {

    public BikeLetRole findBikeLetRoleByName(String roleName) {
        return BikeLetRole.findBikeLetRoleByName(roleName);
    }
}
