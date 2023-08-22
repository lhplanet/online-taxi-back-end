package com.sdu.internalcommon.dto;

import lombok.Data;

/**
 * @author LHP
 * @date 2023-07-12 11:38
 * @description
 */

@Data
public class DicDistrict {

    private String addressCode;

    private String addressName;

    private String parentAddressCode;

    private Integer level;

}
