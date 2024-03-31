package com.system.controller;

import base.Result;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.system.config.MyPage;
import com.system.service.GoodsService;
import entity.Goods;
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
@RequestMapping("/goodsSys")
@Api(tags = "商品相关接口")
public class GoodsController {

    @Resource
    public GoodsService goodsService;

    public String goodsImgUrl = "D:\\Study\\JavaWork\\CulturalTourism\\CulturalTourism-System\\src\\main\\resources\\static\\GoodsImg\\";

    /**
     * 删除商品照片
     * */
    @ApiOperation("删除商品照片")
    @GetMapping("/delGoodsImg")
    public Result delUpload(@RequestParam("fileName") String fileName){
        File file=new File(goodsImgUrl+fileName);
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
     * 上传商品照片
     * */
    @ApiOperation("上传商品照片")
    @PostMapping("/uploadGoodsImg")
    public Result upload(@RequestBody MultipartFile file) throws IOException {
        String fileName=file.getOriginalFilename();
        File uploadFile=new File(goodsImgUrl+fileName);
        file.transferTo(uploadFile);
        Map<String,String> map=new HashMap<>();
        map.put("goodsImg",fileName);
        return Result.success(map);
    }

    /**
     * 添加商品
     * */
    @ApiOperation("添加商品")
    @PostMapping("/addGoods")
    public Result addHeritage(@RequestBody Goods goods){
        if (goodsService.save(goods)){
            return Result.success();
        }
        return Result.fail();
    }

    /**
     * 删除商品
     * */
    @ApiOperation("删除商品")
    @PostMapping("/delGoods")
    public Result delHeritage(@RequestBody Goods goods){
        if (goodsService.removeById(goods)){
            String[] imgList=goods.getGoodsImg().split("/");
            for (String imgName:imgList){
                if (!imgName.equals("")){
                    File file=new File(goodsImgUrl+imgName);
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
     * 修改商品
     * */
    @ApiOperation("修改商品")
    @PostMapping("/updateGoods")
    public Result updateHeritage(@RequestBody Goods goods){
        if (goodsService.updateById(goods)){
            return Result.success();
        }
        return Result.fail();
    }


    /**
     * 分页查询
     * */
    @ApiOperation("商品分页查询")
    @PostMapping("/selectPage")
    public Result selectPage(@RequestBody MyPage myPage){
        Page<Goods> page=new Page<>();
        page.setCurrent(myPage.getPageNum());
        page.setSize(myPage.getPageSize());
        LambdaQueryWrapper<Goods> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        if(myPage.getParam().get("type").equals("商品类型") && !myPage.getParam().get("search").equals("null")) {
            lambdaQueryWrapper.eq(Goods::getGoodsCategory, myPage.getParam().get("search"));
        }
        if(myPage.getParam().get("type").equals("商品名称") && !myPage.getParam().get("search").equals("null")) {
            lambdaQueryWrapper.like(Goods::getGoodsName,myPage.getParam().get("search"));
        }
        IPage<Goods> result=goodsService.page(page,lambdaQueryWrapper);
        if (page.getCurrent() > result.getPages()) {
            page.setCurrent(result.getPages());
            result = goodsService.page(page, lambdaQueryWrapper);
        }
        if(result.getRecords().isEmpty()) {
            return Result.fail();
        }else {
            return Result.success((int) result.getTotal(), result.getRecords());
        }
    }
}
