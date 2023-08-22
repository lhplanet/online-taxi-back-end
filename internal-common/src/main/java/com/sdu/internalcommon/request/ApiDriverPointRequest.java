package com.sdu.internalcommon.request;

import lombok.Data;

/**
 * @author LHP
 * @date 2023-07-14 0:16
 * @description
 */

@Data
public class ApiDriverPointRequest {

    public Long carId;

    private PointDTO[] points;

}
