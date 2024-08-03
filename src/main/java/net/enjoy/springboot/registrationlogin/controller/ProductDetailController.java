// src/main/java/net/enjoy/springboot/registrationlogin/controller/ProductDetailController.java
package net.enjoy.springboot.registrationlogin.controller;

import net.enjoy.springboot.registrationlogin.dto.ProductDetailDto;
import net.enjoy.springboot.registrationlogin.dto.ProductDto;
import net.enjoy.springboot.registrationlogin.service.ProductDetailService;
import net.enjoy.springboot.registrationlogin.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ProductDetailController {
    private final ProductService productService;
    private final ProductDetailService productDetailService;

    @Autowired
    public ProductDetailController(ProductService productService, ProductDetailService productDetailService) {
        this.productService = productService;
        this.productDetailService = productDetailService;
    }

    @GetMapping("/product_detail")
    // http://localhost:8080/product_detail?product_id=1
    //Hàm này sẽ trả về trang chi tiết sản phẩm: product-detail.html
    public String showProductDetail(@RequestParam(value = "product_id", required = false) Long id, Model model) {
        if (id == null) {
            System.out.println("Product ID is null");
            return "error-page";
        }
        System.out.println("***product_Id = " + id);
        ProductDto product = productService.findById(id);
        model.addAttribute("product", product);
        List<ProductDetailDto> list_product_detail = productDetailService.getProductDetailByProductId(id);
        System.out.println("productDetails = " + list_product_detail);
        model.addAttribute("productDetails", list_product_detail);
        return "product-detail";
    }
}