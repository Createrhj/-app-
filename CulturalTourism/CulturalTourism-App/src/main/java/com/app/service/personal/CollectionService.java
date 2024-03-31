package com.app.service.personal;

import com.baomidou.mybatisplus.extension.service.IService;
import entity.Collection;
import entity.Heritage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CollectionService extends IService<Collection> {
    public List<Heritage> selectHeritage(@Param("userID") int userID);
}
