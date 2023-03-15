package com.zd.takeout.dto;

import com.zd.takeout.entity.OrderDetail;
import com.zd.takeout.entity.Orders;
import lombok.Data;
import java.util.List;

@Data
public class OrdersDto extends Orders {

    private String userName;

    private String phone;

    private String address;

    private String consignee;

    private List<OrderDetail> orderDetails;
	
}
