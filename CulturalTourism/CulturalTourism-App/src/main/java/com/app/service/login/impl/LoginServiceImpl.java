package com.app.service.login.impl;

import com.app.mapper.login.LoginMapper;
import com.app.service.login.LoginService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import entity.User;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl extends ServiceImpl<LoginMapper, User> implements LoginService {
}
