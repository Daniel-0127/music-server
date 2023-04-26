package com.music.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 管理员
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Admin {
    /**
     * 管理员ID(自增)
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 昵称
     */
    private String username;
    /**
     * 密码
     */
    private String password;
}
