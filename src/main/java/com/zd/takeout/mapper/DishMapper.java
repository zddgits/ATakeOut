package com.zd.takeout.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zd.takeout.entity.Dish;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DishMapper extends BaseMapper<Dish> {
}
