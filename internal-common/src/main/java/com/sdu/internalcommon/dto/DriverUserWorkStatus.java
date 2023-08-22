package com.sdu.internalcommon.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author LHP
 * @date 2023-07-13 10:42
 * @description
 */

@Data
public class DriverUserWorkStatus {

    private Long id;

    private Long driverId;

    private Integer workStatus;

    // 创建时间
    private LocalDateTime gmtCreate;

    // 修改时间
    private LocalDateTime gmtModified;

}
