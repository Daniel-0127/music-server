package com.music.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class Song {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private String singer;

    private String album;
    private Date create_time;
    private Date update_time;

}
