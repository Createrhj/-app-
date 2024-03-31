package com.app.service.travel.impl;

import com.app.mapper.travel.HotelMapper;
import com.app.service.travel.HotelService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import entity.Hotel;
import org.springframework.stereotype.Service;

@Service
public class HotelServiceImpl extends ServiceImpl<HotelMapper, Hotel> implements HotelService {
}
