package com.app.mapper.market;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import entity.Orders;
import entity.OrdersDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface OrdersMapper extends BaseMapper<Orders> {

    List<OrdersDTO> selectOrders (@Param("ordersID") String ordersID);
    void deleteOrders(Map<String,Object> map);
    void addOrders(Map<String,Object> map);
}
