package com.zd.takeout.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zd.takeout.common.CustomException;
import com.zd.takeout.entity.Category;
import com.zd.takeout.entity.Dish;
import com.zd.takeout.entity.Setmeal;
import com.zd.takeout.mapper.CategoryMapper;
import com.zd.takeout.service.CategoryService;
import com.zd.takeout.service.DishService;
import com.zd.takeout.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    DishService dishService;
    @Autowired
    SetmealService setMealService;
    /**
     * 根据菜品分类id删除菜品分类，入股哟当前菜品分类下无菜品或者套餐这可以删除
     * @param id 菜品分类id
     */
    @Override
    public void remove(Long id) {
        LambdaQueryWrapper<Dish> dishQueryWrapper = new LambdaQueryWrapper<>();
        dishQueryWrapper.eq(Dish::getCategoryId,id);

        int count1 = dishService.count(dishQueryWrapper);
        if(count1 > 0) {
            throw new CustomException("当前分类下存在菜品");
        }

        LambdaQueryWrapper<Setmeal> setMealQueryWrapper = new LambdaQueryWrapper<>();
        setMealQueryWrapper.eq(Setmeal::getCategoryId,id);

        int count2 = setMealService.count(setMealQueryWrapper);
        if(count2 > 0) {
            throw new CustomException("当前分类下存在套餐");
        }
        super.removeById(id);
    }
}
