package com.zenghao.dto;

import com.zenghao.entity.Setmeal;
import com.zenghao.entity.SetmealDish;
import lombok.Data;
import java.util.List;

@Data
public class SetmealDto extends Setmeal {

    private List<SetmealDish> setmealDishes;

    private String categoryName;
}
