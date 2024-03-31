package com.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.system.mapper.OrdersMapper;
import com.system.service.OrdersService;
import entity.Orders;
import entity.OrdersDTO;
import entity.OrdersDetail;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements OrdersService {

    @Resource
    public OrdersMapper ordersMapper;

    @Override
    public List<OrdersDetail> selectOrders(@Param("ordersID") Long ordersID) {
        return ordersMapper.selectOrders(ordersID);
    }

    @Override
    public void deleteOrders(Map<String, Object> map) {
        ordersMapper.deleteOrders(map);
    }
}
