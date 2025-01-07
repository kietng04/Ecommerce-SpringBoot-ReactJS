package net.enjoy.springboot.registrationlogin.repository;
import net.enjoy.springboot.registrationlogin.entity.OrderDetails;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Long> {
      @Query("SELECT od FROM OrderDetails od WHERE od.order.id = :orderId")
        List<OrderDetails> findByOrderId(Long orderId);
    
}
