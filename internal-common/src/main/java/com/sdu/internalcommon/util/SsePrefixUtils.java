package com.sdu.internalcommon.util;

/**
 * @author LHP
 * @date 2023-07-14 9:33
 * @description
 */
public class SsePrefixUtils {

    public static  final String separator = "$";

    public  static String generatorSseKey(Long userId , String identity){
        return userId+ separator +identity;
    }

}
