package com.sdu.apiboss.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sdu.apiboss.service.sys_role.SysRoleService;
import com.sdu.apiboss.service.sys_user.SysUserService;
import com.sdu.apiboss.service.sys_user_role.SysUserRoleService;
import com.sdu.internalcommon.dto.SysRole;
import com.sdu.internalcommon.dto.SysUser;
import com.sdu.internalcommon.dto.SysUserPagePara;
import com.sdu.internalcommon.dto.SysUserRole;
import com.sdu.internalcommon.result.ResultUtils;
import com.sdu.internalcommon.result.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author Connor
 * @date 2022/11/3 16:54
 */
@RestController
@Slf4j
@RequestMapping("/api/user") // 请求路径
public class SysUserController {
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysUserRoleService sysUserRoleService;

    // 新增用户
    // @RequestBody 注解:
    // 主要用来接收前端传递给后端的json字符串中的数据的(请求体中的数据的)；
    // 最常用的使用请求体传参的无疑是POST请求了，所以使用@RequestBody接收数据时，一般都用POST方式进行提交。
    // 在后端的同一个接收方法里，@RequestBody与@RequestParam()可以同时使用
    // @RequestBody最多只能有一个，而@RequestParam()可以有多个。
    @PostMapping
    public ResultVo addUser(@RequestBody SysUser user){
        // 判断账户是否被占用
        // QueryWrapper是MyBatisPlus提供的sql的构造器
        QueryWrapper<SysUser> query = new QueryWrapper<>();
        query.lambda().eq(SysUser::getUsername,user.getUsername());
        SysUser one = sysUserService.getOne(query);
        if(one != null){
            return ResultUtils.error("账户被占用!");
        }
        // 加密
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        // 设置为非管理员
        user.setIsAdmin("0");
        // 设置时间
        user.setCreateTime(new Date());

        // 入库
        sysUserService.add(user);
        return ResultUtils.success("新增用户成功!");
    }

    // 编辑用户
    @PutMapping
    public ResultVo editUser(@RequestBody SysUser user){
        // 判断账户是否被占用
        // QueryWrapper是MyBatisPlus提供的sql的构造器
        QueryWrapper<SysUser> query = new QueryWrapper<>();
        query.lambda().eq(SysUser::getUsername,user.getUsername());
        SysUser one = sysUserService.getOne(query);
        if(one != null && !Objects.equals(one.getUserId(), user.getUserId())){
            return ResultUtils.error("账户被占用!");
        }
        // 设置时间
        user.setUpdateTime(new Date());

        // 更新
        sysUserService.edit(user);
        return ResultUtils.success("编辑用户成功!");
    }

    // 删除用户
    @DeleteMapping("/{userId}")
    public ResultVo delete(@PathVariable("userId") Long userId){
        boolean remove = sysUserService.removeById(userId);
        if(remove) {
            return ResultUtils.success("删除成功!");
        }
        return ResultUtils.error("删除失败!");
    }

    // 查询列表
    @GetMapping("/list")
    public ResultVo getList(SysUserPagePara para) {
        IPage<SysUser> list = sysUserService.list(para);
        list.getRecords().stream().forEach(item -> {
            item.setPassword(""); // 不把密码返回前端
        });
        return ResultUtils.success("查询成功!",list);
    }

    // 角色列表
    @GetMapping("/roleList")
    public ResultVo getRoleList(){
        List<SysRole> list = sysRoleService.list();
        return ResultUtils.success("查询成功!",list);
    }

    // 获取管理员角色
    @GetMapping("/getSysUserRole")
    public ResultVo getSysUserRole(){
        QueryWrapper<SysRole> query = new QueryWrapper<>();
        query.lambda().eq(SysRole::getRoleType,"1");
        SysRole one = sysRoleService.getOne(query);
        return ResultUtils.success("查询成功!",one);
    }

    // 根据用户的id查询角色的id
    @GetMapping("/role")
    public ResultVo getRoleById(Long userId){
        QueryWrapper<SysUserRole> query = new QueryWrapper<>();
        query.lambda().eq(SysUserRole::getUserId,userId);
        SysUserRole one = sysUserRoleService.getOne(query);
        return ResultUtils.success("查询成功!",one);
    }

    // 根据用户的id查询用户姓名
    @GetMapping("/getUserById")
    public ResultVo getUserById(Long userId){
        /*QueryWrapper<SysUser> query = new QueryWrapper<>();
        query.lambda().eq(SysUser::getUserId,userId);
        SysUser one = sysUserService.getOne(query);*/
        SysUser one = sysUserService.getById(userId);
        return ResultUtils.success("查询成功!",one);
    }
}
