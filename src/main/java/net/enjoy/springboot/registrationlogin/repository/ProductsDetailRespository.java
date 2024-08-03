package net.enjoy.springboot.registrationlogin.repository;

import net.enjoy.springboot.registrationlogin.entity.ProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductsDetailRespository extends JpaRepository<ProductDetail, Long> {
   //Hàm này sẽ trả về danh sách chi tiết sản phẩm theo id sản phẩm
    @Query("SELECT pd FROM ProductDetail pd WHERE pd.product.id = :productId")
    List<ProductDetail> getProductDetailByProductIdSql(@Param("productId") Long productId);
}
