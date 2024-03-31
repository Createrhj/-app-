package com.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import entity.Orders;
import entity.OrdersDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface OrdersService extends IService<Orders> {
    List<OrdersDetail> selectOrders (@Param("ordersID") Long ordersID);
    void deleteOrders(Map<String,Object> map);
}
