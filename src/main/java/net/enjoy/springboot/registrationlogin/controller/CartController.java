package net.enjoy.springboot.registrationlogin.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import net.enjoy.springboot.registrationlogin.config.Cart;
import net.enjoy.springboot.registrationlogin.dto.CartItemDto;
import net.enjoy.springboot.registrationlogin.dto.DetailCartItemDto;
import net.enjoy.springboot.registrationlogin.dto.ProductDto;
import net.enjoy.springboot.registrationlogin.entity.ProductDetail;
import net.enjoy.springboot.registrationlogin.entity.User;
import net.enjoy.springboot.registrationlogin.repository.ProductsDetailRespository;
import net.enjoy.springboot.registrationlogin.service.ProductDetailService;
import net.enjoy.springboot.registrationlogin.service.ProductService;
import net.enjoy.springboot.registrationlogin.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
// import productdetaildto
import net.enjoy.springboot.registrationlogin.dto.ProductDetailDto;

@Controller
public class CartController {
    ProductService productService;
    ProductDetailService productDetailService;
    ProductController productController;
    UserService userService;
    
    @Autowired  
    Cart cart;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Autowired
    public void setProductController(ProductController productController) {
        this.productController = productController;
    }

    @Autowired
    public void setProductDetailService(ProductDetailService productDetailService) {
        this.productDetailService = productDetailService;
    }
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/cart/update/{id}")
    public String updateProfile(@PathVariable Long id,@Valid @ModelAttribute("user") User user,
                                @RequestParam("isPasswordChanged") boolean isPasswordChanged,
                                BindingResult result,Model model, Principal principal) {
  
        if (principal == null) {
            return "redirect:/login";
        }

        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "cart";
        }

        User t = userService.updateUser(id, user, isPasswordChanged);
      
        model.addAttribute("user", t);
        System.out.println("ID USER ĐÃ ĐĂNG NHẬP: " +t.getEmail());
        return "redirect:/cart?success";


    }


    @PostMapping("/cart/add")
    @ResponseBody
    public List<CartItemDto> addCart(@RequestBody CartItemDto cartItemDto, HttpSession session) {
        cart.addItem(cartItemDto);
        return cart.getItems();
    }

    @PostMapping("/cart/getCart")
    @ResponseBody
    public List<DetailCartItemDto> getCart() {
        List<DetailCartItemDto> detailCartItemDtos = new ArrayList<>();

        for (CartItemDto cartItemDto : cart.getItems()) {
            DetailCartItemDto detailCartItemDto = new DetailCartItemDto();
            ProductDto productDto = productService.findProductByIdDetail(cartItemDto.getIdDetail());
            detailCartItemDto.setProduct(productDto);
            detailCartItemDto.setQuantity(cartItemDto.getQuantity());
            ProductDetailDto productDetailDto = productDetailService.getProductDetail(cartItemDto.getIdDetail());
            detailCartItemDto.setProductDetail(productDetailDto);
            detailCartItemDtos.add(detailCartItemDto);
        }
        return detailCartItemDtos;
    }

    @PostMapping("/cart/getProductByIdDetail")
    @ResponseBody
    public List<ProductDto> getProductByIdDetail(List<CartItemDto> cartItemDtos) {
        List<ProductDto> products = new ArrayList<>();
        for (CartItemDto cartItemDto : cartItemDtos) {
            ProductDto productDto = productService.findProductByIdDetail(cartItemDto.getIdDetail());
            products.add(productDto);
        }
        return products;
    }

}
