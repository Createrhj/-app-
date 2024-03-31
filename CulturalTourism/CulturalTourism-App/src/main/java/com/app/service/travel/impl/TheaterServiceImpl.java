package com.app.service.travel.impl;

import com.app.mapper.travel.TheaterMapper;
import com.app.service.travel.TheaterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import entity.Theater;
import org.springframework.stereotype.Service;

@Service
public class TheaterServiceImpl extends ServiceImpl<TheaterMapper, Theater> implements TheaterService {
}
