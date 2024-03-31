package com.app.controller;
import base.Result;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.app.service.login.LoginService;
import entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/login")
@Api(tags = "登录相关Api接口")
public class LoginController {
    @Resource
    public LoginService loginService;

    /**
     * 用户注册功能
     * */
    @ApiOperation("用户注册功能")
    @PostMapping("/userRegister")
    public Result Register(@RequestBody User user){
        LambdaQueryWrapper<User> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getUserPhone,user.getUserPhone()).or().eq(User::getUserAccount,user.getUserAccount());
        if (!loginService.list(lambdaQueryWrapper).isEmpty()){
            return Result.fail();
        }else {
            user.setUserRole("普通用户");
            loginService.save(user);
            return Result.success();
        }
    }

    /**
     * 用户登录功能
     * */
    @ApiOperation("用户登录功能")
    @GetMapping("/userLogin")
    public Result Login(@RequestParam("userAccount") String userAccount,@RequestParam("userPassword") String userPassword){
        List<User> user=loginService.lambdaQuery().eq(User::getUserAccount,userAccount).eq(User::getUserPassword,userPassword).list();
        if (user.isEmpty()){
            return Result.fail();
        }else {
            Map<String, Object> map = new HashMap<>();
            map.put("CurUser", user.get(0));
            return Result.success(map);
        }
    }

    /**
     * 用户身份验证功能
     * */
    @ApiOperation("用户身份验证功能")
    @GetMapping("/verify")
    public Result Verify(@RequestParam("userAccount") String userAccount,@RequestParam("userPhone") String userPhone){
        LambdaQueryWrapper<User> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getUserAccount,userAccount).eq(User::getUserPhone,userPhone);
        if (loginService.list(lambdaQueryWrapper).isEmpty()){
            return Result.fail();
        }else {
            return Result.success();
        }
    }
    /**
     * 用户修改密码功能
     * */
    @ApiOperation("用户修改密码功能")
    @GetMapping("/changePassword")
    public Result changePassword(@RequestParam("userAccount") String userAccount,@RequestParam("newPassword") String newPassword){
        LambdaUpdateWrapper<User> lambdaUpdateWrapper=new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper.eq(User::getUserAccount,userAccount).set(User::getUserPassword,newPassword);
        if (loginService.update(lambdaUpdateWrapper)){
            return Result.success();
        }
        return Result.fail();
    }

}



