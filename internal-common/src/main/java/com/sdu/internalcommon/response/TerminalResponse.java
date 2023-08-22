package com.sdu.internalcommon.response;

import lombok.Data;

/**
 * @author LHP
 * @date 2023-07-13 22:51
 * @description
 */

@Data
public class TerminalResponse {

    private String tid;

    private Long carId;

    private String longitude ;

    private String latitude ;

}
