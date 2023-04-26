package com.music.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.music.dao.SingerDao;
import com.music.entity.MyPage;
import com.music.entity.Singer;
import com.music.entity.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SingerService {


    @Resource
    private SingerDao singerDao;

    /**
     * 添加歌手
     *
     * @param singer 歌手
     * @return true/false
     */
    public boolean insert(Singer singer) {
        singer.setCreate_time(new Date());
        return singerDao.insert(singer) == 1;
    }

    /**
     * 根据用户名查找歌手
     *
     * @param singer 歌手名
     * @return 歌手
     */
    public Singer selectByName(Singer singer) {
        Singer singer1 = new Singer();
        singer1.setName(singer.getName());
        QueryWrapper<Singer> singerQueryWrapper = new QueryWrapper<>(singer1);
        return singerDao.selectOne(singerQueryWrapper);
    }

    /**
     * 根据ID删除歌手
     *
     * @param id 歌手ID
     * @return true/false
     */
    public boolean remove(int id) {
        return singerDao.deleteById(id) == 1;
    }

    /**
     * 将软删除的歌手恢复
     *
     * @param id 歌手
     * @return true/false
     */
    public boolean recover(int id) {
        int i = singerDao.recoverSingerById(id);

        return i == 1;
    }


    /**
     * 通过歌手ID修改歌手
     *
     * @param singer 歌手信息
     * @return true/false
     */
    public boolean update(Singer singer) {
        singer.setCreate_time(new Date());
        return singerDao.updateById(singer) == 1;
    }

    /**
     * 查询歌手是否已存在
     * @param singer 歌手
     * @return 歌手
     */
    public Singer selectOne(Singer singer){
        QueryWrapper<Singer> queryWrapper =new QueryWrapper<>(singer);
        return singerDao.selectOne(queryWrapper);
    }

    /**
     * 寻找被删除的歌手
     *
     * @param singer 歌手
     * @return 返回歌手
     */
    public Singer findDeletedUser(Singer singer) {
        return singerDao.findDeletedSinger(singer);
    }

    /**
     * 通过ID查询歌手
     *
     * @param id 歌手ID
     * @return 歌手
     */
    public Singer selectById(int id) {
        return singerDao.selectById(id);
    }

    /**
     * 手写分页查询
     * @param myPage 页数和每页大小
     * @return map集合
     */
    public  Map<String, Object> MySelectByPage(MyPage myPage){
        int PageSum = singerDao.PageSize(myPage.getSize());
        int start = (myPage.getPage()-1)* myPage.getSize();
        int len = myPage.getSize();
        List<Singer> list = singerDao.MySelectByPage(start, len);
        HashMap<String, Object> map = new HashMap<>();
        map.put("MyData",list);
        map.put("PageSum",PageSum);
        return map;
    }

    /**
     * 分页查询
     *
     * @param myPage 传入第几页、每页大小
     * @return 一页数据
     */
    public Map<String, Object> selectSingerMap(MyPage myPage) {
        Page<Singer> page = new Page<>(myPage.getPage(), myPage.getSize());
        Page<Singer> page1 = singerDao.selectPage(page, null);
        HashMap<String, Object> map = new HashMap<>();
        map.put("total", page1.getPages());
        map.put("row", page1.getRecords());
        return map;

    }

    /**
     * 查询所有歌手数量和
     *
     * @return int
     */
    public int selectAllSinger() {
        return singerDao.selectList(null).size();
    }

    /**
     * 性别查询歌手-分页
     *
     * @param myPage 传入第几页、每页大小
     * @param sex    性别
     * @return 一页数据
     */
    public Map<String, Object> selectBySex(MyPage myPage, int sex) {
        Page<Singer> page = new Page<>(myPage.getPage(), myPage.getSize());
        Singer singer = new Singer();
        singer.setSex(sex);
        QueryWrapper<Singer> singerQueryWrapper = new QueryWrapper<>(singer);
        Page<Singer> page1 = singerDao.selectPage(page, singerQueryWrapper);
        HashMap<String, Object> map = new HashMap<>();
        map.put("total", page1.getPages());
        map.put("row", page1.getRecords());
        return map;

    }

    /**
     * 按性别查询歌手数量和
     *
     * @param sex 性别
     * @return int
     */
    public int selectSexSinger(int sex) {
        Singer singer = new Singer();
        singer.setSex(sex);
        QueryWrapper<Singer> queryWrapper = new QueryWrapper<>(singer);
        return singerDao.selectList(queryWrapper).size();
    }

    /**
     * 根据歌手名查询列表
     *
     * @param data 歌手
     * @return list
     */
    public List<Singer> selectByNameList(String data) {
        data = "%" + data + "%";
        return singerDao.selectByName(data);
    }

}
