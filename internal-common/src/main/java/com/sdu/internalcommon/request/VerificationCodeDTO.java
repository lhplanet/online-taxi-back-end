package com.sdu.internalcommon.request;

import lombok.Data;

/**
 * @author LHP
 * @description 验证码请求类
 */
@Data
public class VerificationCodeDTO {

    private String passengerPhone;

    private String driverPhone;

    private String verificationCode;


}
