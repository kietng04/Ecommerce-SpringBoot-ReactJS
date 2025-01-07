package net.enjoy.springboot.registrationlogin.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.security.access.method.P;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;

import net.enjoy.springboot.registrationlogin.dto.OrderDto;
// import user entity
import net.enjoy.springboot.registrationlogin.entity.User;
// user repo
import net.enjoy.springboot.registrationlogin.repository.UserRepository;
import net.enjoy.springboot.registrationlogin.service.OrdersService;
import net.enjoy.springboot.registrationlogin.service.UserService;

import org.springframework.stereotype.Controller;
// order entity
import net.enjoy.springboot.registrationlogin.entity.Order;
import net.enjoy.springboot.registrationlogin.entity.OrderDetails;
import net.enjoy.springboot.registrationlogin.entity.ProductDetail;
import net.enjoy.springboot.registrationlogin.repository.OrderDetailsRepository;
import net.enjoy.springboot.registrationlogin.repository.OrderRepository;
import net.enjoy.springboot.registrationlogin.repository.ProductsDetailRespository;

import org.springframework.beans.factory.annotation.Autowired;
import net.enjoy.springboot.registrationlogin.config.Cart;
import net.enjoy.springboot.registrationlogin.dto.CartItemDto;
import net.enjoy.springboot.registrationlogin.dto.OrderDetaildto;

@Controller
public class OrdersController {
    UserRepository userRepository;
    OrderRepository orderRepository;
    OrderDetailsRepository orderDetailsRepository;
    ProductsDetailRespository productsDetailRespository;
    OrdersService ordersService;
    UserService userService;

    @Autowired
    Cart cart;

    @Autowired
    public OrdersController(UserRepository userRepository, OrderRepository orderRepository, OrderDetailsRepository orderDetailsRepository,
     ProductsDetailRespository productsDetailRespository, OrdersService ordersService, UserService userService) {
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
        this.orderDetailsRepository = orderDetailsRepository;
        this.productsDetailRespository = productsDetailRespository;
        this.ordersService = ordersService;
        this.userService = userService;
    }

    @PostMapping("/order/add")
    @ResponseBody   
    public String order(double totalPrice) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByEmail(authentication.getName());
        Order order = new Order();
        order.setUser(user);
        order.setOrderTime(new Date(System.currentTimeMillis()));
        order.setTotalPrice(totalPrice);
        orderRepository.save(order);

        // add order detail
        // get new order id
        Long orderId = order.getId();
        for (CartItemDto cartItemDto : cart.getItems()) {
            OrderDetails orderDetail = new OrderDetails();
            ProductDetail productDetail = productsDetailRespository.findById(cartItemDto.getIdDetail()).get();
            orderDetail.setOrder(order);
            orderDetail.setProductDetail(productDetail);
            orderDetail.setQuantity(cartItemDto.getQuantity());
            orderDetailsRepository.save(orderDetail);
        }

        // clear cart
        cart.clear();
        return "oke la";
    }

    @GetMapping("/order-detail/{id}")
    public String orderDetail(@PathVariable Long id, Model model) {
        List<OrderDetails> orderDetails = ordersService.getOrdersDetailsByOrderId(id);
        model.addAttribute("orderDetails", orderDetails);
        Long userid = printLoggedInUserId();
        User user = userService.getUser(userid);
        model.addAttribute("user", user);
        Order order = ordersService.getOrderById(id);
        model.addAttribute("order", order);
        return "order-detail";
    }
    
    public Long printLoggedInUserId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Authentication authentication = new UsernamePasswordAuthenticationToken(auth.getPrincipal(), auth.getCredentials(), auth.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails) {
                String username = ((UserDetails) principal).getUsername();
                User user = userService.findUserByEmail(username);
                if (user != null) {
                    System.out.println("ID USER ĐÃ ĐĂNG NHẬP: " +user.getId());
                    return user.getId();
                }
            }
        }
        return null;
    }
}
