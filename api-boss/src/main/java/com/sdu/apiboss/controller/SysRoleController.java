package com.sdu.apiboss.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sdu.apiboss.service.sys_role.SysRoleService;
import com.sdu.apiboss.service.sys_role_menu.SysRoleMenuService;
import com.sdu.internalcommon.dto.*;
import com.sdu.internalcommon.result.ResultUtils;
import com.sdu.internalcommon.result.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @author Connor
 * @date 2022/11/3 23:44
 */
@RestController
@Slf4j
@RequestMapping("/api/role")
public class SysRoleController {
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysRoleMenuService sysRoleMenuService;

    // 新增角色
    @PostMapping
    public ResultVo addRole(@RequestBody SysRole role) {
        // 判断学生角色是否存在 // TODO: 应该删掉吧
        QueryWrapper<SysRole> query = new QueryWrapper<>();
        query.lambda().eq(SysRole::getRoleType,"2");
        SysRole one = sysRoleService.getOne(query);
        if(one != null && role.getRoleType().equals("2")){
            return ResultUtils.error("学生角色已经存在!");
        }
        // 设置时间
        role.setCreateTime(new Date());
        boolean save = sysRoleService.save(role);
        if(save){
            return ResultUtils.success("新增成功!");
        }
        return ResultUtils.error("新增失败!");
    }

    // 编辑角色
    @PutMapping
    public ResultVo editRole(@RequestBody SysRole role) {
        // 判断学生角色是否存在 // TODO: 应该删掉吧
        QueryWrapper<SysRole> query = new QueryWrapper<>();
        query.lambda().eq(SysRole::getRoleType,"2");
        SysRole one = sysRoleService.getOne(query);
        if(one != null && role.getRoleType().equals("2") && !role.getRoleId().equals(one.getRoleId())){
            return ResultUtils.error("学生角色已经存在!");
        }
        // 更新时间
        role.setUpdateTime(new Date());
        boolean save = sysRoleService.updateById(role);
        if(save){
            return ResultUtils.success("编辑成功!");
        }
        return ResultUtils.error("编辑失败!");
    }

    // 删除角色
    @DeleteMapping("/{roleId}")
    public ResultVo deleteRole(@PathVariable("roleId") Long roleId){
        boolean remove = sysRoleService.removeById(roleId);
        if(remove){
            return ResultUtils.success("删除成功!");
        }
        return ResultUtils.error("删除失败!");
    }

    // 角色列表
    @GetMapping("/list")
    public ResultVo getList(RolePara para){
        IPage<SysRole> list = sysRoleService.list(para);
        return ResultUtils.success("查询成功!", list);
    }

    // 分配权限数据回显(查询角色权限树的回显)
    @GetMapping("/getAssignShow")
    public ResultVo getAssignShow(AssignPara para) {
        AssignVo show = sysRoleService.getAssignShow(para);
        return ResultUtils.success("查询成功!", show);
    }

    // 分配权限保存
    @PostMapping("/saveRoleMenu")
    public ResultVo saveRoleMenu(@RequestBody SaveRolePara para){
        sysRoleMenuService.saveRoleMenu(para.getRoleId(),para.getList());
        return ResultUtils.success("分配权限成功!");
    }
}
