package com.zenghao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zenghao.entity.User;
import com.zenghao.mapper.UserMapper;
import com.zenghao.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl  extends ServiceImpl<UserMapper, User> implements UserService{

}
