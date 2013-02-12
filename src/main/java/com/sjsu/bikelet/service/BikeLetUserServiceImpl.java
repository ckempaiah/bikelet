package com.sjsu.bikelet.service;


import com.sjsu.bikelet.domain.BikeLetUser;

import javax.persistence.TypedQuery;

public class BikeLetUserServiceImpl implements BikeLetUserService {

    @Override
    public BikeLetUser findBikeLetUser(String userName, String password) {

        TypedQuery<BikeLetUser> query = BikeLetUser.entityManager().createQuery("from BikeLetUser blu where blu.userName=:userName and blu.password=:password", BikeLetUser.class);
        query.setParameter("userName", userName);
        query.setParameter("password", password);
        return query.getSingleResult();
    }
}
