package com.music.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.music.entity.Admin;
import com.music.dao.AdminDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class AdminService {
    @Resource
    public AdminDao adminDao;

    /**
     * 添加管理员
     *
     * @param admin 管理员
     * @return true/false
     */
    public boolean insert(Admin admin) {
        return adminDao.insert(admin) == 1;
    }

    /**
     * 删除管理员(只有ID)
     *
     * @param id 管理员ID
     * @return true/false
     */

    public boolean remove(int id) {
        return adminDao.deleteById(id) == 1;

    }

    /**
     * 删除管理员（根据具体类）
     *
     * @param admin 管理员信息(昵称、密码)
     * @return true/false
     */
    public boolean remove(Admin admin) {
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        return adminDao.delete(queryWrapper) == 1;
    }

    /**
     * 根据ID修改管理员信息
     *
     * @param admin 管理员信息(ID、昵称、密码)
     * @return true/false
     */
    public boolean update(Admin admin) {
        return adminDao.updateById(admin) == 1;
    }

    /**
     * 根据ID查询
     *
     * @param id 管理员ID
     * @return 详细信息(Admin)
     */
    public Admin select(int id) {
        return adminDao.selectById(id);
    }

    /**
     * 查找一个
     * @param admin 管理员信息(昵称、密码)
     * @return 管理员
     */
    public Admin selectOne(Admin admin) {
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>(admin);
        return adminDao.selectOne(queryWrapper);
    }

    /**
     * 查询所有管理员
     * @return 管理员列表
     */
    public List<Admin> select(){
        return adminDao.selectList(null);
    }
}
