package com.sdu.internalcommon.dto;

import lombok.Data;

import java.util.List;

/**
 * @author Connor
 * @date 2022/11/5 1:19
 */
@Data
public class SaveRolePara {
    private Long roleId;
    private List<Long> list;
}
