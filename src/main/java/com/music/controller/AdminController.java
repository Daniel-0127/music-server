package com.music.controller;

import com.music.entity.Admin;
import com.music.service.AdminService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
@Api(tags = "管理员")
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Resource
    private AdminService adminService;

    /**
     * 添加管理员
     *
     * @param admin 管理员
     * @return true/false
     */
    @Operation(summary = "添加管理员")
    @PostMapping("/insert")
    public boolean insert(@RequestBody Admin admin) {
        return adminService.insert(admin);
    }

    /**
     * 根据ID修改管理员信息
     *
     * @param admin 管理员信息(ID、昵称、密码)
     * @return true/false
     */
    @Operation(summary = "根据ID修改管理员信息")
    @PutMapping("/update")
    public boolean update(@RequestBody Admin admin) {
        return adminService.update(admin);
    }

    /**
     * 根据账号密码登陆
     * @param admin 账号密码
     * @return 账号
     */
    @Operation(summary = "根据账号密码登陆")
    @GetMapping("/selectOne")
    public Admin selectOne(Admin admin){
        return adminService.selectOne(admin);
    }

    /**
     * 查询所有管理员
     *
     * @return 管理员列表
     */
    @Operation(summary = "查询所有管理员")
    @GetMapping("/select")
    public List<Admin> select() {
        return adminService.select();
    }

}
