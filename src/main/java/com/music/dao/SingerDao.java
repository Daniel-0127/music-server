package com.music.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.music.entity.Singer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SingerDao extends BaseMapper<Singer> {
    /**
     * 查找被删除的并满足条件的数据
     *
     * @param singer 用户和密码
     * @return User
     */
    @Select("SELECT * FROM singer WHERE deleted = 1 and name = #{singer.name} limit 1")
    Singer findDeletedSinger(@Param("singer") Singer singer);

    /**
     * 通过ID把软删除的数据恢复
     *
     * @param id id
     * @return 1/0
     */
    @Update("UPDATE singer SET deleted = 0 WHERE id = #{id}")
    int recoverSingerById(@Param("id") int id);
    /**
     * 根据昵称查询歌手
     * @param data 昵称
     * @return list
     */
    @Select("select * from singer where name like #{data}")
    List<Singer> selectByName(@Param("data") String data);

    /**
     * 查询分页页数
     * @param size 每页大小
     * @return int
     */
    @Select("SELECT CEIL(COUNT(*)/#{size}) FROM SINGER")
    int PageSize(@Param("size") int size);

    @Select("SELECT * FROM SINGER LIMIT #{start},#{len}")
    List<Singer> MySelectByPage(@Param("start") int start, @Param("len") int len);
}
