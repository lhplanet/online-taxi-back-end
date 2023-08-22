package com.sdu.internalcommon.response;

import lombok.Data;

/**
 * @author LHP
 * @date 2023-07-12 0:13
 * @description
 */

@Data
public class ForecastPriceResponse {

    private double price;

    private String cityCode;

    private String vehicleType;

    private String fareType;

    private Integer fareVersion;

}
