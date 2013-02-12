package com.sjsu.bikelet.service;


import com.sjsu.bikelet.domain.TenantLicensePolicy;
import com.sjsu.bikelet.exception.BikeletValidationException;
import org.springframework.util.CollectionUtils;


import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class TenantLicensePolicyServiceImpl implements TenantLicensePolicyService {

    public void saveTenantLicensePolicy(TenantLicensePolicy tenantLicensePolicy) {

        tenantLicensePolicy.persist();
    }

    @Override
    public List<TenantLicensePolicy> verifyLicensePolicyDates(TenantLicensePolicy tenantLicensePolicy) {

        TypedQuery<TenantLicensePolicy> licenseQuery = TenantLicensePolicy.entityManager().createQuery("from TenantLicensePolicy tlc " +
                " where ((tlc.licenseStartDate between :newStartDate and :newEndDate) or tlc.licenseEndDate >= :newEndDate) and tlc.tenantId.id= :tenantId", TenantLicensePolicy.class);
        licenseQuery.setParameter("newStartDate", tenantLicensePolicy.getLicenseStartDate());
        licenseQuery.setParameter("newEndDate", tenantLicensePolicy.getLicenseEndDate());
        licenseQuery.setParameter("tenantId", tenantLicensePolicy.getTenantId().getId());
        List<TenantLicensePolicy> activeLicensePolicy = licenseQuery.getResultList();
        return activeLicensePolicy;
    }
}
