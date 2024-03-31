package com.app.controller;

import base.Result;
import base.SnowFlake;
import com.app.service.market.CartService;
import com.app.service.market.GoodsService;
import com.app.service.market.OrdersService;
import com.app.service.market.UserAddressService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import entity.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/mark")
@Api(tags = "购物相关Api接口")
public class MarketController {

    @Resource
    public UserAddressService userAddressService;

    @Resource
    public OrdersService ordersService;

    @Resource
    public CartService cartService;

    @Resource
    public GoodsService goodsService;

    /**
     * 查看商品详情功能
     */
    @GetMapping("/goodsDetail")
    @ApiOperation("查看商品详情功能")
    public Result goodsDetail(@RequestParam("goodsID") int goodsID){
        LambdaQueryWrapper<Goods> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Goods::getGoodsID,goodsID);
        if (!goodsService.list(lambdaQueryWrapper).isEmpty()){
            return Result.success(goodsService.list(lambdaQueryWrapper));
        }
        return Result.fail();
    }

    /**
     * 查询符合条件的所有商品
     * */
    @ApiOperation("查询符合条件的所有商品")
    @PostMapping("/allGoods")
    public Result allGoods (@RequestBody Map<Object,Object> searchMap){

        String goodsName= (String) searchMap.get("goodsName");
        String goodsCategory=(String) searchMap.get("goodsCategory");
        String goodsLocation=(String) searchMap.get("goodsLocation");
        String lowPrice=(String) searchMap.get("lowPrice");
        String highPrice=(String) searchMap.get("highPrice");

        LambdaQueryWrapper<Goods> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        if (!goodsName.equals("")){
            lambdaQueryWrapper.like(Goods::getGoodsName,goodsName);
        }
        if (!goodsCategory.equals("")){
            lambdaQueryWrapper.eq(Goods::getGoodsCategory,goodsCategory);
        }
        if (!goodsLocation.equals("")){
            lambdaQueryWrapper.eq(Goods::getGoodsLocation,goodsLocation);
        }
        if (!lowPrice.equals("")&&!highPrice.equals("")){
            lambdaQueryWrapper.between(Goods::getGoodsPrice,lowPrice,highPrice);
        }
        List<Goods> goodsList=goodsService.list(lambdaQueryWrapper);
        if (!goodsList.isEmpty()){
            return Result.success(goodsList);
        }
        return Result.fail();
    }

    /**
     * 查询购物车
     * */
    @ApiOperation("查询购物车功能")
    @GetMapping("/selectCart")
    public Result selectCart(@RequestParam("userID") int userID){
        if (cartService.selectCart(userID).isEmpty()){
            return Result.fail();
        }else {
            return Result.success(cartService.selectCart(userID));
        }
    }

    /**
     * 删除购物车
     * */
    @ApiOperation("删除购物车功能")
    @GetMapping("/deleteCart")
    public Result deleteCart(@RequestParam("userID") int userID,@RequestParam("goodsID") int goodsID){
        if (cartService.deleteCart(userID,goodsID)){
            return Result.success();
        }else {
            return Result.fail();
        }
    }

    /**
     * 购物车商品变动
     * */
    @ApiOperation("购物车商品变动")
    @GetMapping("/addCart")
    public Result addCart(@RequestParam("userID") int userID,@RequestParam("goodsID") int goodsID,@RequestParam("goodsNumber") int goodsNumber){
        Map<String,Object> map =new HashMap<>();
        map.put("userID",userID);
        map.put("goodsID",goodsID);
        map.put("goodsNumber",goodsNumber);
        cartService.addCart(map);
        if (map.get("result").equals(0)){
            return Result.success();
        }else {
            return Result.fail();
        }
    }

    /**
     * 选定商品并计算总价
     * */
    @ApiOperation("选定商品并计算总价")
    @GetMapping("/checkSum")
    public Result checkSum(@RequestParam("userID") int userID,@RequestParam("goodsID") int goodsID,@RequestParam("isCheck") int isCheck){
        Map<String,Object> map =new HashMap<>();
        map.put("userID",userID);
        map.put("goodsID",goodsID);
        map.put("isCheck",isCheck);
        cartService.checkSum(map);
        if (map.get("result").equals(0)){
            return Result.success(map.get("total"));
        }else {
            return Result.fail();
        }
    }

    /**
     * 查看用户所有的订单
     * */
    @ApiOperation("看用户所有的订单功能")
    @GetMapping("/userOrders")
    public Result userOrders(@RequestParam("userID") int userID){
        LambdaQueryWrapper<Orders> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Orders::getUserID,userID);
        List<Orders> orders=ordersService.list(lambdaQueryWrapper);
        if (!orders.isEmpty()){
            return Result.success(orders);
        }
        return Result.fail();
    }

    /**
     * 查看订单详情
     * */
    @ApiOperation("查看订单详情功能")
    @GetMapping("/selectOrders")
    public Result selectOrders(@RequestParam("ordersID") String ordersID){
        System.out.println(ordersID);
        List<OrdersDTO> ordersDTOList=ordersService.selectOrders(ordersID);
        if (!ordersDTOList.isEmpty()){
            return Result.success(ordersDTOList);
        }
        return Result.fail();
    }

    /**
     * 展示购买的商品
     * */
    @ApiOperation("展示购买的商品")
    @GetMapping("/buyGoods")
    public Result buyGoods(@RequestParam("userID") int userID){
        if (cartService.buyGoods(userID).isEmpty()){
            return Result.fail();
        }else {
            return Result.success(cartService.buyGoods(userID));
        }
    }

    /**
     * 生成订单
     * */
    @ApiOperation("添加订单功能")
    @PostMapping("/addOrders")
    public Result addOrders(@RequestBody Orders orders){
        SnowFlake snowFlake=new SnowFlake(1,2);
        LocalDateTime now = LocalDateTime.now();
        orders.setOrdersID(String.valueOf(snowFlake.nextId())).setOrdersDate(String.valueOf(now)).setOrdersStatue("待发货");
        if (ordersService.save(orders)){
            Map<String,Object> map =new HashMap<>();
            map.put("userID",orders.getUserID());
            map.put("ordersID",orders.getOrdersID());
            ordersService.addOrders(map);
            return Result.success(orders);
        }else {
            return Result.fail();
        }
    }

    /**
     * 删除订单
     * */
    @ApiOperation("删除订单功能")
    @GetMapping("/delOrders")
    public Result delOrders(@RequestParam("userID") int userID,@RequestParam("ordersID") String ordersID){
        Map<String,Object> map=new HashMap<>();
        map.put("userID",userID);
        map.put("ordersID",ordersID);
        ordersService.deleteOrders(map);
        if (map.get("result").equals(0)){
            return Result.success(map);
        }else {
            return Result.fail();
        }
    }


    /**
     * 查询某个地址
     * */
    @ApiOperation("查询某个地址")
    @GetMapping("/getAddress")
    public Result getAddress(@RequestParam("addressID") int addressID){
        LambdaQueryWrapper<UserAddress> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(UserAddress::getAddressID,addressID);
        List<UserAddress> userAddresses=userAddressService.list(lambdaQueryWrapper);
        if (!userAddresses.isEmpty()){
            return Result.success(userAddresses.get(0));
        }
        return Result.fail();
    }

    /**
     * 查询默认地址
     * */
    @ApiOperation("查询默认收货地址")
    @GetMapping("/defaultAddress")
    public Result defaultAddress(@RequestParam("userID") int userID){
        LambdaQueryWrapper<UserAddress> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(UserAddress::getUserID,userID).eq(UserAddress::getWeight,1);
        List<UserAddress> userAddresses=userAddressService.list(lambdaQueryWrapper);
        if (!userAddresses.isEmpty()){
            return Result.success(userAddresses.get(0));
        }
        return Result.fail();
    }

    /**
     * 查看收货地址
     * */
    @ApiOperation("查看收货地址")
    @GetMapping("/showAddress")
    public Result showAddress(@RequestParam("userID") int userID){
        System.out.println("userID:"+userID);
        LambdaQueryWrapper<UserAddress> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(UserAddress::getUserID,userID).orderByDesc(UserAddress::getWeight);
        List<UserAddress> userAddresses=userAddressService.list(lambdaQueryWrapper);
        if (!userAddresses.isEmpty()){
            return Result.success(userAddresses);
        }
        return Result.fail();
    }

    /**
     * 添加收货地址
     * */
    @ApiOperation("添加收货地址功能")
    @PostMapping("/addAddress")
    public Result addAddress(@RequestBody UserAddress userAddress){

        changeWeight(userAddress);

        if (userAddressService.save(userAddress)){
            return Result.success();
        }
        return Result.fail();
    }

    /**
     * 删除收货地址
     * */
    @ApiOperation("删除收货地址功能")
    @PostMapping("/delAddress")
    public Result delAddress(@RequestBody UserAddress userAddress){
        if (userAddressService.removeById(userAddress.getAddressID())){
            return Result.success();
        }
        return Result.fail();
    }

    /**
     * 修改收货地址
     * */
    @ApiOperation("修改收货地址功能")
    @PostMapping("/updateAddress")
    public Result updateAddress(@RequestBody UserAddress userAddress){
        changeWeight(userAddress);
        System.out.println(userAddress);
        if (userAddressService.updateById(userAddress)){
            return Result.success();
        }
        return Result.fail();
    }

    private void changeWeight(@RequestBody UserAddress userAddress) {
        if (userAddress.getWeight()==1){
            LambdaQueryWrapper<UserAddress> lambdaQueryWrapper=new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(UserAddress::getUserID,userAddress.getUserID());
            List<UserAddress> userAddresses=userAddressService.list(lambdaQueryWrapper);
            for (UserAddress oldUserAddress : userAddresses){
                if (oldUserAddress.getWeight()==1){
                    LambdaUpdateWrapper<UserAddress> lambdaUpdateWrapper=new LambdaUpdateWrapper<>();
                    lambdaUpdateWrapper.eq(UserAddress::getAddressID,oldUserAddress.getAddressID())
                            .set(UserAddress::getWeight,0);
                    userAddressService.update(lambdaUpdateWrapper);
                }
            }
        }
    }

}
