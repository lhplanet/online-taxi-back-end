package com.sdu.apiboss.service.sys_role_menu;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sdu.internalcommon.dto.SysRoleMenu;

import java.util.List;

/**
 * @author Connor
 * @date 2022/11/5 0:48
 */
public interface SysRoleMenuService extends IService<SysRoleMenu> {
    void saveRoleMenu(Long roleId, List<Long> menuIds);
}
