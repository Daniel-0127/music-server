package com.music.controller;

import com.music.entity.MyPage;
import com.music.entity.User;
import com.music.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Api(tags = "用户")
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    /**
     * 添加用户
     *
     * @param user 用户
     * @return true/false
     */
    @Operation(summary = "添加用户")
    @PostMapping("/insert")

    public boolean insert(@RequestBody User user) {
        User user1 = new User();
        user1.setUsername(user.getUsername());
        user1.setPassword(user.getPassword());
        User user2 = userService.findDeletedUser(user1);
        User user3 = userService.selectOne(user1);
        if (user2 != null) {
            return userService.recover(user2.getId());
        } else if (user3 != null) {
            return false;
        } else {
            return userService.insert(user);
        }
    }

    /**
     * 将软删除的用户恢复
     *
     * @param user 用户id
     * @return true/false
     */
    @Operation(summary = "将软删除的用户恢复")
    @PutMapping("/recover")

    public boolean recover(@RequestBody User user) {

        return userService.recover(user.getId());
    }

    /**
     * 删除用户
     *
     * @param id 用户ID
     * @return true/false
     */
    @Operation(summary = "删除用户")
    @DeleteMapping("/remove")
    public boolean remove(int id) {
        return userService.remove(id);
    }

    /**
     * 通过ID修改用户数据
     *
     * @param user 用户
     * @return true/false
     */
    @Operation(summary = "修改数据")
    @PutMapping("/update")
    public boolean update(@RequestBody User user) {
        return userService.update(user);
    }

    /**
     * 查询用户
     *
     * @param user 账号密码
     * @return 用户
     */
    @Operation(summary = "查询用户")
    @GetMapping("/selectOne")
    public User selectOne(User user) {
        return userService.selectOne(user);
    }

    /**
     * 分页查询
     *
     * @param myPage 传入第几页、每页大小
     * @return 一页数据
     */

    @Operation(summary = "查询用户集合")
    @GetMapping("/selectUserMap")
    public Map<String, Object> selectUserMap(MyPage myPage) {
        return userService.selectUserMap(myPage);
    }

    /**
     * 查询所有用户数量和
     * @return int
     */
    @Operation(summary = "查询所有用户数量和")
    @GetMapping("/selectAllNum")
    public int selectAllNum(){
       return userService.selectAllNum();
    }

    /**
     * 根据性别查询用户数量和
     * @param sex 性别
     * @return int
     */
    @Operation(summary = "根据性别查询用户数量和")
    @GetMapping("/selectSexNum")
    public int selectSexNum(int sex){
        return userService.selectSexNum(sex);
    }

    /**
     * 根据昵称搜索相关歌曲
     *
     * @param data 姓名
     * @return list
     */
    @Operation(summary = "分页条件查询")
    @GetMapping("/SelectByUsernameList")
    public List<User> SelectByUsernameList(String data) {
        return userService.SelectByUsernameList(data);
    }

    /**
     * 手写分页查询
     * @param myPage 页数和每页大小
     * @return map集合
     */
    @Operation(summary = "MySelectByPage")
    @GetMapping("/MySelectByPage")
    public  Map<String, Object> MySelectByPage(MyPage myPage){
        return userService.MySelectByPage(myPage);
    }

    /**
     * 修改头像
     * @param file 头像
     * @param id 用户id
     * @return 结果
     */
    @Operation(summary = "修改头像")
    @PutMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file, int id) {
        return userService.upload(file, id);
    }
}
