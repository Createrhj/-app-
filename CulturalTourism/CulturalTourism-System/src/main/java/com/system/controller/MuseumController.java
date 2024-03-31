package com.system.controller;

import base.Result;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.system.config.MyPage;
import com.system.service.HeritageService;
import com.system.service.HotelService;
import com.system.service.MuseumService;
import entity.Heritage;
import entity.Hotel;
import entity.Museum;
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
@RequestMapping("/museumSys")
@Api(tags = "馆藏相关接口")
public class MuseumController {

    @Resource
    public MuseumService museumService;

    public String museumImgUrl = "D:\\Study\\JavaWork\\CulturalTourism\\CulturalTourism-System\\src\\main\\resources\\static\\MuseumImg\\";
    /**
     * 删除馆藏照片
     * */
    @ApiOperation("删除馆藏照片")
    @GetMapping("/delMuseumImg")
    public Result delUpload(@RequestParam("fileName") String fileName){
        File file=new File(museumImgUrl+fileName);
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
     * 上传馆藏照片
     * */
    @ApiOperation("上传馆藏照片")
    @PostMapping("/uploadMuseumImg")
    public Result upload(@RequestBody MultipartFile file) throws IOException {
        String fileName=file.getOriginalFilename();
        File uploadFile=new File(museumImgUrl+fileName);
        file.transferTo(uploadFile);
        Map<String,String> map=new HashMap<>();
        map.put("museumImg",fileName);
        return Result.success(map);
    }

    /**
     * 添加馆藏
     * */
    @ApiOperation("添加馆藏信息")
    @PostMapping("/addMuseum")
    public Result addHeritage(@RequestBody Museum museum){
        if (museumService.save(museum)){
            return Result.success();
        }
        return Result.fail();
    }

    /**
     * 删除馆藏
     * */
    @ApiOperation("删除馆藏信息")
    @PostMapping("/delMuseum")
    public Result delHeritage(@RequestBody Museum museum){
        if (museumService.removeById(museum)){
            String[] imgList=museum.getMuseumImg().split("/");
            for (String imgName:imgList){
                if (!imgName.equals("")){
                    File file=new File(museumImgUrl+imgName);
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
     * 修改馆藏
     * */
    @ApiOperation("修改馆藏信息")
    @PostMapping("/updateMuseum")
    public Result updateHeritage(@RequestBody Museum museum){
        if (museumService.updateById(museum)){
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
        Page<Museum> page=new Page<>();
        page.setCurrent(myPage.getPageNum());
        page.setSize(myPage.getPageSize());

        LambdaQueryWrapper<Museum> lambdaQueryWrapper=new LambdaQueryWrapper<>();

        if(myPage.getParam().get("type").equals("馆藏类型") && !myPage.getParam().get("search").equals("null")) {
            lambdaQueryWrapper.eq(Museum::getMuseumCategory, myPage.getParam().get("search"));
        }

        if(myPage.getParam().get("type").equals("所在城市") && !myPage.getParam().get("search").equals("null")) {
            lambdaQueryWrapper.eq(Museum::getMuseumCity, myPage.getParam().get("search"));
        }

        if(myPage.getParam().get("type").equals("馆藏名称") && !myPage.getParam().get("search").equals("null")) {
            lambdaQueryWrapper.like(Museum::getMuseumName,myPage.getParam().get("search"));
        }

        IPage<Museum> result=museumService.page(page,lambdaQueryWrapper);
        if (page.getCurrent() > result.getPages()) {
            page.setCurrent(result.getPages());
            result = museumService.page(page, lambdaQueryWrapper);
        }
        if(result.getRecords().isEmpty()) {
            return Result.fail();
        }else {
            return Result.success((int) result.getTotal(), result.getRecords());
        }
    }
}
