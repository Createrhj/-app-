package com.app.mapper.personal;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import entity.Heritage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CollectionMapper extends BaseMapper<entity.Collection> {
    public List<Heritage> selectHeritage(@Param("userID") int userID);
}
