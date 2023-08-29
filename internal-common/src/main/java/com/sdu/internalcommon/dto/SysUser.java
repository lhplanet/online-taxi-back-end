package com.sdu.internalcommon.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author Connor
 * @date 2022/11/3 16:34
 */
@Data
@TableName("sys_user") // 对应的数据库表
public class SysUser {
    // 主键,并且自动递增
    @TableId(type = IdType.AUTO)
    private Long userId;
    @TableField(exist = false)
    private Long roleId;
    private String username;
    private String password;
    private String phone;
    private String email;
    private String sex;
    private String isAdmin;
    /**
     * 帐户是否过期(1未过期,0已过期)
     */
    private boolean isAccountNonExpired = true;
    /**
     * 帐户是否被锁定(1未锁定,0已锁定)
     */
    private boolean isAccountNonLocked = true;
    /**
     * 密码是否过期(1未过期,0已过期)
     */
    private boolean isCredentialsNonExpired = true;
    /**
     * 帐户是否可用(1可用,0删除用户)
     */
    private boolean isEnabled = true;
    private String nickName;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
    @TableField(exist = false)
    private String code;

}
