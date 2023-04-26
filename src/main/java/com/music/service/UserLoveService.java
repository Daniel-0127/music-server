package com.music.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.music.dao.UserLoveDao;
import com.music.entity.MyPage;
import com.music.entity.Song;
import com.music.entity.UserLove;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserLoveService {
    @Resource
    private UserLoveDao userLoveDao;

    /**
     * 添加收藏
     *
     * @param user_id 用户id
     * @param song_id 歌曲id
     * @return 1/0
     */
    public int insert(int user_id, int song_id) {
        UserLove love = new UserLove(null, user_id, song_id, new Date());
        return userLoveDao.insert(love);
    }

    /**
     * 删除收藏
     *
     * @param id id
     * @return 1/0
     */
    public int remove(int id) {
            return userLoveDao.deleteById(id);
    }

    /**
     * 根据用户收藏分页查询
     *
     * @param myPage  传入第几页、每页大小
     * @param user_id 用户id
     * @return 一页数据
     */
    public Map<String, Object> SelectByUserLove(MyPage myPage, int user_id) {
        Page<UserLove> page = new Page<>(myPage.getPage(), myPage.getSize());
        UserLove userLove = new UserLove();
        userLove.setUser_id(user_id);
        QueryWrapper<UserLove> queryWrapper = new QueryWrapper<>(userLove);
        Page<UserLove> page1 = userLoveDao.selectPage(page, queryWrapper);
        HashMap<String, Object> map = new HashMap<>();
        map.put("total", page1.getPages());
        map.put("row", page1.getRecords());
        return map;

    }

    /**
     * 查询用户喜欢歌曲列表-通过时间逆序
     * @param user_id 用户id
     * @return 链表
     */
    public List<Song> SelectList(int user_id) {
        return userLoveDao.LoveList(user_id);
    }

    /**
     * 通过用户id和歌曲id查找一条数据
     * @param user_id 用户id
     * @param song_id 歌曲id
     * @return UserLove
     */
    public UserLove SelectOne(int user_id, int song_id){
        UserLove love = new UserLove(null, user_id, song_id, null);
        QueryWrapper<UserLove> queryWrapper = new QueryWrapper<>(love);
        return userLoveDao.selectOne(queryWrapper);
    }
}
