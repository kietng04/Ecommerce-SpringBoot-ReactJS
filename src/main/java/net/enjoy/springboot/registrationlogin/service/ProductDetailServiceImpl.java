// src/main/java/net/enjoy/springboot/registrationlogin/service/ProductDetailServiceImpl.java
package net.enjoy.springboot.registrationlogin.service;

import net.enjoy.springboot.registrationlogin.dto.ProductDetailDto;
import net.enjoy.springboot.registrationlogin.entity.ProductDetail;
import net.enjoy.springboot.registrationlogin.repository.ProductsDetailRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductDetailServiceImpl implements ProductDetailService {

    //Sử dụng ProductsDetailRespository để truy vấn dữ liệu
    private final ProductsDetailRespository productsDetailRespository;

    @Autowired
    public ProductDetailServiceImpl(ProductsDetailRespository productsDetailRespository) {
        this.productsDetailRespository = productsDetailRespository;
    }

    @Override
    //Hàm này sẽ trả về danh sách chi tiết sản phẩm theo id sản phẩm
    public List<ProductDetailDto> getProductDetailByProductId(Long productId) {
        List<ProductDetail> list_product_detail_sql = productsDetailRespository.getProductDetailByProductIdSql(productId);
        List<ProductDetailDto> list_product_detail_dto = list_product_detail_sql.stream().map(this::convertEntityToDto).collect(Collectors.toList());
        return list_product_detail_dto;

    }

    //Hàm này sẽ chuyển đổi từ entity sang dto
    private ProductDetailDto convertEntityToDto(ProductDetail productDetail) {
        ProductDetailDto productDetailDto = new ProductDetailDto();
        productDetailDto.setId(productDetail.getId());
        productDetailDto.setColor(productDetail.getColor().getColorName());
        productDetailDto.setSize(productDetail.getSize().getSizeName());
        productDetailDto.setPrice(productDetail.getPrice());
        productDetailDto.setQuantity(productDetail.getQuantity());
        return productDetailDto;
    }

    @Override
    //Hàm  chi tiết sản phẩm theo id
    public ProductDetail getSqlProductDetailById(Long id) {
        return productsDetailRespository.findById(id).orElse(null);
    }
}