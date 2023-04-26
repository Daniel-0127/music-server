package com.music.dao;

//import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.music.entity.Admin;
import org.apache.ibatis.annotations.Mapper;

@Mapper
//@Repository
public interface AdminDao extends BaseMapper<Admin> {
}
