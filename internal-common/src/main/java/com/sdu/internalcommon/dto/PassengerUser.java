package com.sdu.internalcommon.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author LHP
 * @description 乘客用户实体类
 */
@Data
public class PassengerUser {

    private Long id;

    private LocalDateTime gmtCreate;

    private LocalDateTime gmtModified;

    private String passengerPhone;

    private String passengerName;

    private byte passengerGender;

    private byte state;

    private String profilePhoto;

}
