package com.sdu.internalcommon.util;

/**
 * @author LHP
 * @description 生成redis的key的工具类
 */
public class RedisPrefixUtils {

    /**
     * 验证码的前缀
     */
    public static String verificationCodePrefix = "verification-code-";

    /**
     * token的前缀
     */
    public static String tokenPrefix = "token-";

    /**
     * 黑名单设备号前缀
     */
    public static String blackDeviceCodePrefix = "black-device-";

    /**
     * 根据手机号和身份生成redis中的key
     * @param phone 手机号
     * @param identity 身份
     * @return redis中的key
     */
    public static String generateKeyByPhone(String phone, String identity) {
        return verificationCodePrefix + identity + "-" + phone;
    }

    /**
     * 根据手机号、身份和token类型生成token在radis中的key
     * @param phone 手机号
     * @param identity 身份
     * @param tokenType token类型：access_token，refresh_token
     * @return redis中的key
     */
    public static String generateTokenKey(String phone, String identity, String tokenType) {
        return tokenPrefix + phone + "-" + identity + "-" + tokenType;
    }

}
