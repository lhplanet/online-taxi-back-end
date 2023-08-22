package com.sdu.internalcommon.constant;

import lombok.Getter;

/**
 * @author LHP
 * @date 2023-07-10 8:51
 * @description 通用状态枚举
 */
public enum CommonStatusEnum {

        /**
        * 通用状态
        */
        SUCCESS(1, "success"), // 成功
        FAIL(0, "fail"), // 失败
        VERIFICATION_CODE_ERROR(1099, "验证码错误"), // 验证码错误：1000-1099
        TOKEN_ERROR(1199, "token错误"), // token错误：1100-1199
        USER_NOT_EXISTS(1299, "当前用户不存在"), // 用户不存在：1200-1299





        PRICE_RULE_EMPTY(1300,"计价规则不存在"), // 计价规则：1300-1399

        PRICE_RULE_EXISTS(1301,"计价规则已存在，不允许添加"),

        PRICE_RULE_NOT_EDIT(1302,"计价规则没有变化"),

        PRICE_RULE_CHANGED(1303,"计价规则有变化"),



        MAP_DISTRICT_ERROR(1499, "请求地图错误"), // 请求地图错误：1400-1499




        DRIVER_CAR_BIND_NOT_EXISTS(1500,"司机和车辆绑定关系不存在"), // 司机和车辆：1500-1599

        DRIVER_NOT_EXITST(1501,"司机不存在"),

        DRIVER_CAR_BIND_EXISTS(1502,"司机和车辆绑定关系已存在，请勿重复绑定"),

        DRIVER_BIND_EXISTS(1503,"司机已经被绑定了，请勿重复绑定"),

        CAR_BIND_EXISTS(1504,"车辆已经被绑定了，请勿重复绑定"),

        CITY_DRIVER_EMPTY(1505,"当前城市没有可用的司机"),

        AVAILABLE_DRIVER_EMPTY(1506,"可用的司机为空"),

        ORDER_GOING_ON(1600,"有正在进行的订单"), // 订单：1600-1699

        DEVICE_IS_BLACK(1601,"该设备超过下单次数"), // 下单异常

        CITY_SERVICE_NOT_SERVICE(1602,"当前城市不提供叫车服务"),

        ORDER_CANCEL_ERROR(1603, "订单取消失败")
        ;

        @Getter
        private int code;
        @Getter
        private String value;

        CommonStatusEnum(int code, String value) {
            this.code = code;
            this.value = value;
        }

}
