package com.sdu.internalcommon.dto;

import lombok.Data;

/**
 * @author LHP
 * @description
 */
@Data
public class EvaluateVo {

    private Long id;

    private Long orderId;

    private Integer rating;

    private String evaluation;

    private String passengerPhone;

    private String driverPhone;
}
