// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.sjsu.bikelet.domain;

import java.lang.String;

privileged aspect Address_Roo_ToString {
    
    public String Address.toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("AddressId: ").append(getAddressId()).append(", ");
        sb.append("AddressLine1: ").append(getAddressLine1()).append(", ");
        sb.append("AddressLine2: ").append(getAddressLine2()).append(", ");
        sb.append("Address_type: ").append(getAddress_type()).append(", ");
        sb.append("CState: ").append(getCState()).append(", ");
        sb.append("Cellphone: ").append(getCellphone()).append(", ");
        sb.append("City: ").append(getCity()).append(", ");
        sb.append("Country: ").append(getCountry()).append(", ");
        sb.append("EntityId: ").append(getEntityId()).append(", ");
        sb.append("EntityType: ").append(getEntityType()).append(", ");
        sb.append("Id: ").append(getId()).append(", ");
        sb.append("Version: ").append(getVersion()).append(", ");
        sb.append("Workphone: ").append(getWorkphone()).append(", ");
        sb.append("ZipCode: ").append(getZipCode());
        return sb.toString();
    }
    
}
