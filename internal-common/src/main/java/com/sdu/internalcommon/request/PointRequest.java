package com.sdu.internalcommon.request;

import lombok.Data;

/**
 * @author LHP
 * @date 2023-07-13 23:47
 * @description
 */

@Data
public class PointRequest {

    private String tid;

    private String trid;

    private PointDTO[] points;

}
