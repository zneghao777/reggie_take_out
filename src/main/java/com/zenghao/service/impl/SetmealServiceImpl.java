package com.zenghao.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zenghao.common.CustomException;
import com.zenghao.dto.SetmealDto;
import com.zenghao.entity.Setmeal;
import com.zenghao.entity.SetmealDish;
import com.zenghao.mapper.SetmealMapper;
import com.zenghao.service.SetmealDishService;
import com.zenghao.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SetmealServiceImpl extends ServiceImpl<SetmealMapper, Setmeal> implements SetmealService {

    @Autowired
    private SetmealDishService setmealDishService;

    /**
     * 新增套餐同时需要保存套餐和菜品关联的关系
     * @param setmealDto
     */
    @Override
    @Transactional
    public void saveWithDish(SetmealDto setmealDto) {
        //保存套餐的基本信息, 操作setmeal,执行insert操作
        this.save(setmealDto);

        List<SetmealDish> setmealDishes = setmealDto.getSetmealDishes();
        setmealDishes.stream().map((item) -> {
            item.setSetmealId(setmealDto.getId());
            return item;
        }).collect(Collectors.toList());

        //保存套餐和菜品的关联信息, 操作setmeal_dish, 执行insert操作
        setmealDishService.saveBatch(setmealDishes);
    }

    /**
     * 删除套餐及其关联数据
     */
    @Override
    public void removeWithDish(List<Long> ids) {
        //
        //查询套餐状态,确定是否可以删除
        LambdaQueryWrapper<Setmeal> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(Setmeal::getId,ids);
        queryWrapper.eq(Setmeal::getStatus,1);
        //如果不饿能删除,抛出异常信息
        int count = this.count(queryWrapper);
        if (count > 0){
            throw new CustomException("套餐正在售卖中,不能删除!!");
        }
        //如果可以删除,先删除套餐表中的数据
        this.removeByIds(ids);
        //删除关系表中的数据
        LambdaQueryWrapper<SetmealDish> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.in(SetmealDish::getSetmealId,ids);
        setmealDishService.remove(lambdaQueryWrapper);
    }

   /* @Override
    public void updateStatus(Setmeal setmeal, List<Long> ids) {
            LambdaQueryWrapper<Setmeal> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.in(Setmeal::getId,ids);
            this.update(setmeal,queryWrapper);

    }*/
}
