package com.system.controller;

import base.Result;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.system.config.MyPage;
import com.system.service.*;
import entity.*;
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
@RequestMapping("/theaterSys")
@Api(tags = "剧场相关接口")
public class TheaterController {

    @Resource
    public TheaterService theaterService;

    public String theaterImgUrl = "D:\\Study\\JavaWork\\CulturalTourism\\CulturalTourism-System\\src\\main\\resources\\static\\TheaterImg\\";
    /**
     * 删除剧场照片
     * */
    @ApiOperation("删除剧场照片")
    @GetMapping("/delTheaterImg")
    public Result delUpload(@RequestParam("fileName") String fileName){
        File file=new File(theaterImgUrl+fileName);
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
     * 上传剧场照片
     * */
    @ApiOperation("上传剧场照片")
    @PostMapping("/uploadTheaterImg")
    public Result upload(@RequestBody MultipartFile file) throws IOException {
        String fileName=file.getOriginalFilename();
        File uploadFile=new File(theaterImgUrl+fileName);
        file.transferTo(uploadFile);
        Map<String,String> map=new HashMap<>();
        map.put("theaterImg",fileName);
        return Result.success(map);
    }

    /**
     * 添加剧场
     * */
    @ApiOperation("添加剧场信息")
    @PostMapping("/addTheater")
    public Result addHeritage(@RequestBody Theater theater){
        if (theaterService.save(theater)){
            return Result.success();
        }
        return Result.fail();
    }

    /**
     * 删除剧场
     * */
    @ApiOperation("删除剧场信息")
    @PostMapping("/delTheater")
    public Result delHeritage(@RequestBody Theater theater){
        if (theaterService.removeById(theater)){
            String[] imgList=theater.getTheaterImg().split("/");
            for (String imgName:imgList){
                if (!imgName.equals("")){
                    File file=new File(theaterImgUrl+imgName);
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
     * 修改剧场
     * */
    @ApiOperation("修改剧场信息")
    @PostMapping("/updateTheater")
    public Result updateHeritage(@RequestBody Theater theater){
        if (theaterService.updateById(theater)){
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
        Page<Theater> page=new Page<>();
        page.setCurrent(myPage.getPageNum());
        page.setSize(myPage.getPageSize());

        LambdaQueryWrapper<Theater> lambdaQueryWrapper=new LambdaQueryWrapper<>();

        if(myPage.getParam().get("type").equals("剧场类型") && !myPage.getParam().get("search").equals("null")) {
            lambdaQueryWrapper.eq(Theater::getTheaterCategory, myPage.getParam().get("search"));
        }

        if(myPage.getParam().get("type").equals("所在城市") && !myPage.getParam().get("search").equals("null")) {
            lambdaQueryWrapper.eq(Theater::getTheaterCity, myPage.getParam().get("search"));
        }

        if(myPage.getParam().get("type").equals("剧场名称") && !myPage.getParam().get("search").equals("null")) {
            lambdaQueryWrapper.like(Theater::getTheaterName,myPage.getParam().get("search"));
        }

        IPage<Theater> result=theaterService.page(page,lambdaQueryWrapper);
        if (page.getCurrent() > result.getPages()) {
            page.setCurrent(result.getPages());
            result = theaterService.page(page, lambdaQueryWrapper);
        }
        if(result.getRecords().isEmpty()) {
            return Result.fail();
        }else {
            return Result.success((int) result.getTotal(), result.getRecords());
        }
    }
}
