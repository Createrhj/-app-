package com.app.controller;

import base.Result;

import com.app.service.personal.CollectionService;
import com.app.service.personal.UserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import entity.Collection;
import entity.Heritage;
import entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/personal")
@Api(tags = "个人中心相关Api接口")
public class PersonalController {

    @Resource
    public UserService userService;

    @Resource
    public CollectionService collectionService;

    /**
     * 修改用户信息
     * */
    @ApiOperation("修改用户信息功能")
    @PostMapping("/update")
    public Result update(@RequestBody User user){
        if (userService.updateById(user)){
            return Result.success();
        }
        return Result.fail();
    }



    /**
     * 查看用户所有的收藏信息
     * */
    @ApiOperation("查看用户所有的收藏信息")
    @GetMapping("/collection")
    public Result collection(@RequestParam("userID") int userID){
        List<Heritage> collections=collectionService.selectHeritage(userID);
        if (!collections.isEmpty()){
            return Result.success(collections);
        }
        return Result.fail();
    }


    /**
     * 判断是否收藏了
     * */
    @ApiOperation("判断非遗是否被收藏")
    @GetMapping("/isCollection")
    public Result isCollection(@RequestParam("userID") int userID,@RequestParam("heritageID") int heritageID){
        LambdaQueryWrapper <Collection> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Collection::getUserID,userID).eq(Collection::getHeritageID,heritageID);
        if (!collectionService.list(lambdaQueryWrapper).isEmpty()){
            return Result.success();
        }
        return Result.fail();
    }

    /**
     * 添加非遗收藏功能
     * */
    @ApiOperation("添加非遗收藏功能")
    @GetMapping("/addCollection")
    public Result addCollection(@RequestParam("userID") int userID,@RequestParam("heritageID") int heritageID){
        Collection collection=new Collection();
        collection.setUserID(userID).setHeritageID(heritageID);
        if (collectionService.save(collection)){
            return Result.success();
        }
        return Result.fail();
    }

    /**
     * 取消非遗收藏功能
     * */
    @GetMapping("/delCollection")
    @ApiOperation("取消收藏功能")
    public Result delCollection(@RequestParam("userID") int userID,@RequestParam("heritageID") int heritageID){
        LambdaQueryWrapper<Collection> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Collection::getUserID,userID).eq(Collection::getHeritageID,heritageID);
        if (collectionService.remove(lambdaQueryWrapper)){
            return Result.success();
        }
        return Result.fail();
    }

}
