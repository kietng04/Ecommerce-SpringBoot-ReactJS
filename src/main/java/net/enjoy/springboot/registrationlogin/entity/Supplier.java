package net.enjoy.springboot.registrationlogin.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "suppliers")
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Mã nhà cung cấp (manhacungcap)

    @Column(nullable = false)
    private String name; // Tên nhà cung cấp (tennhacungcap)

    @Column(nullable = false)
    private String address; // Địa chỉ (diachi)

    @Column(nullable = false, unique = true)
    private String email; // Email (email)

    @Column(nullable = false, unique = true)
    private String phoneNumber; // Số điện thoại (sdt)

    @Column(nullable = false)
    private int status; // Trạng thái (trangthai)
}