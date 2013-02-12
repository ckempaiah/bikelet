package com.sjsu.bikelet.service;

import com.sjsu.bikelet.domain.TenantLicensePolicy;
import org.springframework.roo.addon.layers.service.RooService;

import java.util.List;

@RooService(domainTypes = { com.sjsu.bikelet.domain.TenantLicensePolicy.class })
public interface TenantLicensePolicyService {
    List<TenantLicensePolicy> verifyLicensePolicyDates(TenantLicensePolicy tenantLicensePolicy);
}
