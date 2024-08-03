package net.enjoy.springboot.registrationlogin.service;

import net.enjoy.springboot.registrationlogin.dto.OrderDetailDto;
import net.enjoy.springboot.registrationlogin.entity.OrderDetail;
import net.enjoy.springboot.registrationlogin.repository.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Override
    public OrderDetailDto createOrderDetail(OrderDetailDto orderDetailDto) {
        return null;
    }
}