package net.enjoy.springboot.registrationlogin.service;

import net.enjoy.springboot.registrationlogin.dto.DetailCartItemDto;
import net.enjoy.springboot.registrationlogin.entity.User;
import net.enjoy.springboot.registrationlogin.repository.OrderDetailsRepository;
import net.enjoy.springboot.registrationlogin.repository.OrderRepository;
import net.enjoy.springboot.registrationlogin.entity.Order;
import net.enjoy.springboot.registrationlogin.entity.OrderDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersServiceImpl implements OrdersService {
    private final OrderRepository orderRepository;
    private final OrderDetailsRepository orderDetailsRepository;

    @Autowired
    public OrdersServiceImpl(OrderRepository orderRepository, OrderDetailsRepository orderDetailsRepository) {
        this.orderRepository = orderRepository;
        this.orderDetailsRepository = orderDetailsRepository;
    }

    @Override
    public void saveOrder(User user, List<DetailCartItemDto> detailCartItemDtos) {
        double totalPrice = 0;
        for (DetailCartItemDto detailCartItemDto : detailCartItemDtos) {
            totalPrice += detailCartItemDto.getQuantity() * detailCartItemDto.getProductDetail().getPrice();
        }
        Order order = new Order();
        order.setUser(user);
        order.setTotalPrice(totalPrice);
        orderRepository.save(order);
    }

    @Override
    public List<Order> getOrdersByUserId(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    @Override
    public List<OrderDetails> getOrdersDetailsByOrderId(Long orderId) {
        return orderDetailsRepository.findByOrderId(orderId);
    }

    @Override
    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId).orElse(null);
    }
}