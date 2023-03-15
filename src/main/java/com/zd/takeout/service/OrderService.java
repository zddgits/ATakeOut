package com.zd.takeout.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zd.takeout.entity.Orders;

public interface OrderService extends IService<Orders> {

    public void submit(Orders orders);
}
