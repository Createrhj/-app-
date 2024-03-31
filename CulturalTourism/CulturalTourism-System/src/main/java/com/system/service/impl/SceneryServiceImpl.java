package com.system.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.system.mapper.SceneryMapper;
import com.system.service.SceneryService;
import entity.Scenery;
import org.springframework.stereotype.Service;

@Service
public class SceneryServiceImpl extends ServiceImpl<SceneryMapper, Scenery> implements SceneryService {
}
