package com.sdu.apiboss.service.sys_role;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sdu.internalcommon.dto.AssignPara;
import com.sdu.internalcommon.dto.AssignVo;
import com.sdu.internalcommon.dto.RolePara;
import com.sdu.internalcommon.dto.SysRole;


/**
 * @author Connor
 * @date 2022/11/3 23:40
 */
public interface SysRoleService extends IService<SysRole>  {
    IPage<SysRole> list(RolePara para);
    // 角色权限的回显
    AssignVo getAssignShow(AssignPara para);
}
