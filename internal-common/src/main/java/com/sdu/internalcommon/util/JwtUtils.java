package com.sdu.internalcommon.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.sdu.internalcommon.dto.TokenResult;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author LHP
 * @description jwt工具类
 */
public class JwtUtils {

    /**
     * 盐
     */
    private static final String SIGN = "!sop@WDT^&*()_+";

    /**
     * 手机号
     */
    private static final String JWT_KEY_PHONE = "phone";

    /**
     * 1-乘客，2-司机
     */
    private static final String JWT_KEY_IDENTITY = "identity";

    /**
     * token类型：access_token，refresh_token
     */
    private static final String JWT_TOKEN_TYPE = "tokenType";

    /**
     * token生成时间
     */
    private static final String JWT_TOKEN_TIME = "tokenTime";

    /**
     * 生成token
     * @param phone 手机号
     * @param identity 1-乘客，2-司机
     * @param tokenType token类型：access_token，refresh_token
     * @return token
     */
    public static String generateToken(String phone, String identity, String tokenType) {
        Map<String, String> map = new HashMap<>();
        map.put(JWT_KEY_PHONE, phone);
        map.put(JWT_KEY_IDENTITY, identity);
        map.put(JWT_TOKEN_TYPE, tokenType);
        // 防止每次生成的token一样
        map.put(JWT_TOKEN_TIME, Calendar.getInstance().getTime().toString());

        JWTCreator.Builder builder = JWT.create();

        // 整合map
        map.forEach(builder::withClaim);

        // 加盐，生成token，并返回
        return builder.sign(Algorithm.HMAC256(SIGN));
    }

    // 解析token
    public static TokenResult parseToken(String token) {
        DecodedJWT verify = JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);
        String phone = verify.getClaim(JWT_KEY_PHONE).asString();
        String identity = verify.getClaim(JWT_KEY_IDENTITY).asString();

        TokenResult tokenResult = new TokenResult();
        tokenResult.setPhone(phone);
        tokenResult.setIdentity(identity);
        return tokenResult;

    }

    /**
     * 校验token，主要判断token是否异常
     * @param token
     * @return
     */
    public static TokenResult checkToken(String token) {
        TokenResult tokenResult = null;
        try {
            tokenResult = JwtUtils.parseToken(token);
        } catch (Exception e) { // token无效

        }
        return tokenResult;
        /*try {
            tokenResult = JwtUtils.parseToken(token);
        } catch (SignatureVerificationException e) { // token签名错误
            resultString = "token sign error";
            result = false;
        } catch (TokenExpiredException e) { // token过期
            resultString = "token time out";
            result = false;
        } catch (AlgorithmMismatchException e) { // token算法不一致
            resultString = "token algorithm mismatch";
            result = false;
        } catch (Exception e) { // token无效
            resultString = "token is invalid";
            result = false;
        }*/
    }

    public static void main(String[] args) {
        String s = generateToken("15553581029", "1", "accessToken");
        System.out.println("生成的token：" + s);
        System.out.println("解析-------------");
        TokenResult tokenResult = parseToken(s);
        System.out.println("手机号：" + tokenResult.getPhone());
        System.out.println("身份：" + tokenResult.getIdentity());
    }

}
