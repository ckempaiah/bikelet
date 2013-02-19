package com.sjsu.bikelet.web;

import com.sjsu.bikelet.domain.LicensePolicy;
import com.sjsu.bikelet.domain.Program;
import com.sjsu.bikelet.domain.Tenant;
import com.sjsu.bikelet.service.LicensePolicyService;
import com.sjsu.bikelet.service.ProgramService;
import com.sjsu.bikelet.service.TenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;
import org.springframework.roo.addon.web.mvc.controller.converter.RooConversionService;
import java.lang.Long;

/**
 * A central place to register application converters and formatters. 
 */
@RooConversionService
public class ApplicationConversionServiceFactoryBean extends FormattingConversionServiceFactoryBean {
    @Autowired
    private LicensePolicyService licensePolicyService;
    
    @Autowired
    TenantService tenantService;
    
    @Autowired
    ProgramService programService;

	@Override
	protected void installFormatters(FormatterRegistry registry) {
		super.installFormatters(registry);
		// Register application converters and formatters
	}

    public Converter<LicensePolicy, String> getLicensePolicyToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<com.sjsu.bikelet.domain.LicensePolicy, java.lang.String>() {
            public String convert(LicensePolicy licensePolicy) {
                return new StringBuilder().append(licensePolicy.getLicenseName()).append("-").append(licensePolicy.getLicenseType().name()).toString();
            }
        };
    }

    public Converter<Long, LicensePolicy> getIdToLicensePolicyConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, com.sjsu.bikelet.domain.LicensePolicy>() {
            public com.sjsu.bikelet.domain.LicensePolicy convert(java.lang.Long id) {
                return licensePolicyService.findLicensePolicy(id);
            }
        };
    }
    
    public Converter<Tenant, String> getTenantToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<com.sjsu.bikelet.domain.Tenant, java.lang.String>() {
            public String convert(Tenant tenant) {
                return new StringBuilder().append(tenant.getTenantName()).toString();
            }
        };
    }
    
    public Converter<String, Tenant> getStringToTenantConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, com.sjsu.bikelet.domain.Tenant>() {
            public com.sjsu.bikelet.domain.Tenant convert(String id) {
                return tenantService.findTenant(Long.valueOf(id));
            }
        };
    }
    
    public Converter<Program, String> getProgramToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<com.sjsu.bikelet.domain.Program, java.lang.String>() {
            public String convert(Program program) {
                return new StringBuilder().append(program.getProgramName()).toString();
            }
        };
    }
    
    public Converter<String, Program> getStringToProgramConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, com.sjsu.bikelet.domain.Program>() {
            public com.sjsu.bikelet.domain.Program convert(String id) {
                return programService.findProgram(Long.valueOf(id));
            }
        };
    }
   
}
