package com.sdu.internalcommon.dto;

import lombok.Data;

/**
 * @author LHP
 * @description
 */
@Data
//@TableName("evaluate")
public class Evaluate {

    private Long id;

    private Long orderId;

    private Integer rating;

    private String evaluation;

//    @TableField(exist = false)
//    private String passengerPhone;

//    @TableField(exist = false)
//    private String driverPhone;

}
