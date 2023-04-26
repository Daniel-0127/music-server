package com.music.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.music.entity.Song;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface SongDao extends BaseMapper<Song> {
    /**
     * 搜索相关歌曲
     * @param data 关键词
     * @return list
     */
    @Select("SELECT * FROM song WHERE name LIKE #{data}")
    List<Song> SelectBySong(@Param("data") String data);

    /**
     * 搜索相关歌手
     * @param data 关键词
     * @return list
     */
    @Select("SELECT * FROM song WHERE singer LIKE #{data}")
    List<Song> SelectBySinger(@Param("data") String data);
}
