package com.sdu.internalcommon.dto;

import lombok.Data;

/**
 * @author Connor
 * @date 2022/11/4 23:27
 * 从前端接收到的参数
 */
@Data
public class AssignPara {
    private Long userId;
    private Long roleId;
}
