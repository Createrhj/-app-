package com.system.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.system.mapper.MuseumMapper;
import com.system.service.MuseumService;
import entity.Museum;
import org.springframework.stereotype.Service;

@Service
public class MuseumServiceImpl extends ServiceImpl<MuseumMapper, Museum> implements MuseumService {
}
