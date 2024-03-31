package com.app.service.market;

import com.baomidou.mybatisplus.extension.service.IService;
import entity.Orders;
import entity.OrdersDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


public interface OrdersService extends IService<Orders> {

    List<OrdersDTO> selectOrders (@Param("ordersID") String ordersID);

    void deleteOrders(Map<String,Object> map);
    void addOrders(Map<String,Object> map);
}
