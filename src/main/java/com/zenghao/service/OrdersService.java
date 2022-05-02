package com.zenghao.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zenghao.entity.Orders;

public interface OrdersService extends IService<Orders> {
    void submit(Orders orders);
}
