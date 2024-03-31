package com.app.service.market.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.app.mapper.market.OrdersMapper;
import com.app.service.market.OrdersService;
import entity.Orders;
import entity.OrdersDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements OrdersService {

    @Resource
    public OrdersMapper ordersMapper;

    public List<OrdersDTO> selectOrders (@Param("ordersID") String ordersID){
        return ordersMapper.selectOrders(ordersID);
    }

    @Override
    public void deleteOrders(Map<String, Object> map) {
        ordersMapper.deleteOrders(map);
    }

    @Override
    public void addOrders(Map<String, Object> map) {
        ordersMapper.addOrders(map);
    }

}
