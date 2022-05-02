package com.zenghao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zenghao.entity.OrderDetail;
import com.zenghao.entity.Orders;
import com.zenghao.mapper.OrderDetailMapper;
import com.zenghao.mapper.OrdersMapper;
import com.zenghao.service.OrderDetailService;
import com.zenghao.service.OrdersService;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements OrderDetailService {
}
