package com.system.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.system.mapper.HotelMapper;
import com.system.service.HotelService;
import entity.Hotel;
import org.springframework.stereotype.Service;

@Service
public class HotelServiceImpl extends ServiceImpl<HotelMapper, Hotel> implements HotelService {
}
