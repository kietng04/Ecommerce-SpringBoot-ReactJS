package net.enjoy.springboot.registrationlogin.service;

import net.enjoy.springboot.registrationlogin.dto.OrderDto;
import net.enjoy.springboot.registrationlogin.entity.Order;

public interface OrderService {
    void createOrder(OrderDto orderDto);


    //Ham trả về ordersql theo id
    Order getSqlOrderByOrderId(Long orderId);

}