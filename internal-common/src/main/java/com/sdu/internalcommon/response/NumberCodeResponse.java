package com.sdu.internalcommon.response;

import lombok.Data;

/**
 * @author LHP
 * @description 验证码响应类：获取到验证码后返回给调用者的响应类
 */
@Data
public class NumberCodeResponse {

    private int numberCode;

}
