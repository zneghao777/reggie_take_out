package com.zenghao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zenghao.entity.ShoppingCart;
import com.zenghao.mapper.ShoppingCartMapper;
import com.zenghao.service.ShoppingCartService;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartMapper, ShoppingCart> implements ShoppingCartService {
}
