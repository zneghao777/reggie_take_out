package com.zenghao.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zenghao.dto.SetmealDto;
import com.zenghao.entity.Setmeal;

import java.util.List;


public interface SetmealService extends IService<Setmeal> {

    /**
     * 新增套餐同时需要保存套餐和菜品关联的关系
     * @param setmealDto
     */
    public void saveWithDish(SetmealDto setmealDto);

    void removeWithDish(List<Long> ids);

    SetmealDto getByIdwithDish(Long id);

    /* void updateStatus(Setmeal setmeal, List<Long> ids);*/
}