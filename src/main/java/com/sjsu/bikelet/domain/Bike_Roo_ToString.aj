// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.sjsu.bikelet.domain;

import java.lang.String;

privileged aspect Bike_Roo_ToString {
    
    public String Bike.toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("BikeId: ").append(getBikeId()).append(", ");
        sb.append("BikeLocation: ").append(getBikeLocation()).append(", ");
        sb.append("BikeType: ").append(getBikeType()).append(", ");
        sb.append("Bikecolor: ").append(getBikecolor()).append(", ");
        sb.append("Bikeheight: ").append(getBikeheight()).append(", ");
        sb.append("Bikestatus: ").append(getBikestatus()).append(", ");
        sb.append("Id: ").append(getId()).append(", ");
        sb.append("LastServiceDate: ").append(getLastServiceDate()).append(", ");
        sb.append("RentTransactions: ").append(getRentTransactions()).append(", ");
        sb.append("TenantId: ").append(getTenantId()).append(", ");
        sb.append("Version: ").append(getVersion()).append(", ");
        sb.append("WheelSize: ").append(getWheelSize());
        return sb.toString();
    }
    
}
