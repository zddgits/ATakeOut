package com.zd.takeout.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zd.takeout.dto.DishDto;
import com.zd.takeout.entity.Dish;

public interface DishService extends IService<Dish> {
    //新增裁菜品与口味
    public void saveWithFlavor(DishDto dishDto);
    public DishDto getByIdWithFlavor(Long id);
    public void updateWithFlavor(DishDto dishDto);
}
