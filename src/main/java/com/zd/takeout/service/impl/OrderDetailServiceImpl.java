package com.zd.takeout.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zd.takeout.entity.OrderDetail;
import com.zd.takeout.mapper.OrderDetailMapper;
import com.zd.takeout.service.OrderDetailService;
import com.zd.takeout.service.OrderService;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements OrderDetailService {
}
