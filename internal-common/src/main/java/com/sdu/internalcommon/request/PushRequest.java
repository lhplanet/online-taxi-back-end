package com.sdu.internalcommon.request;

import lombok.Data;

/**
 * @author LHP
 * @date 2023-07-14 9:32
 * @description
 */

@Data
public class PushRequest {

    private Long userId;

    private String identity;

    private String content;

}
