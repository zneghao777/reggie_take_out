package com.zenghao.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zenghao.entity.Category;
import com.zenghao.entity.Employee;
import org.springframework.stereotype.Service;



public interface CategoryService extends IService<Category> {
    public void remove(Long id);
}