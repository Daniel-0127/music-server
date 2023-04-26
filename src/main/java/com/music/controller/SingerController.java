package com.music.controller;

import com.music.entity.MyPage;
import com.music.entity.Singer;
import com.music.service.SingerService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Api(tags = "歌手")
@RestController
@RequestMapping("/singer")
public class SingerController {
    @Resource
    private SingerService singerService;

    /**
     * 添加歌手（先判断用户名是否存在，存在则不添加，不存在则添加）
     *
     * @param singer 歌手
     * @return 结果
     */
    @Operation(summary = "添加歌手")
    @PostMapping("/insert")
    public boolean insert(@RequestBody Singer singer) {
        Singer singer1 = new Singer();
        singer1.setName(singer.getName());
        Singer singer2 = singerService.findDeletedUser(singer1);
        Singer singer3 = singerService.selectOne(singer1);
        if (singer2 != null) {
            return singerService.recover(singer2.getId());
        } else if (singer3 !=null) {
            return false;
        } else {
           return singerService.insert(singer);
        }

    }

    /**
     * 删除歌手（根据ID）
     *
     * @param id 歌手id
     * @return true/false
     */
    @Operation(summary = "删除歌手")
    @DeleteMapping("/remove")
    public boolean remove(int id) {
        return singerService.remove(id);
    }

    /**
     * 将软删除的歌手恢复
     *
     * @param singer 歌手id
     * @return true/false
     */
    @Operation(summary = "将软删除的歌手恢复")
    @PutMapping("/recover")
    public boolean recover(@RequestBody Singer singer) {

        return singerService.recover(singer.getId());
    }
    /**
     * 修改歌手信息
     *
     * @param singer 歌手
     * @return true/false
     */
    @Operation(summary = "修改歌手信息")
    @PutMapping("/update")
    public boolean update(@RequestBody Singer singer) {
        return singerService.update(singer);
    }

    /**
     * 通过ID查询歌手
     *
     * @param id 歌手ID
     * @return 歌手
     */
    @Operation(summary = "通过ID查询歌手")
    @GetMapping("/selectById")
    public Singer selectById(int id) {
        return singerService.selectById(id);
    }

    /**
     * 手写分页查询
     * @param myPage 页数和每页大小
     * @return map集合
     */
    @Operation(summary = "MySelectByPage")
    @GetMapping("/MySelectByPage")
    public  Map<String, Object> MySelectByPage(MyPage myPage){
        return singerService.MySelectByPage(myPage);
    }

    /**
     * 分页查询
     *
     * @param myPage 传入第几页、每页大小
     * @return 一页数据
     */
    @Operation(summary = "分页查询")
    @GetMapping("/select")
    public Map<String, Object> selectSingerMap(MyPage myPage) {
        return singerService.selectSingerMap(myPage);
    }

    /**
     * 性别查询歌手-分页
     *
     * @param myPage 传入第几页、每页大小
     * @param sex    性别
     * @return 一页数据
     */
    @Operation(summary = "性别查询歌手")
    @GetMapping("/selectBySex")
    public Map<String, Object> selectBySex(MyPage myPage, int sex) {
//        System.out.println(sex);
        return singerService.selectBySex(myPage, sex);
    }

    /**
     * 查询所有歌手数量和
     *
     * @return int
     */
    @Operation(summary = "查询所有歌手数量和")
    @GetMapping("/selectAllSinger")
    public int selectAllSinger() {
        return singerService.selectAllSinger();
    }

    /**
     * 根据歌手名查询列表
     * @param data 歌手
     * @return list
     */
    @Operation(summary = "根据歌手名查询")
    @GetMapping("/selectByNameList")
    public List<Singer> selectByNameList(String data){
        return singerService.selectByNameList(data);
    }


    /**
     * 按性别查询歌手数量和
     * @param sex 性别
     * @return int
     */
    @Operation(summary = "按性别查询歌手数量和")
    @GetMapping("/selectSexSinger")
    public int selectSexSinger(int sex){
        return singerService.selectSexSinger(sex);
    }

    public String upload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return "上传失败，请选择文件";
        }
        String fileName = UUID.randomUUID().toString() + ".jpg";
        String filePath = "D:\\upload\\";
        File dest = new File(filePath + fileName);
        try {
            file.transferTo(dest);
            return "上传成功";
        } catch (IOException e) {
            return "上传失败！";
        }
    }
}
