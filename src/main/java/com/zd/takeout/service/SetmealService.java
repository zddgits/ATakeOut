package com.zd.takeout.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zd.takeout.dto.SetmealDto;
import com.zd.takeout.entity.Setmeal;

import java.util.List;

public interface SetmealService extends IService<Setmeal> {
    public void saveWithDish(SetmealDto setmealDto);
    public void removeWithDish(List<Long> list);
}
