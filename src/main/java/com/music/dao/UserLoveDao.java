package com.music.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.music.entity.Song;
import com.music.entity.UserLove;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface UserLoveDao extends BaseMapper<UserLove> {

    @Select("select song.* from user_love,song where user_id = #{id} and user_love.song_id=song.id order by user_love.create_time desc")
    List<Song> LoveList(@Param("id") int id);
}
