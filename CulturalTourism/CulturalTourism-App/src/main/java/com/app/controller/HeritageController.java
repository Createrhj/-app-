package com.app.controller;

import base.Result;
import com.app.service.heritage.HeritageService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import entity.Heritage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/heritage")
@Api(tags = "非遗相关Api接口")
public class HeritageController {

    @Resource
    public HeritageService heritageService;


    /**
     * 查询非遗详情功能
     * */
    @ApiOperation("查询非遗详情功能")
    @GetMapping("/heritageDetail")
    public Result heritageDetail (@RequestParam("heritageID") int heritageID){
        LambdaQueryWrapper<Heritage> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Heritage::getHeritageID,heritageID);
        if (!heritageService.list(lambdaQueryWrapper).isEmpty()){
            return Result.success(heritageService.list(lambdaQueryWrapper));
        }
        return Result.fail();
    }

    /**
     * 查询符合调节的所有非遗
     * */
    @ApiOperation("查询符合调节的所有非遗")
    @PostMapping("/allHeritage")
    public Result allHeritage (@RequestBody Map<Object,Object> searchMap){

        String heritageName= (String) searchMap.get("heritageName");
        String heritageCategory=(String) searchMap.get("heritageCategory");
        String heritageCity=(String) searchMap.get("heritageCity");
        String earliestTime=(String) searchMap.get("earliestTime");
        String latestTime=(String) searchMap.get("latestTime");

        LambdaQueryWrapper<Heritage> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        if (!heritageName.equals("")){
            lambdaQueryWrapper.like(Heritage::getHeritageName,heritageName);
        }
        if (!heritageCategory.equals("")){
            lambdaQueryWrapper.eq(Heritage::getHeritageCategory,heritageCategory);
        }
        if (!heritageCity.equals("")){
            lambdaQueryWrapper.eq(Heritage::getHeritageCity,heritageCity);
        }
        if (!earliestTime.equals("")&&!latestTime.equals("")){
            lambdaQueryWrapper.between(Heritage::getApplicationTime,earliestTime,latestTime);
        }
        List<Heritage> heritages=heritageService.list(lambdaQueryWrapper);
        if (!heritages.isEmpty()){
            return Result.success(heritages);
        }
        return Result.fail();
    }

}
