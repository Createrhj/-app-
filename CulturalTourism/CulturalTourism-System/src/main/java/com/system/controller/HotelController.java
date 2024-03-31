package com.system.controller;

import base.Result;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.system.config.MyPage;
import com.system.service.HeritageService;
import com.system.service.HotelService;
import entity.Heritage;
import entity.Hotel;
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
@RequestMapping("/hotelSys")
@Api(tags = "住宿相关接口")
public class HotelController {

    @Resource
    public HotelService hotelService;

    public String hotelImgUrl = "D:\\Study\\JavaWork\\CulturalTourism\\CulturalTourism-System\\src\\main\\resources\\static\\HotelImg\\";
    /**
     * 删除酒店照片
     * */
    @ApiOperation("删除酒店照片")
    @GetMapping("/delHotelImg")
    public Result delUpload(@RequestParam("fileName") String fileName){
        File file=new File(hotelImgUrl+fileName);
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
     * 上传酒店照片
     * */
    @ApiOperation("上传酒店照片")
    @PostMapping("/uploadHotelImg")
    public Result upload(@RequestBody MultipartFile file) throws IOException {
        String fileName=file.getOriginalFilename();
        File uploadFile=new File(hotelImgUrl+fileName);
        file.transferTo(uploadFile);
        Map<String,String> map=new HashMap<>();
        map.put("hotelImg",fileName);
        return Result.success(map);
    }

    /**
     * 添加酒店
     * */
    @ApiOperation("添加酒店信息")
    @PostMapping("/addHotel")
    public Result addHeritage(@RequestBody Hotel hotel){
        if (hotelService.save(hotel)){
            return Result.success();
        }
        return Result.fail();
    }

    /**
     * 删除酒店
     * */
    @ApiOperation("删除酒店信息")
    @PostMapping("/delHotel")
    public Result delHeritage(@RequestBody Hotel hotel){
        if (hotelService.removeById(hotel)){
            String[] imgList=hotel.getHotelImg().split("/");
            for (String imgName:imgList){
                if (!imgName.equals("")){
                    File file=new File(hotelImgUrl+imgName);
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
     * 修改酒店
     * */
    @ApiOperation("修改酒店信息")
    @PostMapping("/updateHotel")
    public Result updateHeritage(@RequestBody Hotel hotel){
        if (hotelService.updateById(hotel)){
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
        Page<Hotel> page=new Page<>();
        page.setCurrent(myPage.getPageNum());
        page.setSize(myPage.getPageSize());

        LambdaQueryWrapper<Hotel> lambdaQueryWrapper=new LambdaQueryWrapper<>();

        if(myPage.getParam().get("type").equals("住宿类型") && !myPage.getParam().get("search").equals("null")) {
            lambdaQueryWrapper.eq(Hotel::getHotelCategory, myPage.getParam().get("search"));
        }

        if(myPage.getParam().get("type").equals("所在城市") && !myPage.getParam().get("search").equals("null")) {
            lambdaQueryWrapper.eq(Hotel::getHotelCity, myPage.getParam().get("search"));
        }

        if(myPage.getParam().get("type").equals("住宿名称") && !myPage.getParam().get("search").equals("null")) {
            lambdaQueryWrapper.like(Hotel::getHotelName,myPage.getParam().get("search"));
        }

        IPage<Hotel> result=hotelService.page(page,lambdaQueryWrapper);
        if (page.getCurrent() > result.getPages()) {
            page.setCurrent(result.getPages());
            result = hotelService.page(page, lambdaQueryWrapper);
        }
        if(result.getRecords().isEmpty()) {
            return Result.fail();
        }else {
            return Result.success((int) result.getTotal(), result.getRecords());
        }
    }
}
