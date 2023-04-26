package com.music.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 歌手
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Singer {
    /**
     * 歌手ID(自增)
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 姓名
     */
    private String name;
    /**
     * 性别
     */
    private Integer sex;

    /**
     * 出生日期
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date birth;
    /**
     * 地址
     */
    private String address;
    /**
     * 介绍
     */
    private String introduce;
    /**
     * 标记是否删除
     */
    @TableLogic(value = "0", delval = "1")
    private boolean deleted;
    /**
     * 创建时间
     */
    private Date create_time;
}
