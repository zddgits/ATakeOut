package com.zd.takeout.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zd.takeout.entity.Category;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CategoryMapper extends BaseMapper<Category> {
}
