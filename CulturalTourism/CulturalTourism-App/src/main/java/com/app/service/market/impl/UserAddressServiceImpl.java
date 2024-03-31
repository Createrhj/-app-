package com.app.service.market.impl;


import com.app.mapper.market.UserAddressMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.app.service.market.UserAddressService;
import entity.UserAddress;
import org.springframework.stereotype.Service;

@Service
public class UserAddressServiceImpl extends ServiceImpl<UserAddressMapper, UserAddress> implements UserAddressService {
}
