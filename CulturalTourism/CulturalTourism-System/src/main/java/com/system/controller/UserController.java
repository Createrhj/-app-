package com.system.controller;

import base.Result;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.system.config.MyPage;
import com.system.service.UserService;
import entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/userSys")
@Api(tags = "用户相关接口")
public class UserController {

    @Resource
    public UserService userService;

    public String avatarUrl = "D:\\Study\\JavaWork\\CulturalTourism\\CulturalTourism-System\\src\\main\\resources\\static\\UserAvatar\\";

    /**
     * 用户注册
     * */
    @ApiOperation("用户注册")
    @PostMapping("/userRegister")
    public Result userRegister(@RequestBody User user){
        user.setUserRole("管理员");
        if (userService.save(user)){
            return Result.success();
        }
        return Result.fail();
    }

    /**
     * 用户登录
     * */
    @ApiOperation("用户登录")
    @GetMapping("/userLogin")
    public Result userLogin(@RequestParam("userAccount") String userAccount,@RequestParam("userPassword") String userPassword){
        LambdaQueryWrapper<User> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getUserAccount,userAccount).eq(User::getUserPassword,userPassword);
        List<User> user=userService.list(lambdaQueryWrapper);
        if (user.isEmpty()){
            return Result.fail();
        }
        else {
            if (user.get(0).getUserRole().equals("管理员")){
                return Result.success();
            }
        }
        return Result.fail();
    }

    /**
     * 删除用户头像
     * */
    @ApiOperation("删除用户头像")
    @GetMapping("/delAvatar")
    public Result delUpload(@RequestParam("fileName") String fileName){
        File file=new File(avatarUrl+fileName);
        if (file.exists()) {
            if (file.delete()) {
                return Result.success();
            } else {
                return Result.fail();
            }
        }else {
            return Result.fail();
        }
    }

    /**
     * 上传用户头像
     * */
    @ApiOperation("上传用户头像")
    @PostMapping("/uploadAvatar")
    public Result upload(@RequestBody MultipartFile file) throws IOException {
        String fileName=file.getOriginalFilename();
        File uploadFile=new File(avatarUrl+fileName);
        file.transferTo(uploadFile);
        Map<String,String> map=new HashMap<>();
        map.put("userAvatar",fileName);
        return Result.success(map);
    }

    /**
     * 删除用户
     * */
    @ApiOperation("删除用户")
    @PostMapping("/delUser")
    public Result delUser(@RequestBody User user){
        if (userService.removeById(user)){
            File file=new File(avatarUrl+user.getUserAvatar());
            if (file.exists()) {
                file.delete();
            }
            return Result.success();
        }
        return Result.fail();
    }

    /**
     * 修改用户
     * */
    @ApiOperation("修改用户")
    @PostMapping("/updateUser")
    public Result updateUser(@RequestBody User user){
        LambdaQueryWrapper<User> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getUserID,user.getUserID());
        User oldUser=userService.getOne(lambdaQueryWrapper);
        if (!oldUser.getUserAvatar().equals(user.getUserAvatar())){
            File file=new File(avatarUrl+oldUser.getUserAvatar());
            if (file.exists()) {
                file.delete();
            }
        }
        if (userService.updateById(user)){
            return Result.success();
        }
        return Result.fail();
    }

    /**
     * 分页查询
     * */
    @ApiOperation("用户分页查询")
    @PostMapping("/selectPage")
    public Result selectPage(@RequestBody MyPage myPage){
        Page<User> page=new Page<>();
        page.setCurrent(myPage.getPageNum());
        page.setSize(myPage.getPageSize());

        LambdaQueryWrapper<User> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        if(myPage.getParam().get("type").equals("用户昵称") && !myPage.getParam().get("search").equals("null")) {
            lambdaQueryWrapper.like(User::getUserName, myPage.getParam().get("search"));
        }

        if (myPage.getParam().get("type").equals("用户账号") && !myPage.getParam().get("search").equals("null")){
            lambdaQueryWrapper.eq(User::getUserAccount, myPage.getParam().get("search"));
        }

        if (myPage.getParam().get("type").equals("用户权限") && !myPage.getParam().get("search").equals("null")){
            lambdaQueryWrapper.eq(User::getUserRole, myPage.getParam().get("search"));
        }

        IPage<User> result=userService.page(page,lambdaQueryWrapper);
        if (page.getCurrent() > result.getPages()) {
            page.setCurrent(result.getPages());
            result = userService.page(page, lambdaQueryWrapper);
        }

        if(result.getRecords().isEmpty()) {
            return Result.fail();
        }else {
            return Result.success((int) result.getTotal(), result.getRecords());
        }
    }
}
