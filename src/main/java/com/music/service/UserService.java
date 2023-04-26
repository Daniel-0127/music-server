package com.music.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.music.entity.MyPage;
import com.music.entity.User;
import com.music.dao.UserDao;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


@Service
public class UserService {
    @Resource
    private UserDao userDao;

    /**
     * 新增用户
     *
     * @param user 用户
     * @return true/false
     */
    public boolean insert(User user) {
        String birth = String.valueOf(user.getBirth());
        System.out.println(birth);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date myBirth = new Date();
        try {
            myBirth = dateFormat.parse(birth);
        } catch (Exception e) {
            e.printStackTrace();
        }
        user.setBirth(myBirth);
        user.setCreate_time(new Date());
        user.setUpdate_time(new Date());
        return userDao.insert(user) == 1;
    }

    /**
     * 删除用户
     *
     * @param id 用户ID
     * @return true/false
     */
    public boolean remove(int id) {
        User user = new User();
        user.setId(id);
        user.setUpdate_time(new Date());
        userDao.updateById(user);
        return userDao.deleteById(id) == 1;
    }

    /**
     * 将软删除的用户恢复
     *
     * @param id 用户
     * @return true/false
     */
    public boolean recover(int id) {
        int i = userDao.recoverUserById(id);
        User user = userDao.selectById(id);
        user.setUpdate_time(new Date());
        return userDao.updateById(user) == 1;
    }

    /**
     * 修改用户数据
     *
     * @param user 用户
     * @return true/false
     */

    public boolean update(User user) {
        return userDao.updateById(user) == 1;
    }

    /**
     * 寻找被删除的用户
     *
     * @param user 用户账号和密码
     * @return 返回用户
     */
    public User findDeletedUser(User user) {
        return userDao.findDeletedUser(user);
    }

    /**
     * 查询一个用户
     *
     * @param user
     * @return
     */
    public User selectOne(User user) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>(user);
        return userDao.selectOne(queryWrapper);
    }

    /**
     * 查询用户数量和
     *
     * @return int
     */
    public int selectAllNum() {
        return userDao.selectList(null).size();
    }

    /**
     * 根据性别查询用户数量和
     *
     * @param sex 性别
     * @return int
     */
    public int selectSexNum(int sex) {
        User user = new User();
        user.setSex(sex);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>(user);
        return userDao.selectList(queryWrapper).size();
    }

    /**
     * 分页查询
     *
     * @param myPage 传入第几页、每页大小
     * @return 一页数据
     */
    public Map<String, Object> selectUserMap(MyPage myPage) {
        //1.创建分页对象
        //参数1代表第几页 参数2代表每页显示多少行
        //select*from food where like 起始行数：0,查询行数：2;
        //（页数-1）*行数 = 查询的起始行数
        Page<User> page = new Page<>(myPage.getPage(), myPage.getSize());
        Page<User> page1 = userDao.selectPage(page, null);
        HashMap<String, Object> map = new HashMap<>();
        map.put("total", page1.getPages());
        map.put("row", page1.getRecords());
        return map;

    }

    /**
     * 根据昵称搜索相关歌曲
     *
     * @param data 姓名
     * @return list
     */
    public List<User> SelectByUsernameList(String data) {
        data = "%" + data + "%";
        return userDao.SelectByUsername(data);

    }

    /**
     * 手写分页查询
     * @param myPage 页数和每页大小
     * @return map集合
     */
    public  Map<String, Object> MySelectByPage(MyPage myPage){
        int PageSum = userDao.PageSize(myPage.getSize());
        int start = (myPage.getPage()-1)* myPage.getSize();
        int len = myPage.getSize();
        List<User> list = userDao.MySelectByPage(start, len);
        HashMap<String, Object> map = new HashMap<>();
        map.put("MyData",list);
        map.put("PageSum",PageSum);
        return map;
    }

    /**
     * 修改头像
     *
     * @param file 头像
     * @param id   用户id
     * @return 结果
     */
    public String upload(MultipartFile file, int id) {
        if (file.isEmpty()) {
            return "上传失败，请选择文件";
        }
        String fileName = id + ".jpg";
        String filePath = "E:\\yz-music\\img\\User\\";
        File dest = new File(filePath + fileName);
        try {
            file.transferTo(dest);
            return "上传成功";
        } catch (IOException e) {
            return "上传失败！";
        }
    }


}
