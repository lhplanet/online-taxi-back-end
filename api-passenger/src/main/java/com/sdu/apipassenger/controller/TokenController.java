package com.sdu.apipassenger.controller;

import com.sdu.apipassenger.service.TokenService;
import com.sdu.internalcommon.dto.ResponseResult;
import com.sdu.internalcommon.response.TokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LHP
 * @date 2023-07-11 18:18
 * @description
 */

@RestController
public class TokenController {

    @Autowired
    private TokenService tokenService;

    @PostMapping("/token-refresh")
    public ResponseResult refreshToken(@RequestBody TokenResponse tokenResponse) {

        String refreshTokenSrc = tokenResponse.getRefreshToken();
        System.out.println("原来的refreshToken:" + refreshTokenSrc);

        return tokenService.refreshToken(refreshTokenSrc);
    }

}
