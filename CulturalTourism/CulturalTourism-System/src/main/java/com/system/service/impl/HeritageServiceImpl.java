package com.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.system.mapper.HeritageMapper;
import com.system.service.HeritageService;
import entity.Heritage;
import org.springframework.stereotype.Service;

@Service
public class HeritageServiceImpl extends ServiceImpl<HeritageMapper, Heritage> implements HeritageService {
}
