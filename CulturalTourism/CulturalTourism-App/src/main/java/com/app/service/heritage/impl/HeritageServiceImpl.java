package com.app.service.heritage.impl;

import com.app.mapper.hritage.HeritageMapper;
import com.app.service.heritage.HeritageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import entity.Heritage;
import org.springframework.stereotype.Service;

@Service
public class HeritageServiceImpl extends ServiceImpl<HeritageMapper, Heritage> implements HeritageService {
}
