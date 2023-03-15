package com.zd.takeout.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zd.takeout.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
