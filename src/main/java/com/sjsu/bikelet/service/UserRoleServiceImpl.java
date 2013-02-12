package com.sjsu.bikelet.service;


import com.sjsu.bikelet.domain.UserRole;

import javax.persistence.TypedQuery;

public class UserRoleServiceImpl implements UserRoleService {

    @Override
    public UserRole findUserRoleByUserId(Long userId){

        TypedQuery<UserRole> query = UserRole.entityManager().createQuery("from UserRole ur where ur.userId.id=:userId",UserRole.class);
        query.setParameter("userId", userId);
        return query.getSingleResult();
    }
}
