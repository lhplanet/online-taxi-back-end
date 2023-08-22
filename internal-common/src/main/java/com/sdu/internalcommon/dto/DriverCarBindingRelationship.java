package com.sdu.internalcommon.dto;

import lombok.Data;
import java.time.LocalDateTime;

/**
 * @author 李浩鹏
 * @since 2023-07-13
 */
@Data
public class DriverCarBindingRelationship {

    private Long id;

    private Long driverId;

    private Long carId;

    private Integer bindingState; // 绑定状态：1-绑定，2-解绑

    private LocalDateTime bindingTime;

    private LocalDateTime unBindingTime;

}
