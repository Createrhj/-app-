package com.app.controller;

import base.Result;
import com.app.service.travel.HotelService;
import com.app.service.travel.MuseumService;
import com.app.service.travel.SceneryService;
import com.app.service.travel.TheaterService;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import entity.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/travel")
@Api(tags = "旅游相关Api接口")
public class TravelController {

    @Resource
    public HotelService hotelService;
    @Resource
    public SceneryService sceneryService;
    @Resource
    public MuseumService museumService;
    @Resource
    public TheaterService theaterService;


    /**
     * 查看酒店详情功能
     */
    @GetMapping("/hotelDetail")
    @ApiOperation("查看酒店详情功能")
    public Result hotelDetail(@RequestParam("hotelID") int hotelID){
        LambdaQueryWrapper<Hotel> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Hotel::getHotelID,hotelID);
        if (!hotelService.list(lambdaQueryWrapper).isEmpty()){
            return Result.success(hotelService.list(lambdaQueryWrapper));
        }
        return Result.fail();
    }

    /**
     * 查询符合条件的所有酒店
     * */
    @ApiOperation("查询符合条件的所有酒店")
    @PostMapping("/allHotel")
    public Result allHotel (@RequestBody Map<Object,Object> searchMap){

        String hotelName= (String) searchMap.get("hotelName");
        String hotelCategory=(String) searchMap.get("hotelCategory");
        String hotelCity=(String) searchMap.get("hotelCity");
        String lowPrice=(String) searchMap.get("lowPrice");
        String highPrice=(String) searchMap.get("highPrice");

        LambdaQueryWrapper<Hotel> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        if (!hotelName.equals("")){
            lambdaQueryWrapper.like(Hotel::getHotelName,hotelName);
        }
        if (!hotelCategory.equals("")){
            lambdaQueryWrapper.eq(Hotel::getHotelCategory,hotelCategory);
        }
        if (!hotelCity.equals("")){
            lambdaQueryWrapper.eq(Hotel::getHotelCity,hotelCity);
        }
        if (!lowPrice.equals("")&&!highPrice.equals("")){
            lambdaQueryWrapper.between(Hotel::getHotelPrice,lowPrice,highPrice);
        }
        List<Hotel> hotels=hotelService.list(lambdaQueryWrapper);
        if (!hotels.isEmpty()){
            return Result.success(hotels);
        }
        return Result.fail();
    }

    /**
     * 查看景点详情功能
     */
    @GetMapping("/sceneryDetail")
    @ApiOperation("查看景点详情功能")
    public Result sceneryDetail(@RequestParam("sceneryID") int sceneryID){
        LambdaQueryWrapper<Scenery> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Scenery::getSceneryID,sceneryID);
        if (!sceneryService.list(lambdaQueryWrapper).isEmpty()){
            return Result.success(sceneryService.list(lambdaQueryWrapper));
        }
        return Result.fail();
    }

    /**
     * 查询符合条件的所有景点
     * */
    @ApiOperation("查询符合条件的所有景点")
    @PostMapping("/allScenery")
    public Result allScenery (@RequestBody Map<Object,Object> searchMap){

        String sceneryName= (String) searchMap.get("sceneryName");
        String sceneryCategory=(String) searchMap.get("sceneryCategory");
        String sceneryCity=(String) searchMap.get("sceneryCity");
        String lowPrice=(String) searchMap.get("lowPrice");
        String highPrice=(String) searchMap.get("highPrice");

        LambdaQueryWrapper<Scenery> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        if (!sceneryName.equals("")){
            lambdaQueryWrapper.like(Scenery::getSceneryName,sceneryName);
        }
        if (!sceneryCategory.equals("")){
            lambdaQueryWrapper.eq(Scenery::getSceneryCategory,sceneryCategory);
        }
        if (!sceneryCity.equals("")){
            lambdaQueryWrapper.eq(Scenery::getSceneryCity,sceneryCity);
        }
        if (!lowPrice.equals("")&&!highPrice.equals("")){
            lambdaQueryWrapper.between(Scenery::getSceneryPrice,lowPrice,highPrice);
        }
        List<Scenery> sceneries=sceneryService.list(lambdaQueryWrapper);
        if (!sceneries.isEmpty()){
            return Result.success(sceneries);
        }
        return Result.fail();
    }

    /**
     * 查看剧场详情功能
     */
    @GetMapping("/theaterDetail")
    @ApiOperation("查看剧场详情功能")
    public Result theaterDetail(@RequestParam("theaterID") int theaterID){
        LambdaQueryWrapper<Theater> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Theater::getTheaterID,theaterID);
        if (!theaterService.list(lambdaQueryWrapper).isEmpty()){
            return Result.success(theaterService.list(lambdaQueryWrapper));
        }
        return Result.fail();
    }
    /**
     * 查询符合条件的所有剧场
     * */
    @ApiOperation("查询符合条件的所有剧场")
    @PostMapping("/allTheater")
    public Result allTheater (@RequestBody Map<Object,Object> searchMap){

        String theaterName= (String) searchMap.get("theaterName");
        String theaterCategory=(String) searchMap.get("theaterCategory");
        String theaterCity=(String) searchMap.get("theaterCity");
        String lowPrice=(String) searchMap.get("lowPrice");
        String highPrice=(String) searchMap.get("highPrice");

        LambdaQueryWrapper<Theater> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        if (!theaterName.equals("")){
            lambdaQueryWrapper.like(Theater::getTheaterName,theaterName);
        }
        if (!theaterCategory.equals("")){
            lambdaQueryWrapper.eq(Theater::getTheaterCategory,theaterCategory);
        }
        if (!theaterCity.equals("")){
            lambdaQueryWrapper.eq(Theater::getTheaterCity,theaterCity);
        }
        if (!lowPrice.equals("")&&!highPrice.equals("")){
            lambdaQueryWrapper.between(Theater::getTheaterPrice,lowPrice,highPrice);
        }
        List<Theater> theaters=theaterService.list(lambdaQueryWrapper);
        if (!theaters.isEmpty()){
            return Result.success(theaters);
        }
        return Result.fail();
    }

    /**
     * 查看博物馆详情功能
     */
    @GetMapping("/museumDetail")
    @ApiOperation("查看博物馆详情功能")
    public Result museumDetail(@RequestParam("museumID") int museumID){
        LambdaQueryWrapper<Museum> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Museum::getMuseumID,museumID);
        if (!museumService.list(lambdaQueryWrapper).isEmpty()){
            return Result.success(museumService.list(lambdaQueryWrapper));
        }
        return Result.fail();
    }

    /**
     * 查询符合条件的所有博物馆
     * */
    @ApiOperation("查询符合条件的博物馆")
    @PostMapping("/allMuseum")
    public Result allMuseum (@RequestBody Map<Object,Object> searchMap){

        String museumName= (String) searchMap.get("museumName");
        String museumCategory=(String) searchMap.get("museumCategory");
        String museumCity=(String) searchMap.get("museumCity");

        LambdaQueryWrapper<Museum> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        if (!museumName.equals("")){
            lambdaQueryWrapper.like(Museum::getMuseumName,museumName);
        }
        if (!museumCategory.equals("")){
            lambdaQueryWrapper.eq(Museum::getMuseumCategory,museumCategory);
        }
        if (!museumCity.equals("")){
            lambdaQueryWrapper.eq(Museum::getMuseumCity,museumCity);
        }

        List<Museum> museums=museumService.list(lambdaQueryWrapper);
        if (!museums.isEmpty()){
            return Result.success(museums);
        }
        return Result.fail();
    }
}
