package net.enjoy.springboot.registrationlogin.service;

import net.enjoy.springboot.registrationlogin.dto.OrderDto;
import net.enjoy.springboot.registrationlogin.dto.OrderDetailDto;
import net.enjoy.springboot.registrationlogin.entity.Order;
import net.enjoy.springboot.registrationlogin.entity.OrderDetail;
import net.enjoy.springboot.registrationlogin.entity.ProductDetail;
import net.enjoy.springboot.registrationlogin.entity.User;
import net.enjoy.springboot.registrationlogin.repository.OrderRepository;
import net.enjoy.springboot.registrationlogin.repository.OrderDetailRepository;
import net.enjoy.springboot.registrationlogin.repository.ProductsDetailRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class OrderServiceImpl implements OrderService {
    UserService userService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private ProductsDetailRespository productDetailRepository;

//    @Override
//    public OrderDto createOrder(OrderDto orderDto) {
//        Order order = new Order();
//        User user = new User();
//        user.setId(orderDto.getUserId());
//        order.setUser(user);
//        order.setOrderDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
//        order.setTotalPrice(orderDto.getTotalPrice());
//        order.setStatus(orderDto.getStatus());
//        order = orderRepository.save(order);
//
//        for (OrderDetailDto detailDto : orderDto.getOrderDetails()) {
//            OrderDetail orderDetail = new OrderDetail();
//            orderDetail.setOrder(order);
//            ProductDetail productDetail = productDetailRepository.findById(detailDto.getProductId()).orElseThrow(() -> new RuntimeException("Product not found"));
//            orderDetail.setProductDetail(productDetail);
//            orderDetail.setQuantity(detailDto.getQuantity());
//            orderDetail.setTotalPrice(detailDto.getTotalPrice());
//            orderDetailRepository.save(orderDetail);
//        }
//
//        orderDto.setId(order.getId());
//        return orderDto;
//    }

    @Override

    public void createOrder(OrderDto orderDto) { Order order = new Order();
        order.setUser(userService.getUserById(orderDto.getUserId()));
        order.setOrderDate(orderDto.getOrderDate());
        order.setTotalPrice(orderDto.getTotalPrice());
        order.setStatus(orderDto.getStatus());
        orderRepository.save(order);
    }

    @Override
    public Order getSqlOrderByOrderId(Long orderId) {
        return orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));
    }
}