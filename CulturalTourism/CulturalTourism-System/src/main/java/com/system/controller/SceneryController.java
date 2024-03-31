package com.system.controller;

import base.Result;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.system.config.MyPage;
import com.system.service.HeritageService;
import com.system.service.HotelService;
import com.system.service.MuseumService;
import com.system.service.SceneryService;
import entity.Heritage;
import entity.Hotel;
import entity.Museum;
import entity.Scenery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/scenerySys")
@Api(tags = "景点相关接口")
public class SceneryController {

    @Resource
    public SceneryService sceneryService;

    public String sceneryImgUrl = "D:\\Study\\JavaWork\\CulturalTourism\\CulturalTourism-System\\src\\main\\resources\\static\\SceneryImg\\";
    /**
     * 删除景点照片
     * */
    @ApiOperation("删除景点照片")
    @GetMapping("/delSceneryImg")
    public Result delUpload(@RequestParam("fileName") String fileName){
        File file=new File(sceneryImgUrl+fileName);
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
     * 上传景点照片
     * */
    @ApiOperation("上传景点照片")
    @PostMapping("/uploadSceneryImg")
    public Result upload(@RequestBody MultipartFile file) throws IOException {
        String fileName=file.getOriginalFilename();
        File uploadFile=new File(sceneryImgUrl+fileName);
        file.transferTo(uploadFile);
        Map<String,String> map=new HashMap<>();
        map.put("sceneryImg",fileName);
        return Result.success(map);
    }

    /**
     * 添加景点
     * */
    @ApiOperation("添加景点信息")
    @PostMapping("/addScenery")
    public Result addHeritage(@RequestBody Scenery scenery){
        if (sceneryService.save(scenery)){
            return Result.success();
        }
        return Result.fail();
    }

    /**
     * 删除景点
     * */
    @ApiOperation("删除景点信息")
    @PostMapping("/delScenery")
    public Result delHeritage(@RequestBody Scenery scenery){
        if (sceneryService.removeById(scenery)){
            String[] imgList=scenery.getSceneryImg().split("/");
            for (String imgName:imgList){
                if (!imgName.equals("")){
                    File file=new File(sceneryImgUrl+imgName);
                    if (file.exists()) {
                        file.delete();
                    }
                }
            }
            return Result.success();
        }
        return Result.fail();
    }

    /**
     * 修改景点
     * */
    @ApiOperation("修改景点信息")
    @PostMapping("/updateScenery")
    public Result updateHeritage(@RequestBody Scenery scenery){
        if (sceneryService.updateById(scenery)){
            return Result.success();
        }
        return Result.fail();
    }


    /**
     * 分页查询
     * */
    @ApiOperation("非遗分页查询")
    @PostMapping("/selectPage")
    public Result selectPage(@RequestBody MyPage myPage){
        Page<Scenery> page=new Page<>();
        page.setCurrent(myPage.getPageNum());
        page.setSize(myPage.getPageSize());

        LambdaQueryWrapper<Scenery> lambdaQueryWrapper=new LambdaQueryWrapper<>();

        if(myPage.getParam().get("type").equals("景点类型") && !myPage.getParam().get("search").equals("null")) {
            lambdaQueryWrapper.eq(Scenery::getSceneryCategory, myPage.getParam().get("search"));
        }

        if(myPage.getParam().get("type").equals("所在城市") && !myPage.getParam().get("search").equals("null")) {
            lambdaQueryWrapper.eq(Scenery::getSceneryCity, myPage.getParam().get("search"));
        }

        if(myPage.getParam().get("type").equals("景点名称") && !myPage.getParam().get("search").equals("null")) {
            lambdaQueryWrapper.like(Scenery::getSceneryName,myPage.getParam().get("search"));
        }

        IPage<Scenery> result=sceneryService.page(page,lambdaQueryWrapper);
        if (page.getCurrent() > result.getPages()) {
            page.setCurrent(result.getPages());
            result = sceneryService.page(page, lambdaQueryWrapper);
        }
        if(result.getRecords().isEmpty()) {
            return Result.fail();
        }else {
            return Result.success((int) result.getTotal(), result.getRecords());
        }
    }
}
