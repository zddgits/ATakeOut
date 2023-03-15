package com.zd.takeout.dto;

import com.zd.takeout.entity.Setmeal;
import com.zd.takeout.entity.SetmealDish;
import lombok.Data;
import java.util.List;

@Data
public class SetmealDto extends Setmeal {

    private List<SetmealDish> setmealDishes;

    private String categoryName;
}
