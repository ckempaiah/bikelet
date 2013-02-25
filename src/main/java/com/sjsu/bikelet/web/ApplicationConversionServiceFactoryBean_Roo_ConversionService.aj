// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.sjsu.bikelet.web;

import com.sjsu.bikelet.domain.Address;
import com.sjsu.bikelet.domain.AddressAssociation;
import com.sjsu.bikelet.domain.Bike;
import com.sjsu.bikelet.domain.BikeLetRole;
import com.sjsu.bikelet.domain.BikeLetUser;
import com.sjsu.bikelet.domain.BikeLocation;
import com.sjsu.bikelet.domain.Bill;
import com.sjsu.bikelet.domain.BillTransaction;
import com.sjsu.bikelet.domain.LicensePolicy;
import com.sjsu.bikelet.domain.PaymentInfo;
import com.sjsu.bikelet.domain.PaymentTransaction;
import com.sjsu.bikelet.domain.Permission;
import com.sjsu.bikelet.domain.Program;
import com.sjsu.bikelet.domain.RentTransaction;
import com.sjsu.bikelet.domain.RolePermission;
import com.sjsu.bikelet.domain.Station;
import com.sjsu.bikelet.domain.SubscriptionPolicy;
import com.sjsu.bikelet.domain.SubscriptionRate;
import com.sjsu.bikelet.domain.Tenant;
import com.sjsu.bikelet.domain.TenantLicensePolicy;
import com.sjsu.bikelet.domain.UserRole;
import com.sjsu.bikelet.domain.UserSubscriptionPolicy;
import com.sjsu.bikelet.service.AddressAssociationService;
import com.sjsu.bikelet.service.AddressService;
import com.sjsu.bikelet.service.BikeLetRoleService;
import com.sjsu.bikelet.service.BikeLetUserService;
import com.sjsu.bikelet.service.BikeLocationService;
import com.sjsu.bikelet.service.BikeService;
import com.sjsu.bikelet.service.BillService;
import com.sjsu.bikelet.service.BillTransactionService;
import com.sjsu.bikelet.service.PaymentInfoService;
import com.sjsu.bikelet.service.PaymentTransactionService;
import com.sjsu.bikelet.service.PermissionService;
import com.sjsu.bikelet.service.ProgramService;
import com.sjsu.bikelet.service.RentTransactionService;
import com.sjsu.bikelet.service.RolePermissionService;
import com.sjsu.bikelet.service.StationService;
import com.sjsu.bikelet.service.SubscriptionPolicyService;
import com.sjsu.bikelet.service.SubscriptionRateService;
import com.sjsu.bikelet.service.TenantLicensePolicyService;
import com.sjsu.bikelet.service.TenantService;
import com.sjsu.bikelet.service.UserRoleService;
import com.sjsu.bikelet.service.UserSubscriptionPolicyService;
import com.sjsu.bikelet.web.ApplicationConversionServiceFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;

privileged aspect ApplicationConversionServiceFactoryBean_Roo_ConversionService {
    
    declare @type: ApplicationConversionServiceFactoryBean: @Configurable;
    
    @Autowired
    AddressService ApplicationConversionServiceFactoryBean.addressService;
    
    @Autowired
    AddressAssociationService ApplicationConversionServiceFactoryBean.addressAssociationService;
    
    @Autowired
    BikeService ApplicationConversionServiceFactoryBean.bikeService;
    
    @Autowired
    BikeLetRoleService ApplicationConversionServiceFactoryBean.bikeLetRoleService;
    

    
    @Autowired
    BikeLocationService ApplicationConversionServiceFactoryBean.bikeLocationService;
    
    @Autowired
    BillService ApplicationConversionServiceFactoryBean.billService;
    
    @Autowired
    BillTransactionService ApplicationConversionServiceFactoryBean.billTransactionService;
    
    @Autowired
    PaymentInfoService ApplicationConversionServiceFactoryBean.paymentInfoService;
    
    @Autowired
    PaymentTransactionService ApplicationConversionServiceFactoryBean.paymentTransactionService;
    
    @Autowired
    PermissionService ApplicationConversionServiceFactoryBean.permissionService;
   
    @Autowired
    RentTransactionService ApplicationConversionServiceFactoryBean.rentTransactionService;
    
    @Autowired
    RolePermissionService ApplicationConversionServiceFactoryBean.rolePermissionService;

    @Autowired
    SubscriptionRateService ApplicationConversionServiceFactoryBean.subscriptionRateService;
    

    
    @Autowired
    TenantLicensePolicyService ApplicationConversionServiceFactoryBean.tenantLicensePolicyService;
    
    @Autowired
    UserRoleService ApplicationConversionServiceFactoryBean.userRoleService;
    
    @Autowired
    UserSubscriptionPolicyService ApplicationConversionServiceFactoryBean.userSubscriptionPolicyService;
    
    public Converter<Address, String> ApplicationConversionServiceFactoryBean.getAddressToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<com.sjsu.bikelet.domain.Address, java.lang.String>() {
            public String convert(Address address) {
                return new StringBuilder().append(address.getAddressLine1()).append(' ').append(address.getAddressLine2()).append(' ').append(address.getCity()).append(' ').append(address.getAddressState()).toString();
            }
        };
    }
    
    public Converter<Long, Address> ApplicationConversionServiceFactoryBean.getIdToAddressConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, com.sjsu.bikelet.domain.Address>() {
            public com.sjsu.bikelet.domain.Address convert(java.lang.Long id) {
                return addressService.findAddress(id);
            }
        };
    }
    
    public Converter<String, Address> ApplicationConversionServiceFactoryBean.getStringToAddressConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, com.sjsu.bikelet.domain.Address>() {
            public com.sjsu.bikelet.domain.Address convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), Address.class);
            }
        };
    }
    
    public Converter<AddressAssociation, String> ApplicationConversionServiceFactoryBean.getAddressAssociationToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<com.sjsu.bikelet.domain.AddressAssociation, java.lang.String>() {
            public String convert(AddressAssociation addressAssociation) {
                return new StringBuilder().append(addressAssociation.getEntityId()).append(' ').append(addressAssociation.getAddressId()).append(' ').append(addressAssociation.getEntityType()).toString();
            }
        };
    }
    
    public Converter<Long, AddressAssociation> ApplicationConversionServiceFactoryBean.getIdToAddressAssociationConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, com.sjsu.bikelet.domain.AddressAssociation>() {
            public com.sjsu.bikelet.domain.AddressAssociation convert(java.lang.Long id) {
                return addressAssociationService.findAddressAssociation(id);
            }
        };
    }
    
    public Converter<String, AddressAssociation> ApplicationConversionServiceFactoryBean.getStringToAddressAssociationConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, com.sjsu.bikelet.domain.AddressAssociation>() {
            public com.sjsu.bikelet.domain.AddressAssociation convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), AddressAssociation.class);
            }
        };
    }
    
    public Converter<Bike, String> ApplicationConversionServiceFactoryBean.getBikeToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<com.sjsu.bikelet.domain.Bike, java.lang.String>() {
            public String convert(Bike bike) {
                return new StringBuilder().append(bike.getBikeHeight()).append(' ').append(bike.getBikeColor()).append(' ').append(bike.getBikeType()).append(' ').append(bike.getLastServiceDate()).toString();
            }
        };
    }
    
    public Converter<Long, Bike> ApplicationConversionServiceFactoryBean.getIdToBikeConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, com.sjsu.bikelet.domain.Bike>() {
            public com.sjsu.bikelet.domain.Bike convert(java.lang.Long id) {
                return bikeService.findBike(id);
            }
        };
    }
    
    public Converter<String, Bike> ApplicationConversionServiceFactoryBean.getStringToBikeConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, com.sjsu.bikelet.domain.Bike>() {
            public com.sjsu.bikelet.domain.Bike convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), Bike.class);
            }
        };
    }
    
    public Converter<BikeLetRole, String> ApplicationConversionServiceFactoryBean.getBikeLetRoleToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<com.sjsu.bikelet.domain.BikeLetRole, java.lang.String>() {
            public String convert(BikeLetRole bikeLetRole) {
                return new StringBuilder().append(bikeLetRole.getRoleName()).toString();
            }
        };
    }
    
    public Converter<Long, BikeLetRole> ApplicationConversionServiceFactoryBean.getIdToBikeLetRoleConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, com.sjsu.bikelet.domain.BikeLetRole>() {
            public com.sjsu.bikelet.domain.BikeLetRole convert(java.lang.Long id) {
                return bikeLetRoleService.findBikeLetRole(id);
            }
        };
    }
    
    public Converter<String, BikeLetRole> ApplicationConversionServiceFactoryBean.getStringToBikeLetRoleConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, com.sjsu.bikelet.domain.BikeLetRole>() {
            public com.sjsu.bikelet.domain.BikeLetRole convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), BikeLetRole.class);
            }
        };
    }
    
    public Converter<BikeLetUser, String> ApplicationConversionServiceFactoryBean.getBikeLetUserToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<com.sjsu.bikelet.domain.BikeLetUser, java.lang.String>() {
            public String convert(BikeLetUser bikeLetUser) {
                return new StringBuilder().append(bikeLetUser.getFirstName()).append(' ').append(bikeLetUser.getLastName()).append(' ').append(bikeLetUser.getEmail()).append(' ').append(bikeLetUser.getPassword()).toString();
            }
        };
    }
    
    public Converter<Long, BikeLetUser> ApplicationConversionServiceFactoryBean.getIdToBikeLetUserConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, com.sjsu.bikelet.domain.BikeLetUser>() {
            public com.sjsu.bikelet.domain.BikeLetUser convert(java.lang.Long id) {
                return bikeLetUserService.findBikeLetUser(id);
            }
        };
    }
    
    public Converter<BikeLocation, String> ApplicationConversionServiceFactoryBean.getBikeLocationToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<com.sjsu.bikelet.domain.BikeLocation, java.lang.String>() {
            public String convert(BikeLocation bikeLocation) {
                return new StringBuilder().append(bikeLocation.getBikeStatus()).toString();
            }
        };
    }
    
    public Converter<Long, BikeLocation> ApplicationConversionServiceFactoryBean.getIdToBikeLocationConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, com.sjsu.bikelet.domain.BikeLocation>() {
            public com.sjsu.bikelet.domain.BikeLocation convert(java.lang.Long id) {
                return bikeLocationService.findBikeLocation(id);
            }
        };
    }
    
    public Converter<String, BikeLocation> ApplicationConversionServiceFactoryBean.getStringToBikeLocationConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, com.sjsu.bikelet.domain.BikeLocation>() {
            public com.sjsu.bikelet.domain.BikeLocation convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), BikeLocation.class);
            }
        };
    }
    
    public Converter<Bill, String> ApplicationConversionServiceFactoryBean.getBillToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<com.sjsu.bikelet.domain.Bill, java.lang.String>() {
            public String convert(Bill bill) {
                return new StringBuilder().append(bill.getTotalcharges()).append(' ').append(bill.getDescription()).append(' ').append(bill.getCreatedDate()).append(' ').append(bill.getBillStartDate()).toString();
            }
        };
    }
    
    public Converter<Long, Bill> ApplicationConversionServiceFactoryBean.getIdToBillConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, com.sjsu.bikelet.domain.Bill>() {
            public com.sjsu.bikelet.domain.Bill convert(java.lang.Long id) {
                return billService.findBill(id);
            }
        };
    }
    
    public Converter<String, Bill> ApplicationConversionServiceFactoryBean.getStringToBillConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, com.sjsu.bikelet.domain.Bill>() {
            public com.sjsu.bikelet.domain.Bill convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), Bill.class);
            }
        };
    }
    
    public Converter<BillTransaction, String> ApplicationConversionServiceFactoryBean.getBillTransactionToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<com.sjsu.bikelet.domain.BillTransaction, java.lang.String>() {
            public String convert(BillTransaction billTransaction) {
                return new StringBuilder().append(billTransaction.getTotalCost()).append(' ').append(billTransaction.getTransactionType()).append(' ').append(billTransaction.getStartDate()).append(' ').append(billTransaction.getEndDate()).toString();
            }
        };
    }
    
    public Converter<Long, BillTransaction> ApplicationConversionServiceFactoryBean.getIdToBillTransactionConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, com.sjsu.bikelet.domain.BillTransaction>() {
            public com.sjsu.bikelet.domain.BillTransaction convert(java.lang.Long id) {
                return billTransactionService.findBillTransaction(id);
            }
        };
    }
    
    public Converter<String, BillTransaction> ApplicationConversionServiceFactoryBean.getStringToBillTransactionConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, com.sjsu.bikelet.domain.BillTransaction>() {
            public com.sjsu.bikelet.domain.BillTransaction convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), BillTransaction.class);
            }
        };
    }
    
    public Converter<String, LicensePolicy> ApplicationConversionServiceFactoryBean.getStringToLicensePolicyConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, com.sjsu.bikelet.domain.LicensePolicy>() {
            public com.sjsu.bikelet.domain.LicensePolicy convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), LicensePolicy.class);
            }
        };
    }
    
    public Converter<PaymentInfo, String> ApplicationConversionServiceFactoryBean.getPaymentInfoToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<com.sjsu.bikelet.domain.PaymentInfo, java.lang.String>() {
            public String convert(PaymentInfo paymentInfo) {
                return new StringBuilder().append(paymentInfo.getId()).append(' ').append(paymentInfo.getCardNumber()).append(' ').append(paymentInfo.getCardUserName()).toString();
            }
        };
    }
    
    public Converter<Long, PaymentInfo> ApplicationConversionServiceFactoryBean.getIdToPaymentInfoConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, com.sjsu.bikelet.domain.PaymentInfo>() {
            public com.sjsu.bikelet.domain.PaymentInfo convert(java.lang.Long id) {
                return paymentInfoService.findPaymentInfo(id);
            }
        };
    }
    
    public Converter<String, PaymentInfo> ApplicationConversionServiceFactoryBean.getStringToPaymentInfoConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, com.sjsu.bikelet.domain.PaymentInfo>() {
            public com.sjsu.bikelet.domain.PaymentInfo convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), PaymentInfo.class);
            }
        };
    }
    
    public Converter<PaymentTransaction, String> ApplicationConversionServiceFactoryBean.getPaymentTransactionToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<com.sjsu.bikelet.domain.PaymentTransaction, java.lang.String>() {
            public String convert(PaymentTransaction paymentTransaction) {
                return new StringBuilder().append(paymentTransaction.getStatus()).append(' ').append(paymentTransaction.getDateOfTransaction()).append(' ').append(paymentTransaction.getPermissionName()).append(' ').append(paymentTransaction.getDescription()).toString();
            }
        };
    }
    
    public Converter<Long, PaymentTransaction> ApplicationConversionServiceFactoryBean.getIdToPaymentTransactionConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, com.sjsu.bikelet.domain.PaymentTransaction>() {
            public com.sjsu.bikelet.domain.PaymentTransaction convert(java.lang.Long id) {
                return paymentTransactionService.findPaymentTransaction(id);
            }
        };
    }
    
    public Converter<String, PaymentTransaction> ApplicationConversionServiceFactoryBean.getStringToPaymentTransactionConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, com.sjsu.bikelet.domain.PaymentTransaction>() {
            public com.sjsu.bikelet.domain.PaymentTransaction convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), PaymentTransaction.class);
            }
        };
    }
    
    public Converter<Permission, String> ApplicationConversionServiceFactoryBean.getPermissionToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<com.sjsu.bikelet.domain.Permission, java.lang.String>() {
            public String convert(Permission permission) {
                return new StringBuilder().append(permission.getPermissionName()).append(' ').append(permission.getDescription()).toString();
            }
        };
    }
    
    public Converter<Long, Permission> ApplicationConversionServiceFactoryBean.getIdToPermissionConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, com.sjsu.bikelet.domain.Permission>() {
            public com.sjsu.bikelet.domain.Permission convert(java.lang.Long id) {
                return permissionService.findPermission(id);
            }
        };
    }
    
    public Converter<String, Permission> ApplicationConversionServiceFactoryBean.getStringToPermissionConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, com.sjsu.bikelet.domain.Permission>() {
            public com.sjsu.bikelet.domain.Permission convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), Permission.class);
            }
        };
    }
    
    public Converter<Long, Program> ApplicationConversionServiceFactoryBean.getIdToProgramConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, com.sjsu.bikelet.domain.Program>() {
            public com.sjsu.bikelet.domain.Program convert(java.lang.Long id) {
                return programService.findProgram(id);
            }
        };
    }
     
    public Converter<RentTransaction, String> ApplicationConversionServiceFactoryBean.getRentTransactionToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<com.sjsu.bikelet.domain.RentTransaction, java.lang.String>() {
            public String convert(RentTransaction rentTransaction) {
                return new StringBuilder().append(rentTransaction.getFromStationId()).append(' ').append(rentTransaction.getToStationId()).append(' ').append(rentTransaction.getTenantId()).append(' ').append(rentTransaction.getRentStartDate()).toString();
            }
        };
    }
    
    public Converter<Long, RentTransaction> ApplicationConversionServiceFactoryBean.getIdToRentTransactionConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, com.sjsu.bikelet.domain.RentTransaction>() {
            public com.sjsu.bikelet.domain.RentTransaction convert(java.lang.Long id) {
                return rentTransactionService.findRentTransaction(id);
            }
        };
    }
    
    public Converter<String, RentTransaction> ApplicationConversionServiceFactoryBean.getStringToRentTransactionConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, com.sjsu.bikelet.domain.RentTransaction>() {
            public com.sjsu.bikelet.domain.RentTransaction convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), RentTransaction.class);
            }
        };
    }
    
    public Converter<RolePermission, String> ApplicationConversionServiceFactoryBean.getRolePermissionToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<com.sjsu.bikelet.domain.RolePermission, java.lang.String>() {
            public String convert(RolePermission rolePermission) {
                return "(no displayable fields)";
            }
        };
    }
    
    public Converter<Long, RolePermission> ApplicationConversionServiceFactoryBean.getIdToRolePermissionConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, com.sjsu.bikelet.domain.RolePermission>() {
            public com.sjsu.bikelet.domain.RolePermission convert(java.lang.Long id) {
                return rolePermissionService.findRolePermission(id);
            }
        };
    }
    
    public Converter<String, RolePermission> ApplicationConversionServiceFactoryBean.getStringToRolePermissionConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, com.sjsu.bikelet.domain.RolePermission>() {
            public com.sjsu.bikelet.domain.RolePermission convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), RolePermission.class);
            }
        };
    }
    
    public Converter<Long, Station> ApplicationConversionServiceFactoryBean.getIdToStationConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, com.sjsu.bikelet.domain.Station>() {
            public com.sjsu.bikelet.domain.Station convert(java.lang.Long id) {
                return stationService.findStation(id);
            }
        };
    }
    
    public Converter<Long, SubscriptionPolicy> ApplicationConversionServiceFactoryBean.getIdToSubscriptionPolicyConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, com.sjsu.bikelet.domain.SubscriptionPolicy>() {
            public com.sjsu.bikelet.domain.SubscriptionPolicy convert(java.lang.Long id) {
                return subscriptionPolicyService.findSubscriptionPolicy(id);
            }
        };
    }

    
    public Converter<SubscriptionRate, String> ApplicationConversionServiceFactoryBean.getSubscriptionRateToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<com.sjsu.bikelet.domain.SubscriptionRate, java.lang.String>() {
            public String convert(SubscriptionRate subscriptionRate) {
                return new StringBuilder().append(subscriptionRate.getMembershipPerMonth()).append(' ').append(subscriptionRate.getOrganizationShare()).append(' ').append(subscriptionRate.getUserShare()).append(' ').append(subscriptionRate.getFreeMinsPerDay()).toString();
            }
        };
    }
    
    public Converter<Long, SubscriptionRate> ApplicationConversionServiceFactoryBean.getIdToSubscriptionRateConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, com.sjsu.bikelet.domain.SubscriptionRate>() {
            public com.sjsu.bikelet.domain.SubscriptionRate convert(java.lang.Long id) {
                return subscriptionRateService.findSubscriptionRate(id);
            }
        };
    }
    
    public Converter<String, SubscriptionRate> ApplicationConversionServiceFactoryBean.getStringToSubscriptionRateConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, com.sjsu.bikelet.domain.SubscriptionRate>() {
            public com.sjsu.bikelet.domain.SubscriptionRate convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), SubscriptionRate.class);
            }
        };
    }
    
    public Converter<Long, Tenant> ApplicationConversionServiceFactoryBean.getIdToTenantConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, com.sjsu.bikelet.domain.Tenant>() {
            public com.sjsu.bikelet.domain.Tenant convert(java.lang.Long id) {
                return tenantService.findTenant(id);
            }
        };
    }
    
    public Converter<TenantLicensePolicy, String> ApplicationConversionServiceFactoryBean.getTenantLicensePolicyToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<com.sjsu.bikelet.domain.TenantLicensePolicy, java.lang.String>() {
            public String convert(TenantLicensePolicy tenantLicensePolicy) {
                return new StringBuilder().append(tenantLicensePolicy.getLicenseStartDate()).append(' ').append(tenantLicensePolicy.getLicenseEndDate()).toString();
            }
        };
    }
    
    public Converter<Long, TenantLicensePolicy> ApplicationConversionServiceFactoryBean.getIdToTenantLicensePolicyConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, com.sjsu.bikelet.domain.TenantLicensePolicy>() {
            public com.sjsu.bikelet.domain.TenantLicensePolicy convert(java.lang.Long id) {
                return tenantLicensePolicyService.findTenantLicensePolicy(id);
            }
        };
    }
    
    public Converter<String, TenantLicensePolicy> ApplicationConversionServiceFactoryBean.getStringToTenantLicensePolicyConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, com.sjsu.bikelet.domain.TenantLicensePolicy>() {
            public com.sjsu.bikelet.domain.TenantLicensePolicy convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), TenantLicensePolicy.class);
            }
        };
    }
    
    public Converter<UserRole, String> ApplicationConversionServiceFactoryBean.getUserRoleToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<com.sjsu.bikelet.domain.UserRole, java.lang.String>() {
            public String convert(UserRole userRole) {
                return "(no displayable fields)";
            }
        };
    }
    
    public Converter<Long, UserRole> ApplicationConversionServiceFactoryBean.getIdToUserRoleConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, com.sjsu.bikelet.domain.UserRole>() {
            public com.sjsu.bikelet.domain.UserRole convert(java.lang.Long id) {
                return userRoleService.findUserRole(id);
            }
        };
    }
    
    public Converter<String, UserRole> ApplicationConversionServiceFactoryBean.getStringToUserRoleConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, com.sjsu.bikelet.domain.UserRole>() {
            public com.sjsu.bikelet.domain.UserRole convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), UserRole.class);
            }
        };
    }
    
    public Converter<UserSubscriptionPolicy, String> ApplicationConversionServiceFactoryBean.getUserSubscriptionPolicyToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<com.sjsu.bikelet.domain.UserSubscriptionPolicy, java.lang.String>() {
            public String convert(UserSubscriptionPolicy userSubscriptionPolicy) {
                return "(no displayable fields)";
            }
        };
    }
    
    public Converter<Long, UserSubscriptionPolicy> ApplicationConversionServiceFactoryBean.getIdToUserSubscriptionPolicyConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, com.sjsu.bikelet.domain.UserSubscriptionPolicy>() {
            public com.sjsu.bikelet.domain.UserSubscriptionPolicy convert(java.lang.Long id) {
                return userSubscriptionPolicyService.findUserSubscriptionPolicy(id);
            }
        };
    }
    
    public Converter<String, UserSubscriptionPolicy> ApplicationConversionServiceFactoryBean.getStringToUserSubscriptionPolicyConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, com.sjsu.bikelet.domain.UserSubscriptionPolicy>() {
            public com.sjsu.bikelet.domain.UserSubscriptionPolicy convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), UserSubscriptionPolicy.class);
            }
        };
    }
    
    public void ApplicationConversionServiceFactoryBean.installLabelConverters(FormatterRegistry registry) {
        registry.addConverter(getAddressToStringConverter());
        registry.addConverter(getIdToAddressConverter());
        registry.addConverter(getStringToAddressConverter());
        registry.addConverter(getAddressAssociationToStringConverter());
        registry.addConverter(getIdToAddressAssociationConverter());
        registry.addConverter(getStringToAddressAssociationConverter());
        registry.addConverter(getBikeToStringConverter());
        registry.addConverter(getIdToBikeConverter());
        registry.addConverter(getStringToBikeConverter());
        registry.addConverter(getBikeLetRoleToStringConverter());
        registry.addConverter(getIdToBikeLetRoleConverter());
        registry.addConverter(getStringToBikeLetRoleConverter());
        registry.addConverter(getBikeLetUserToStringConverter());
        registry.addConverter(getIdToBikeLetUserConverter());
        registry.addConverter(getStringToBikeLetUserConverter());
        registry.addConverter(getBikeLocationToStringConverter());
        registry.addConverter(getIdToBikeLocationConverter());
        registry.addConverter(getStringToBikeLocationConverter());
        registry.addConverter(getBillToStringConverter());
        registry.addConverter(getIdToBillConverter());
        registry.addConverter(getStringToBillConverter());
        registry.addConverter(getBillTransactionToStringConverter());
        registry.addConverter(getIdToBillTransactionConverter());
        registry.addConverter(getStringToBillTransactionConverter());
        registry.addConverter(getLicensePolicyToStringConverter());
        registry.addConverter(getIdToLicensePolicyConverter());
        registry.addConverter(getStringToLicensePolicyConverter());
        registry.addConverter(getPaymentInfoToStringConverter());
        registry.addConverter(getIdToPaymentInfoConverter());
        registry.addConverter(getStringToPaymentInfoConverter());
        registry.addConverter(getPaymentTransactionToStringConverter());
        registry.addConverter(getIdToPaymentTransactionConverter());
        registry.addConverter(getStringToPaymentTransactionConverter());
        registry.addConverter(getPermissionToStringConverter());
        registry.addConverter(getIdToPermissionConverter());
        registry.addConverter(getStringToPermissionConverter());
        registry.addConverter(getProgramToStringConverter());
        registry.addConverter(getIdToProgramConverter());
        registry.addConverter(getStringToProgramConverter());
        registry.addConverter(getRentTransactionToStringConverter());
        registry.addConverter(getIdToRentTransactionConverter());
        registry.addConverter(getStringToRentTransactionConverter());
        registry.addConverter(getRolePermissionToStringConverter());
        registry.addConverter(getIdToRolePermissionConverter());
        registry.addConverter(getStringToRolePermissionConverter());
        registry.addConverter(getStationToStringConverter());
        registry.addConverter(getIdToStationConverter());
        registry.addConverter(getStringToStationConverter());
        registry.addConverter(getSubscriptionPolicyToStringConverter());
        registry.addConverter(getIdToSubscriptionPolicyConverter());
        registry.addConverter(getStringToSubscriptionPolicyConverter());
        registry.addConverter(getSubscriptionRateToStringConverter());
        registry.addConverter(getIdToSubscriptionRateConverter());
        registry.addConverter(getStringToSubscriptionRateConverter());
        registry.addConverter(getTenantToStringConverter());
        registry.addConverter(getIdToTenantConverter());
        registry.addConverter(getStringToTenantConverter());
        registry.addConverter(getTenantLicensePolicyToStringConverter());
        registry.addConverter(getIdToTenantLicensePolicyConverter());
        registry.addConverter(getStringToTenantLicensePolicyConverter());
        registry.addConverter(getUserRoleToStringConverter());
        registry.addConverter(getIdToUserRoleConverter());
        registry.addConverter(getStringToUserRoleConverter());
        registry.addConverter(getUserSubscriptionPolicyToStringConverter());
        registry.addConverter(getIdToUserSubscriptionPolicyConverter());
        registry.addConverter(getStringToUserSubscriptionPolicyConverter());
    }
    
    public void ApplicationConversionServiceFactoryBean.afterPropertiesSet() {
        super.afterPropertiesSet();
        installLabelConverters(getObject());
    }
    
}
