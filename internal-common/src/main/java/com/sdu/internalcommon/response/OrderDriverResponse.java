package com.sdu.internalcommon.response;

import lombok.Data;

/**
 * @author LHP
 * @date 2023-07-14 9:33
 * @description
 */

@Data
public class OrderDriverResponse {

    private Long driverId;

    private String driverPhone;

    private Long carId;

    /**
     * 机动车驾驶证号
     */
    private String licenseId;

    /**
     * 车辆号牌
     */
    private String vehicleNo;

    /**
     * 车辆类型
     */
    private String vehicleType;
}
