package com.music.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.Date;

/**
 * 用户
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    /**
     * 用户ID(自增)
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 账号
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 性别
     */
    private Integer sex;
    /**
     * 手机号
     */
    private String phone_number;
    /**
     * 电子邮箱
     */
    private String email;
    /**
     * 出生日期
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date birth;
    /**
     * 自我介绍
     */
    private String introduce;
    /**
     * 住址
     */
    private String address;
    /**
     * 头像
     */
    private String profile_photo;
    /**
     * 标记是否删除
     */
    @TableLogic(value = "0", delval = "1")
    private boolean deleted;
    /**
     * 创建时间
     */
    private Date create_time;
    /**
     * 修改时间
     */
    private Date update_time;


}
