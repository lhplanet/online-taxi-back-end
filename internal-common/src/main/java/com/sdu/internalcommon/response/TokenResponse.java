package com.sdu.internalcommon.response;

import lombok.Data;

/**
 * @author LHP
 * @date 2023-07-10 20:27
 * @description
 */

@Data
public class TokenResponse {

    private String accessToken;

    private String refreshToken;

}
