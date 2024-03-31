package com.system.controller;

import base.Result;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.system.config.MyPage;
import com.system.service.OrdersService;
import entity.Orders;
import entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/ordersSys")
@Api(tags = "订单相关接口")
public class OrdersController {

    @Resource
    public OrdersService ordersService;

    /**
     * 查看订单详情
     * */
    @ApiOperation("查看订单详情")
    @GetMapping("/selectOrders")
    public Result selectOrders(@RequestParam("ordersID") Long ordersID){
        System.out.println(ordersID);
        System.out.println(ordersService.selectOrders(ordersID));
        return Result.success(ordersService.selectOrders(ordersID));
    }

    /**
     * 删除订单
     * */
    @ApiOperation("删除订单")
    @GetMapping("/delOrders")
    public Result delOrders(@RequestParam("ordersID") Long ordersID){
        Map<String,Object> map=new HashMap<>();
        map.put("ordersID",ordersID);
        ordersService.deleteOrders(map);
        if (map.get("result").equals(0)){
            return Result.success(map);
        }else {
            return Result.fail();
        }
    }

    /**
     * 改变订单状态
     * */
    @ApiOperation("改变订单状态")
    @GetMapping("/changeStatue")
    public Result changeStatue(@RequestParam("ordersID") Long ordersID,@RequestParam("ordersStatue") String ordersStatue){
        LambdaUpdateWrapper<Orders> lambdaUpdateWrapper=new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper.eq(Orders::getOrdersID,ordersID).set(Orders::getOrdersStatue,ordersStatue);
        if (ordersService.update(lambdaUpdateWrapper)){
            return Result.success();
        }
        return Result.fail();
    }

    /**
     * 分页查询
     * */
    @ApiOperation("订单分页查询")
    @PostMapping("/selectPage")
    public Result selectPage(@RequestBody MyPage myPage){
        Page<Orders> page=new Page<>();
        page.setCurrent(myPage.getPageNum());
        page.setSize(myPage.getPageSize());

        LambdaQueryWrapper<Orders> lambdaQueryWrapper=new LambdaQueryWrapper<>();

        if (myPage.getParam().get("type").equals("订单编号") && !myPage.getParam().get("search").equals("null")){
            lambdaQueryWrapper.eq(Orders::getOrdersID, myPage.getParam().get("search"));
        }

        if (myPage.getParam().get("type").equals("订单状态") && !myPage.getParam().get("search").equals("null")){
            lambdaQueryWrapper.eq(Orders::getOrdersStatue, myPage.getParam().get("search"));
        }

        IPage<Orders> result=ordersService.page(page,lambdaQueryWrapper);
        if (page.getCurrent() > result.getPages()) {
            page.setCurrent(result.getPages());
            result = ordersService.page(page, lambdaQueryWrapper);
        }

        if(result.getRecords().isEmpty()) {
            return Result.fail();
        }else {
            return Result.success((int) result.getTotal(), result.getRecords());
        }
    }


}
