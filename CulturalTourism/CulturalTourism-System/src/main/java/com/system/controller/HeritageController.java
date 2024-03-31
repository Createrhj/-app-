package com.system.controller;

import base.Result;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.system.config.MyPage;
import com.system.service.HeritageService;
import entity.Heritage;
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
@RequestMapping("/heritageSys")
@Api(tags = "非遗相关接口")
public class HeritageController {

    @Resource
    public HeritageService heritageService;

    public String heritageImgUrl = "D:\\Study\\JavaWork\\CulturalTourism\\CulturalTourism-System\\src\\main\\resources\\static\\HeritageImg\\";
    /**
     * 删除非遗照片
     * */
    @ApiOperation("删除非遗照片")
    @GetMapping("/delHeritageImg")
    public Result delUpload(@RequestParam("fileName") String fileName){
        File file=new File(heritageImgUrl+fileName);
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
     * 上传非遗照片
     * */
    @ApiOperation("上传非遗照片")
    @PostMapping("/uploadHeritageImg")
    public Result upload(@RequestBody MultipartFile file) throws IOException {
        String fileName=file.getOriginalFilename();
        File uploadFile=new File(heritageImgUrl+fileName);
        file.transferTo(uploadFile);
        Map<String,String> map=new HashMap<>();
        map.put("heritageImg",fileName);
        return Result.success(map);
    }

    /**
     * 添加非遗
     * */
    @ApiOperation("添加非遗信息")
    @PostMapping("/addHeritage")
    public Result addHeritage(@RequestBody Heritage heritage){
        if (heritageService.save(heritage)){
            return Result.success();
        }
        return Result.fail();
    }

    /**
     * 删除非遗
     * */
    @ApiOperation("删除非遗信息")
    @PostMapping("/delHeritage")
    public Result delHeritage(@RequestBody Heritage heritage){
        if (heritageService.removeById(heritage)){
            String[] imgList=heritage.getHeritageImg().split("/");
            for (String imgName:imgList){
                if (!imgName.equals("")){
                    File file=new File(heritageImgUrl+imgName);
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
     * 修改非遗
     * */
    @ApiOperation("修改非遗信息")
    @PostMapping("/updateHeritage")
    public Result updateHeritage(@RequestBody Heritage heritage){
        if (heritageService.updateById(heritage)){
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
        Page<Heritage> page=new Page<>();
        page.setCurrent(myPage.getPageNum());
        page.setSize(myPage.getPageSize());

        LambdaQueryWrapper<Heritage> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        if(myPage.getParam().get("type").equals("所属市州") && !myPage.getParam().get("search").equals("null")) {
            lambdaQueryWrapper.like(Heritage::getHeritageCity, myPage.getParam().get("search"));
        }

        if(myPage.getParam().get("type").equals("非遗名称") && !myPage.getParam().get("search").equals("null")) {
            lambdaQueryWrapper.like(Heritage::getHeritageName,myPage.getParam().get("search"));
        }

        IPage<Heritage> result=heritageService.page(page,lambdaQueryWrapper);
        if (page.getCurrent() > result.getPages()) {
            page.setCurrent(result.getPages());
            result = heritageService.page(page, lambdaQueryWrapper);
        }
        if(result.getRecords().isEmpty()) {
            return Result.fail();
        }else {
            return Result.success((int) result.getTotal(), result.getRecords());
        }
    }
}
