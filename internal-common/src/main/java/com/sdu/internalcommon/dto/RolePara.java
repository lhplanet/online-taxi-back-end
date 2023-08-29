package com.sdu.internalcommon.dto;


import lombok.Data;

/**
 * @author Connor
 * @date 2022/11/4 8:03
 */
@Data
public class RolePara {
    private Long currentPage;
    private Long pageSize;
    private String roleName;
}
