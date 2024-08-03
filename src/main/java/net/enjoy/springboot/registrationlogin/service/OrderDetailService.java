package net.enjoy.springboot.registrationlogin.service;

import net.enjoy.springboot.registrationlogin.dto.OrderDetailDto;

public interface OrderDetailService {
    OrderDetailDto createOrderDetail(OrderDetailDto orderDetailDto);
}