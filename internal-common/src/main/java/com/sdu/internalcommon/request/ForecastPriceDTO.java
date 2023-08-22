package com.sdu.internalcommon.request;

import lombok.Data;

/**
 * @author LHP
 * @date 2023-07-12 0:01
 * @description
 */

@Data
public class ForecastPriceDTO {

    private String depLongitude;

    private String depLatitude;

    private String destLongitude;

    private String destLatitude;

    private String cityCode;

    private String vehicleType;

}
