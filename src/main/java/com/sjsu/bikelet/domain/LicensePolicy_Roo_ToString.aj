// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.sjsu.bikelet.domain;

import java.lang.String;

privileged aspect LicensePolicy_Roo_ToString {
    
    public String LicensePolicy.toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("FreeTrialPeriodInDays: ").append(getFreeTrialPeriodInDays()).append(", ");
        sb.append("Id: ").append(getId()).append(", ");
        sb.append("LicenseBaseCost: ").append(getLicenseBaseCost()).append(", ");
        sb.append("LicenseCostPerUser: ").append(getLicenseCostPerUser()).append(", ");
        sb.append("LicenseId: ").append(getLicenseId()).append(", ");
        sb.append("LicenseName: ").append(getLicenseName()).append(", ");
        sb.append("LicenseType: ").append(getLicenseType()).append(", ");
        sb.append("TenLicPolicy: ").append(getTenLicPolicy()).append(", ");
        sb.append("Version: ").append(getVersion());
        return sb.toString();
    }
    
}
