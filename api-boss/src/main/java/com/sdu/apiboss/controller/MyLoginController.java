package com.sdu.apiboss.controller;

import com.sdu.apiboss.service.sys_user.SysUserService;
import com.sdu.internalcommon.dto.SysUser;
import com.sdu.internalcommon.result.Result;
import com.sdu.internalcommon.result.ResultFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Connor
 * @date 2022/11/12 2:16
 * @description 后端登录控制器，处理前端请求
 */
@RestController
@CrossOrigin(value = "http://localhost:8087", maxAge = 1800, allowedHeaders ="Content-Type")
public class MyLoginController {

    @Autowired
    private SysUserService sysUserService;

    @PostMapping("/api/resetPassword")
    public Result resetPassword(@RequestBody SysUser user) {
        if (user.getUsername()!=null&& user.getEmail()!=null){
            //第一步，传来的是用户名和邮件，其他为空，则生成验证码并发送邮件
            return sysUserService.checkUserAndEmail(user);
        }
        if (user.getCode()!=null && user.getUsername()!=null) {
            //第二步，传来code和username，需要验证数据库中的code是否正确
            return sysUserService.checkCode(user.getCode(), user.getUsername());
        }
        if (user.getUsername()!=null && user.getPassword()!=null) {
            //最后，用户名和密码同时传过来，开始重置密码。
            return sysUserService.resetPassword(user.getUsername(), user.getPassword());
        }
        return ResultFactory.buildFailResult("未知错误");
    }

}
