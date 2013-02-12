package com.sjsu.bikelet.web;

import com.sjsu.bikelet.domain.LicensePolicy;
import com.sjsu.bikelet.service.LicensePolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;
import org.springframework.roo.addon.web.mvc.controller.converter.RooConversionService;

/**
 * A central place to register application converters and formatters. 
 */
@RooConversionService
public class ApplicationConversionServiceFactoryBean extends FormattingConversionServiceFactoryBean {
    @Autowired
    private LicensePolicyService licensePolicyService;

//	@Override
//	protected void installFormatters(FormatterRegistry registry) {
//		super.installFormatters(registry);
//		// Register application converters and formatters
//	}

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
}
