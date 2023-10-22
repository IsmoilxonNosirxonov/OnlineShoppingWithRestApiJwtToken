package uz.in.onlineshoppingwithrestapijwttoken.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.*;
import uz.in.onlineshoppingwithrestapijwttoken.domain.Enum.OrderStatus;


import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "orders")
public class Order extends BaseEntity{

    @ManyToOne()
    private User user;

    @OneToMany()
    private List<OrderItem> orderItems=new ArrayList<>();

    @Column(name = "total_price",nullable = false)
    @Positive
    private Double totalPrice;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;
}
