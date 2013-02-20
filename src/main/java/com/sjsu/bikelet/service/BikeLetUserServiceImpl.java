package com.sjsu.bikelet.service;


import com.sjsu.bikelet.domain.BikeLetRole;
import com.sjsu.bikelet.domain.BikeLetUser;
import com.sjsu.bikelet.domain.UserRole;
import com.sjsu.bikelet.exception.BikeletValidationException;
import com.sjsu.bikelet.model.BikeletRoleName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import javax.persistence.TypedQuery;
import java.util.List;

public class BikeLetUserServiceImpl implements BikeLetUserService {
    @Autowired
    private BikeLetRoleService bikeLetRoleService;
    @Override
    public BikeLetUser findBikeLetUser(String userName, String password) {

        TypedQuery<BikeLetUser> query = BikeLetUser.entityManager().createQuery("from BikeLetUser blu where blu.userName=:userName and blu.password=:password", BikeLetUser.class);
        query.setParameter("userName", userName);
        query.setParameter("password", password);
        return query.getSingleResult();
    }

    @Override
    public BikeLetUser createTenantAdminUser(BikeLetUser bikeLetUser) {

        BikeLetRole bikeletRole = bikeLetRoleService.findBikeLetRoleByName(BikeletRoleName.ROLE_TENANT.name());
        bikeLetUser.persist();
        UserRole userRole = new UserRole();
        userRole.setRoleId(bikeletRole);
        userRole.setUserId(bikeLetUser);
        userRole.persist();
        return bikeLetUser;
    }


    @Override
    public boolean isDuplicateName(String userName, Long userId){

        TypedQuery<BikeLetUser> query;
        if (userId != null) {
            query = BikeLetUser.entityManager().createQuery("from BikeLetUser blu where blu.userName=:userName and blu.id != :userId", BikeLetUser.class);
            query.setParameter("userName", userName);
            query.setParameter("userId", userId);
        } else {
            query = BikeLetUser.entityManager().createQuery("from BikeLetUser blu where blu.userName=:userName", BikeLetUser.class);
            query.setParameter("userName", userName);
        }
        List<BikeLetUser> result = query.getResultList();

        if (CollectionUtils.isEmpty(result)){
            return false;
        } else {
            return true;
        }

    }

    @Override
    public BikeLetUser getTenantAdmin(Long tenantId){
        TypedQuery<BikeLetUser> query;
        query = BikeLetUser.entityManager().createQuery("from BikeLetUser blu where blu.tenantId.id=:tenantId and (blu.programId.id is null or blu.programId.id=0)", BikeLetUser.class);
        query.setParameter("tenantId", tenantId);
        List<BikeLetUser> result = query.getResultList();

        if (CollectionUtils.isEmpty(result)){
            return null;
        } else {
            return result.get(0);
        }
    }
    
    public BikeLetUser findUserFromId(Long userId){
    	return BikeLetUser.getUserFromId(userId);
    }
}
