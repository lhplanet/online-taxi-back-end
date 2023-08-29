package com.sdu.apiboss.service.sys_role_menu.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sdu.apiboss.mapper.SysRoleMenuMapper;
import com.sdu.apiboss.service.sys_role_menu.SysRoleMenuService;
import com.sdu.internalcommon.dto.SysRoleMenu;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Connor
 * @date 2022/11/5 0:49
 */
@Service
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuMapper, SysRoleMenu> implements SysRoleMenuService {
    @Override
    @Transactional
    public void saveRoleMenu(Long roleId, List<Long> menuIds) {
        // 先删除原来的，再插入
        QueryWrapper<SysRoleMenu> query = new QueryWrapper<>();
        query.lambda().eq(SysRoleMenu::getRoleId,roleId);
        this.baseMapper.delete(query);
        // 插入新的
        this.baseMapper.saveRoleMenu(roleId,menuIds);
    }
}
