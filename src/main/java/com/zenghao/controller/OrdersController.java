package com.zenghao.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zenghao.common.R;
import com.zenghao.entity.Orders;
import com.zenghao.service.OrdersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@Slf4j
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    @PostMapping("/submit")
    public R<String> submit(@RequestBody Orders orders){
        log.info("订单数据:{}",orders);
        ordersService.submit(orders);
        return R.success("下单成功");
    }

    @GetMapping("/page")
    public R<Page> page(int page , int pageSize) {

        Page<Orders> pageInfo =  new Page<>(page,pageSize);

        LambdaQueryWrapper<Orders> query = new LambdaQueryWrapper<>();

        query.orderByDesc(Orders::getCheckoutTime);

        ordersService.page(pageInfo,query);

        return R.success(pageInfo);
    }
    @PutMapping
    public R<String> ToSend(@RequestBody Orders orders){
        Orders orders1 = ordersService.getById(orders);
        orders1.setStatus(3);
        ordersService.updateById(orders1);
        return R.success("派送成功！！");
    }

}
