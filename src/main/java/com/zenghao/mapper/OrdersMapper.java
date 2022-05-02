package com.zenghao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zenghao.entity.Orders;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrdersMapper extends BaseMapper<Orders> {
}
