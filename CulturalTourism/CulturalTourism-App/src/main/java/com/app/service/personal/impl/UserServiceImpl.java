package com.app.service.personal.impl;

import com.app.mapper.personal.UserMapper;
import com.app.service.personal.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import entity.User;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
