package com.music.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.music.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface UserDao extends BaseMapper<User> {
    /**
     * 查找被删除的并满足条件的数据
     *
     * @param user 用户和密码
     * @return User
     */
    @Select("SELECT * FROM user WHERE deleted = 1 and username = #{user.username} and password = #{user.password} limit 1")
    User findDeletedUser(@Param("user") User user);

    /**
     * 通过ID把软删除的数据恢复
     *
     * @param id id
     * @return 1/0
     */
    @Update("UPDATE user SET deleted = 0 WHERE id = #{id}")
    int recoverUserById(@Param("id") int id);


    /**
     * 搜索相关歌曲
     *
     * @param data 关键词
     * @return list
     */
    @Select("SELECT * FROM user WHERE username LIKE #{data}")
    List<User> SelectByUsername(@Param("data") String data);



    /**
     * 获取分页页数
     *
     * @param size 每页大小
     * @return int
     */
    @Select("SELECT CEIL(COUNT(*)/#{size}) FROM USER")
    int PageSize(@Param("size") int size);

    /**
     * 分页查询
     *
     * @param start 开始
     * @param len 大小
     * @return list
     */
    @Select("SELECT * FROM USER LIMIT #{start},#{len}")
    List<User> MySelectByPage(@Param("start") int start, @Param("len") int len);
}
