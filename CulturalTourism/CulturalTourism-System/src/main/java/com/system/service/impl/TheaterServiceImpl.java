package com.system.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.system.mapper.TheaterMapper;
import com.system.service.TheaterService;
import entity.Theater;
import org.springframework.stereotype.Service;

@Service
public class TheaterServiceImpl extends ServiceImpl<TheaterMapper, Theater> implements TheaterService {
}
