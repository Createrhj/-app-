package com.app.service.travel.impl;

import com.app.mapper.travel.SceneryMapper;
import com.app.service.travel.SceneryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import entity.Scenery;
import org.springframework.stereotype.Service;

@Service
public class SceneryServiceImpl extends ServiceImpl<SceneryMapper, Scenery> implements SceneryService {
}
