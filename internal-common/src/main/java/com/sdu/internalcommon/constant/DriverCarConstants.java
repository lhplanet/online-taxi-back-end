package com.sdu.internalcommon.constant;

/**
 * @author LHP
 * @description 司机车辆关系常量类
 */
public class DriverCarConstants {

    // 司机车辆关系状态：绑定
    public static int DRIVER_CAR_BIND = 1;

    // 司机车辆关系状态：解绑
    public static int DRIVER_CAR_UNBIND = 2;

    // 司机状态：0-有效
    public static int DRIVER_STATE_VALID = 0;

    // 司机状态：1-无效
    public static int DRIVER_STATE_INVALID = 1;

    // 司机状态：1-存在
    public static int DRIVER_EXISTS = 1;

    // 司机状态：0-不存在
    public static int DRIVER_NOT_EXISTS = 0;

    // 司机工作状态：收车
    public static int DRIVER_WORK_STATUS_STOP = 0;

    // 司机工作状态：出车
    public static int DRIVER_WORK_STATUS_START = 1;

    // 司机工作状态：暂停
    public static int DRIVER_WORK_STATUS_SUSPEND = 2;
}
