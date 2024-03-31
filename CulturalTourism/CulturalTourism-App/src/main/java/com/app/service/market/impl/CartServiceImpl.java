package com.app.service.market.impl;

import com.app.mapper.market.CartMapper;
import com.app.service.market.CartService;
import entity.CartDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class CartServiceImpl implements CartService {

    @Resource
    public CartMapper cartMapper;

    @Override
    public List<CartDTO> buyGoods(@Param("userID") int userID) {
        return cartMapper.buyGoods(userID);
    }

    @Override
    public List<CartDTO> selectCart(@Param("userID") int userID) {
        return cartMapper.selectCart(userID);
    }


    @Override
    public boolean deleteCart(@Param("userID") int userID,@Param("goodsID") int goodsID) {
        return cartMapper.deleteCart(userID,goodsID);
    }

    @Override
    public void addCart(Map<String,Object> map){
        cartMapper.addCart(map);
    }

    @Override
    public void checkSum(Map<String, Object> map) {
        cartMapper.checkSum(map);
    }
}
