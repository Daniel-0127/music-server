package com.music.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 用户喜欢表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLove {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer user_id;
    private Integer song_id;
    private Date create_time;
}
