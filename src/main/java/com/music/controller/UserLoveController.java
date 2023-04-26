package com.music.controller;

import com.music.entity.MyPage;
import com.music.entity.Song;
import com.music.entity.UserLove;
import com.music.service.UserLoveService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@Api(tags = "用户收藏")
@RequestMapping("/love")
public class UserLoveController {
    @Resource
    private UserLoveService userLoveService;

    /**
     * 添加收藏
     *
     * @param userLove 用户id和歌曲id
     * @return boolean
     */
    @Operation(summary = "添加收藏")
    @PostMapping("/insert")
    public boolean insert(@RequestBody UserLove userLove) {
        UserLove userLove1 = userLoveService.SelectOne(userLove.getUser_id(), userLove.getSong_id());
        if (userLove1 == null) {
            return userLoveService.insert(userLove.getUser_id(), userLove.getSong_id()) == 1;
        } else {
            return false;
        }
    }

    /**
     * 删除收藏
     *
     * @param userLove 用户id和歌曲id
     * @return boolean
     */
    @Operation(summary = "删除收藏")
    @DeleteMapping("/remove")
    public boolean remove(UserLove userLove) {
        UserLove userLove1 = userLoveService.SelectOne(userLove.getUser_id(), userLove.getSong_id());
        if (userLove1 != null) {
            return userLoveService.remove(userLove1.getId()) == 1;
        } else {
            return false;
        }

    }

    /**
     * 根据用户收藏分页查询
     *
     * @param myPage  传入第几页、每页大小
     * @param user_id 用户id
     * @return 一页数据
     */
    @Operation(summary = "根据用户收藏分页查询")
    @GetMapping("/SelectByUserLove")
    public Map<String, Object> SelectByUserLove(MyPage myPage, int user_id) {
        return userLoveService.SelectByUserLove(myPage, user_id);
    }

    /**
     * 查询用户喜欢歌曲列表-通过时间逆序
     *
     * @param user_id 用户id
     * @return 链表
     */
    @Operation(summary = "查询用户喜欢歌曲列表-通过时间逆序")
    @GetMapping("/SelectList")
    public List<Song> SelectList(int user_id) {
        return userLoveService.SelectList(user_id);
    }

    /**
     * 查询一条数据
     * @param userLove 用户id和歌曲id
     * @return 是否收藏
     */
    @Operation(summary = "查找一条数据")
    @GetMapping("/SelectOne")
    public boolean SelectOne(UserLove userLove) {
        return userLoveService.SelectOne(userLove.getUser_id(), userLove.getSong_id()) != null;
    }

}
