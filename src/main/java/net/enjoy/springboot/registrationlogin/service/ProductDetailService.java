// src/main/java/net/enjoy/springboot/registrationlogin/service/ProductDetailService.java
package net.enjoy.springboot.registrationlogin.service;

import net.enjoy.springboot.registrationlogin.dto.ProductDetailDto;
import net.enjoy.springboot.registrationlogin.entity.ProductDetail;

import java.util.List;

public interface ProductDetailService {
    //Hàm  chi tiết sản phẩm theo id
  ProductDetail getSqlProductDetailById(Long id);

    // Hàm này sẽ trả về danh sách chi tiết sản phẩm theo id sản phẩm
    List<ProductDetailDto> getProductDetailByProductId(Long productId);
}