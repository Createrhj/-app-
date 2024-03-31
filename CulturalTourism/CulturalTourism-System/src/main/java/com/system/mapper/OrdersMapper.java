package com.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import entity.Orders;
import entity.OrdersDTO;
import entity.OrdersDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface OrdersMapper extends BaseMapper<Orders> {
    List<OrdersDetail> selectOrders (@Param("ordersID") Long ordersID);
    void deleteOrders(Map<String,Object> map);
}
