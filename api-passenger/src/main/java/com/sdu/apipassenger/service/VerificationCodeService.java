package com.sdu.apipassenger.service;

import com.sdu.apipassenger.remote.ServicePassengerUserClient;
import com.sdu.apipassenger.remote.ServiceVerificationcodeClient;
import com.sdu.internalcommon.constant.CommonStatusEnum;
import com.sdu.internalcommon.constant.IdentityConstants;
import com.sdu.internalcommon.constant.TokenConstants;
import com.sdu.internalcommon.dto.ResponseResult;
import com.sdu.internalcommon.request.VerificationCodeDTO;
import com.sdu.internalcommon.response.NumberCodeResponse;
import com.sdu.internalcommon.response.TokenResponse;
import com.sdu.internalcommon.util.JwtUtils;
import com.sdu.internalcommon.util.RedisPrefixUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author LHP
 * @description 验证码服务类
 */
@Service
public class VerificationCodeService {

    /**
     * 远程调用service-verificationcode服务
     */
    @Autowired
    private ServiceVerificationcodeClient serviceVerificationcodeClient;

    /**
     * 远程调用service-passenger-user服务
     */
    @Autowired
    private ServicePassengerUserClient servicePassengerUserClient;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 生成验证码
     * @param passengerPhone 乘客手机号
     * @return
     */
    public ResponseResult generatorCode(String passengerPhone) {

        // 调用（远程）验证码服务，获取验证码
        ResponseResult<NumberCodeResponse> numberCodeResponse = serviceVerificationcodeClient.getNumberCode(6);
        int numberCode = numberCodeResponse.getData().getNumberCode();

        // 存入redis：
        // key的格式：verification-code-身份代码-手机号
        String key = RedisPrefixUtils.generateKeyByPhone(passengerPhone, IdentityConstants.PASSENGER_IDENTITY);
        // key，value，过期时间
        stringRedisTemplate.opsForValue().set(key, numberCode + "", 2, TimeUnit.MINUTES);

        // 通过短信服务商，将对应的验证码发送到手机上，阿里短信服务，腾讯短信通，华信，容联
        // TODO: 未完成

        // 返回值
        return ResponseResult.success("");
    }

    /**
     * 校验验证码
     * @param passengerPhone 乘客手机号
     * @param verificationCode 验证码
     * @return
     */
    public ResponseResult checkCode(String passengerPhone, String verificationCode) {

        // 去redis中取出验证码
        // 根据手机号和身份，生成redis中的key
        String key = RedisPrefixUtils.generateKeyByPhone(passengerPhone, IdentityConstants.PASSENGER_IDENTITY);
        // 根据key，去redis中取value
        String codeRedis = stringRedisTemplate.opsForValue().get(key);

        // 校验验证码
        // 如果redis中的验证码为空，或者与传入的验证码不一致，返回错误
        if (StringUtils.isBlank(codeRedis)) {
            return ResponseResult.fail(CommonStatusEnum.VERIFICATION_CODE_ERROR.getCode(), CommonStatusEnum.VERIFICATION_CODE_ERROR.getValue());
        }
        if (!verificationCode.trim().equals(codeRedis.trim())) {
            return ResponseResult.fail(CommonStatusEnum.VERIFICATION_CODE_ERROR.getCode(), CommonStatusEnum.VERIFICATION_CODE_ERROR.getValue());
        }

        // 判断原来是否有用户，并进行相应的处理
        VerificationCodeDTO verificationCodeDTO = new VerificationCodeDTO();
        verificationCodeDTO.setPassengerPhone(passengerPhone);
        servicePassengerUserClient.loginOrRegister(verificationCodeDTO);

        // 颁发令牌
        String accessToken = JwtUtils.generateToken(passengerPhone, IdentityConstants.PASSENGER_IDENTITY, TokenConstants.ACCESS_TOKEN_TYPE);
        String refreshToken = JwtUtils.generateToken(passengerPhone, IdentityConstants.PASSENGER_IDENTITY, TokenConstants.REFRESH_TOKEN_TYPE);

        // 将token存入redis
        String accessTokenKey = RedisPrefixUtils.generateTokenKey(passengerPhone, IdentityConstants.PASSENGER_IDENTITY, TokenConstants.ACCESS_TOKEN_TYPE);
        stringRedisTemplate.opsForValue().set(accessTokenKey, accessToken, 30, TimeUnit.DAYS);

        String refreshTokenKey = RedisPrefixUtils.generateTokenKey(passengerPhone, IdentityConstants.PASSENGER_IDENTITY, TokenConstants.REFRESH_TOKEN_TYPE);
        stringRedisTemplate.opsForValue().set(refreshTokenKey, refreshToken, 31, TimeUnit.DAYS);

        // 响应
        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setAccessToken(accessToken);
        tokenResponse.setRefreshToken(refreshToken);

        return ResponseResult.success(tokenResponse);
    }
}
