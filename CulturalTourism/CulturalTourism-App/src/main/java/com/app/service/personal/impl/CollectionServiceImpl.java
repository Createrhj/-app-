package com.app.service.personal.impl;

import com.app.mapper.personal.CollectionMapper;
import com.app.service.personal.CollectionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import entity.Collection;
import entity.Heritage;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CollectionServiceImpl extends ServiceImpl<CollectionMapper, Collection> implements CollectionService {

    @Resource
    public CollectionMapper collectionMapper;
    @Override
    public List<Heritage> selectHeritage(@Param("userID") int userID) {
        return collectionMapper.selectHeritage(userID);
    }
}
