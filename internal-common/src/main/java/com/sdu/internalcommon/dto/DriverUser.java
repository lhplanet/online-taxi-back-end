package com.sdu.internalcommon.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author LHP
 * @date 2023-07-12 18:13
 * @description
 */

@Data
public class DriverUser {

    private Long id;
    private String address;
    private String driverName;
    private String driverPhone;
    private Integer driverGender;
//    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate driverBirthday;
    private String driverNation;
    private String driverContactAddress;
    private String licenseId;
//    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate getDriverLicenseDate;
//    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate driverLicenseOn;
//    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate driverLicenseOff;
    private Integer taxiDriver;
    private String certificateNo;
    private String networkCarIssueOrganization;
//    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate networkCarIssueDate;
//    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate getNetworkCarProofDate;
//    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate networkCarProofOn;
//    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate networkCarProofOff;
//    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate registerDate;
    private Integer commercialType;
    private String contractCompany;
//    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate contractOn;
//    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate contractOff;
    private Integer state;
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime gmtCreate;
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime gmtModified;

}
