package com.app.mapper.market;

import entity.CartDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;


@Mapper
public interface CartMapper {
    List<CartDTO> buyGoods(@Param("userID") int userID);
    List<CartDTO> selectCart(@Param("userID") int userID);
    boolean deleteCart(@Param("userID") int userID,@Param("goodsID") int goodsID);
    void addCart(Map<String,Object> map);
    void checkSum(Map<String,Object> map);

}
