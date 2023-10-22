package uz.in.onlineshoppingwithrestapijwttoken.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "baskets")
public class OrderItem extends BaseEntity{

    @OneToOne()
    @JoinColumn(nullable = false)
    private Product product;

    @Column(nullable = false)
    @Positive
    private Integer amount;

    @ManyToOne(cascade = CascadeType.MERGE)
    private User user;

}
