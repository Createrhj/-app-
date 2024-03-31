package com.app.service.travel.impl;

import com.app.mapper.travel.MuseumMapper;
import com.app.service.travel.MuseumService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import entity.Museum;
import org.springframework.stereotype.Service;

@Service
public class MuseumServiceImpl extends ServiceImpl<MuseumMapper, Museum> implements MuseumService {
}
