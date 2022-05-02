package com.zenghao.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zenghao.common.R;
import com.zenghao.dto.SetmealDto;
import com.zenghao.entity.Category;
import com.zenghao.entity.Setmeal;
import com.zenghao.service.CategoryService;
import com.zenghao.service.SetmealDishService;
import com.zenghao.service.SetmealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 套餐管理
 */
@RestController
@RequestMapping("/setmeal")
@Slf4j
public class SetmealController {

    @Autowired
    private SetmealService setmealService;

    @Autowired
    private SetmealDishService setmealDishService;

    @Autowired
    private CategoryService categoryService;

    /**
     * 新增套餐
     * @param setmealDto
     * @return
     */
    @PostMapping
    public R<String> save(@RequestBody SetmealDto setmealDto){
        log.info("套餐信息:{}",setmealDto);

        setmealService.saveWithDish(setmealDto);
        return R.success("新增套餐成功");
    }

    /**
     * 套餐分页查询
     */

    @GetMapping("/page")
    public R<Page> page(int page,int pageSize , String name){

        Page<Setmeal> pageInfo = new Page<>(page,pageSize);
        Page<SetmealDto> page2 = new Page<>(page,pageSize);

        LambdaQueryWrapper<Setmeal> query = new LambdaQueryWrapper<>();

        query.like(name != null,Setmeal::getName,name);

        query.orderByDesc(Setmeal::getCreateTime);

        setmealService.page(pageInfo, query);

        BeanUtils.copyProperties(pageInfo,page2,"records");

        List<Setmeal> records = pageInfo.getRecords();

        List<SetmealDto> collect = records.stream().map((item) -> {
            SetmealDto setmealDto = new SetmealDto();

            BeanUtils.copyProperties(item, setmealDto);

            Long categoryId = item.getCategoryId();

            Category category = categoryService.getById(categoryId);

            if (category != null) {
                setmealDto.setCategoryName(category.getName());
            }

            return setmealDto;

        }).collect(Collectors.toList());

        page2.setRecords(collect);

        return  R.success(page2);
    }

    /**
     * 删除套餐
     * @param ids
     * @return
     */
    @DeleteMapping
    public R<String> delete(@RequestParam List<Long> ids){

        setmealService.removeWithDish( ids);

        return R.success("套餐数据删除成功!!");
    }

    /*
     * 停售操作
     */

    /*@PostMapping("/status/{setmeal.getStatus()}")
    public R<String> Status(@PathVariable Setmeal setmeal,@RequestParam List<Long> ids){

        setmealService.updateStatus(setmeal,ids);

        return R.success("状态修改成功");
    }*/


    /**
     * 根据条件查询套餐数据
     * @param setmeal
     * @return
     */
    @GetMapping("/list")
    public R<List<Setmeal>> list( Setmeal setmeal){

        LambdaQueryWrapper<Setmeal> query = new LambdaQueryWrapper<>();
        query.eq(setmeal.getCategoryId() != null,Setmeal::getCategoryId,setmeal.getCategoryId());
        query.eq(setmeal.getStatus() != null,Setmeal::getStatus,setmeal.getStatus());
        query.orderByDesc(Setmeal::getUpdateTime);

        List<Setmeal> list = setmealService.list(query);

        return R.success(list);
    }






}
