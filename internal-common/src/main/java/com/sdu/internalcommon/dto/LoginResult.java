package com.sdu.internalcommon.dto;

import lombok.Data;

/**
 * @author Connor
 * @date 2022/11/4 22:10
 */
@Data
public class LoginResult {
    private Long userId;
    private String username;
    private String token;
    private String userType;
}
