package com.sdu.internalcommon.response;

import lombok.Data;

/**
 * @author LHP
 * @description 司机是否存在响应类
 */
@Data
public class DriverUserExistsResponse {

    private String driverPhone;

    private int ifExists;

}
