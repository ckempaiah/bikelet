// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.sjsu.bikelet.domain;

import java.lang.String;

privileged aspect SubscriptionRate_Roo_ToString {
    
    public String SubscriptionRate.toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ExcessChargePerMin: ").append(getExcessChargePerMin()).append(", ");
        sb.append("Id: ").append(getId()).append(", ");
        sb.append("MembershipPerMonth: ").append(getMembershipPerMonth()).append(", ");
        sb.append("OrganizationShare: ").append(getOrganizationShare()).append(", ");
        sb.append("PolicyEndDate: ").append(getPolicyEndDate()).append(", ");
        sb.append("PolicyStartDate: ").append(getPolicyStartDate()).append(", ");
        sb.append("RateId: ").append(getRateId()).append(", ");
        sb.append("UserShare: ").append(getUserShare()).append(", ");
        sb.append("Version: ").append(getVersion());
        return sb.toString();
    }
    
}
