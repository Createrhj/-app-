package com.app.service.market.impl;

import com.app.mapper.market.GoodsMapper;
import com.app.service.market.GoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import entity.Goods;
import org.springframework.stereotype.Service;

@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {
}
