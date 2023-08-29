package com.sdu.internalcommon.dto;

import lombok.Data;

/**
 * @author Connor
 * @date 2022/11/9 20:44
 */
@Data
public class UpdatePasswordPara {
    private Long userId;
    private String userType;
    private String oldPassword;
    private String newPassword;
}
