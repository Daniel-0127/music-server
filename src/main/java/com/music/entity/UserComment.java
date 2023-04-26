package com.music.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 评论
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserComment {
    /**
     * 评论
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 用户ID
     */
    private Integer user_id;
    /**
     * 歌曲ID
     */
    private Integer song_id;
    /**
     * 评论
     */
    private String content;
    /**
     * 点赞数
     */
    private Integer like_num;
    /**
     * 创建时间
     */
    private Date creat_time;

}
