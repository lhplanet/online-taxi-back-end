package com.sdu.internalcommon.dto;

import lombok.Data;

/**
 * @author Connor
 * @date 2022/11/3 18:44
 * 参数接收类
 */
@Data
public class SysUserPagePara {
    private Long currentPage;
    private Long pageSize;
    private String phone;
    private String nickName;
}
