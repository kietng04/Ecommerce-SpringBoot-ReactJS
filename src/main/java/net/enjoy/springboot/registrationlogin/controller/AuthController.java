package net.enjoy.springboot.registrationlogin.controller;

import jakarta.validation.Valid;
import net.enjoy.springboot.registrationlogin.dto.OrderDetailDto;
import net.enjoy.springboot.registrationlogin.dto.OrderDto;
import net.enjoy.springboot.registrationlogin.dto.UserDto;
import net.enjoy.springboot.registrationlogin.entity.User;
import net.enjoy.springboot.registrationlogin.service.OrderService;
import net.enjoy.springboot.registrationlogin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class AuthController {
    private UserService userService;
    private OrderService orderService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    // handler method to handle home page request


    @GetMapping("/blog")
    public String blog() {
        return "blog";
    }
    @GetMapping("/about")
    public String about() {
        return "about";
    }
    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }
    // để chuyển hướng đến trang chủ, lấy từ index.html thành map ur

    @GetMapping("/order")
    public String order() {
        return "order";
    }

    @GetMapping("/order-detail")
    public String orderDetail() {
        return "order-detail";
    }



    // handler method to handle user registration form request
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        // create model object to store form data
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "register";
    }

    // handler method to handle user registration form submit request
    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserDto userDto,
                               BindingResult result,
                               Model model) {
        User existingUser = userService.findUserByEmail(userDto.getEmail());

        if (existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()) {
            result.rejectValue("email", null,
                    "There is already an account registered with the same email");
        }

        if (result.hasErrors()) {
            model.addAttribute("user", userDto);
            return "/register";
        }

        userService.saveUser(userDto);
        return "redirect:/register?success";
    }

    // handler method to handle login request
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    // handler method to handle list of users
    @GetMapping("/users")
    public String users(Model model) {
        List<UserDto> listUserDto = userService.getAllUsers();
        model.addAttribute("users", listUserDto);
        for(UserDto t: listUserDto){
            System.out.println(t.getId());
        }
        printLoggedInUserId();
        return "users";
    }




    // Hàm này để ấy ra id của user đã đăng nhập
    public void printLoggedInUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails) {
                String username = ((UserDetails) principal).getUsername();
                User user = userService.findUserByEmail(username);
                if (user != null) {
                    System.out.println("ID USER ĐÃ ĐĂNG NHẬP: " +user.getId());
                }
            }
        }
    }

    @GetMapping("/cart")
    public String cart() {
        return "cart";
    }

    @PostMapping("/cart")
    public String addOrder(@RequestBody OrderDto orderDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails) {
                String username = ((UserDetails) principal).getUsername();
                User user = userService.findUserByEmail(username);
                if (user != null) {
                    orderDto.setUserId(user.getId());
                    orderService.createOrder(orderDto);
                    return "Order placed successfully!";
                }
            }
        }
        return "Failed to place order!";
    }

}